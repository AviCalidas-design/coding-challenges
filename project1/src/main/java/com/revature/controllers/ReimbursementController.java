package com.revature.controllers;

//import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.JsonResponse;
import com.revature.models.User;
import com.revature.services.ReimbursementService;

import io.javalin.http.Context;

public class ReimbursementController {

    ReimbursementService reimbursementService = new ReimbursementService();

    public void getReis(Context ctx){
        Boolean all = Boolean.valueOf(ctx.pathParam("all"));
        Boolean filtered = Boolean.valueOf(ctx.pathParam("filtered"));
        User user = ctx.sessionAttribute("user");

        if(user == null){
            ctx.json(new JsonResponse(false, "cant retrieve reimbursement because no session found", null));
            return;
        }
        List<Reimbursement> items;
        if(!all)
        {
            items = reimbursementService.getAllAccounts(user.getId());
        }
        else
        {
            items = reimbursementService.everything(filtered);
        }

        ctx.json(new JsonResponse(true, "retrieving all reimbursements for " + user.getFirstName(), items));
    }

    public void createRei(Context ctx){
        try
        {
            Reimbursement rei = ctx.bodyAsClass(Reimbursement.class);
            
            User userFromSession = ctx.sessionAttribute("user");
            
            rei.setUserId(userFromSession.getId());
            rei.giveId();

            reimbursementService.createAccount(rei);

            ctx.json(new JsonResponse(true, "Reimbursement created", rei));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            ctx.json(new JsonResponse(false, "Reimbursement not created", null));
        }
    }

    public void deleteRei(Context ctx){
        Integer groceryItemId = Integer.parseInt(ctx.pathParam("itemId"));

        reimbursementService.deleteAccount(groceryItemId);

        ctx.json(new JsonResponse(true, "reimbursement deleted if exists", null));

    }

    public void authorize(Context ctx){
        Integer id = Integer.parseInt(ctx.pathParam("itemId"));
        String action = new String(ctx.pathParam("action"));
        reimbursementService.authorize(id, action);

        ctx.json(new JsonResponse(true, "item marked in cart if exists", null));
    }
}
