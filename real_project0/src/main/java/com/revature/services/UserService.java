package com.revature.services;

import com.revature.dao.UserDao;
import com.revature.models.User;

public class UserService {
    private UserDao udao;
    public UserService()
    {
        this.udao = new UserDao();
    }
    public UserService(UserDao theDao)
    {
        this.udao = theDao;
    }
    public boolean authenticate(User credentials) {

        //System.out.println(credentials);
        User user = udao.findUser(credentials.getUsername());
        //System.out.println(user);
        //System.out.println(udao.getUsers());
        if(user == null){
            return false;
        }

        if(user.getPassword().equals(credentials.getPassword())){
            return true;
        }

        return false;
    }
    public User getUser(String username){
        return udao.findUser(username);
    }
    public User createUser(User userToCreate){

        User userFromDb = udao.findUser(userToCreate.getUsername());

        if(userFromDb == null){
            userToCreate.giveId();
            udao.createUser(userToCreate);
            return userToCreate;
        }

        return null;
    }
}
