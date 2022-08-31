package com.revature.controllers;

import com.revature.models.JsonResponse;
import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.http.Context;

public class SessionController {

    UserService userService = new UserService();

    public void login(Context ctx){
        //retrieve request body and store in user obj
        User credentials = ctx.bodyAsClass(User.class);

        //check to see if credentials are valid
        if(userService.authenticate(credentials)){
            User userFromDb = userService.getUser(credentials.getUsername());
            ctx.sessionAttribute("user", userFromDb);

            ctx.json(new JsonResponse(true, "login successful", userFromDb));
        }else{
            ctx.json(new JsonResponse(false, "invalid username or password", null));
        }
    }

    public void logout (Context ctx){
        ctx.sessionAttribute("user", null);
        ctx.json(new JsonResponse(true, "logout success and session invalidated", null));
    }

    public void checkSession(Context ctx){
        User user = ctx.sessionAttribute("user");

        if(user == null){
            ctx.json(new JsonResponse(false, "no session found", null));
        }else{
            ctx.json(new JsonResponse(true, "session found", user));
        }
    }
    
}