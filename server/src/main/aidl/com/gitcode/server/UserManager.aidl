// UserManager.aidl
package com.gitcode.server;

// Declare any non-default types here with import statements
import com.gitcode.server.User;

interface UserManager {

    void addUser(inout User user);

    User getUser(int index);
}

