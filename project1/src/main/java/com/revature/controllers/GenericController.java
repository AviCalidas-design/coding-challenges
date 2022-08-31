package com.revature.controllers;

import io.javalin.http.Context;
//import io.javalin.http.Handler;

/*
import java.util.ArrayList;
import java.util.List;
*/
import com.revature.models.JsonResponse;
import com.revature.models.User;
public class GenericController {
    private Object addition;
    public void sayHi(Context ctx){
        System.out.println("say hi endpoint hit");
        ctx.result("Hello World");
    }
    public void setAddition(Object a)
    {
        addition = a;
    }
    public void htmlExample(Context ctx){
        //modify the header "Content-Type" to let the client know what kind of data is going to be sent back
        ctx.contentType("text/html");

        ctx.result("<h1>Hello HTML</h1>");
    }

    public void jsonExample(Context ctx){
        ctx.contentType("application/json");

        ctx.json(new JsonResponse(true, "retreiving all users from db...", addition));
        
    }

    public void queryParamExample(Context ctx){
        Integer id = Integer.parseInt(ctx.queryParam("id"));
        String name = ctx.queryParam("name");
        System.out.println(id);
        System.out.println(name);

        ctx.result("The value that you sent was " + id + " and name was " + name);
    }

    public void pathParamExample(Context ctx){
        Integer id = Integer.parseInt(ctx.pathParam("id"));

        ctx.result("The value that you sent was " + id);
    }

    public void bodyDataExample(Context ctx){
        //converts json string to java object
        User userFromClient = ctx.bodyAsClass(User.class);

        System.out.println(userFromClient);
        /* userFromClient.setUsername(new StringBuilder(userFromClient.getUsername()).reverse().toString());
        ctx.status(418);
        ctx.json(userFromClient); */
    }

    public void formDataExample(Context ctx){
        String firstname = ctx.formParam("firstname");
        String lastname = ctx.formParam("lastname");
        User user = new User();
        user.setFirstName(firstname);
        user.setLastName(lastname);

        ctx.json(new JsonResponse(true, "sending back first and last name",user));
    
    }
    
}
