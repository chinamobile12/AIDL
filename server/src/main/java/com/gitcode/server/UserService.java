package com.gitcode.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MuQuan on 2019/7/1 0001.
 * Features：
 */
public class UserService extends Service {
    private static final String TAG = "Server";
    private List<User> list = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        list.add(new User("GitCode", "深圳"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"on Bind");
        return stub;
    }


    private UserManager.Stub stub = new UserManager.Stub() {
        @Override
        public void addUser(User user) throws RemoteException {
            list.add(user);
            Log.i(TAG,"add user:"+user);
        }

        @Override
        public User getUser(int index) throws RemoteException {
            Log.i(TAG,"get user,index:"+index);
            return list.size() > index && index >= 0 ? list.get(index) : null;
        }
    };
}
