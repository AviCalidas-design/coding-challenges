package com.revature.controllers;

import com.revature.models.JsonResponse;
import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.http.Context;

public class UserController {

    UserService userService = new UserService();

    public void register(Context ctx){
        User userCredentials = ctx.bodyAsClass(User.class);
        
        User userFromDb = userService.createUser(userCredentials);

        if(userFromDb == null){
            ctx.json(new JsonResponse(false, "username already exists in system", null));
        }else{
            ctx.json(new JsonResponse(true, "user created", userFromDb));
        }
    }
    public void printAllUsers(Context ctx)
    {
        ctx.json(new JsonResponse(true,"All users: ", userService.getAllUsers()));
    }

}