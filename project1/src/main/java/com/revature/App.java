package com.revature;




/**
 * Hello world!
 *
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.views.MainMenu;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.session.SessionContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.SessionController;
import com.revature.controllers.UserController;
import com.revature.models.JsonResponse;
import com.revature.models.Reimbursement;
import com.revature.models.User;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.staticfiles.Location;
public class App 
{
    static Logger logger = LogManager.getLogger(App.class);
    public static void main(String args[])
    {
        UserDao dummyDao = new UserDao();
        ReimbursementDao dummyDaoDos = new ReimbursementDao();
        dummyDaoDos.recollectTotal(false,false);
        dummyDao.getUsers();
        MainMenu menu = new MainMenu();
        //menu.view();
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/frontend", Location.CLASSPATH);
        }).start(9000);
        UserController uc = new UserController();
        SessionController sc = new SessionController();
        ReimbursementController rc = new ReimbursementController();
        //List<Reimbursement> reis = dummyDaoDos.recollectTotal(true);
        //gc.setAddition(reis);
        //app.get("/api/reimbursements",gc::jsonExample);
        app.post("/api/users", uc::register);
        app.get("/api/users", uc::printAllUsers);
        app.get("/api/session", sc::checkSession);
        app.post("/api/session", sc::login);
        app.delete("/api/session", sc::logout);
        app.get("/api/reimbursement/{all}/{filtered}", rc::getReis);
        app.post("/api/reimbursement", rc::createRei);
        app.patch("/api/reimbursement/{itemId}/{action}", rc::authorize);
        app.delete("/api/reimbursement/{itemId}", rc::deleteRei);
    }
}