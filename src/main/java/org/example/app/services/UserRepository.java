package org.example.app.services;


import org.apache.log4j.Logger;
import org.example.web.controllers.LoginController;
import org.example.web.dto.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();
    private Logger logger = Logger.getLogger(LoginController.class);

    public boolean checkUserRepo(String username, String password) {
        Iterator<User> it = users.iterator();
        boolean checkUser = false;
        while (it.hasNext()) {
            User user = it.next();
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                checkUser = true;
            }
        }
        return checkUser;
    }


    public void addUser(User user) {
        users.add(user);
    }


}
