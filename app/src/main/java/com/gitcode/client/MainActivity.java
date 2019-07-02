package com.gitcode.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gitcode.server.User;

import com.gitcode.server.UserManager;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Client";
    private UserManager mUserManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toBindService();
    }

    private void toBindService() {
        Intent intent = new Intent("com.gitcode.server.userservice");
        intent.setPackage("com.gitcode.server");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mUserManager = UserManager.Stub.asInterface(service);

            try {
                User user = mUserManager.getUser(0);
                Log.e(TAG, user.toString());

                mUserManager.addUser(new User("张三","北京"));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
