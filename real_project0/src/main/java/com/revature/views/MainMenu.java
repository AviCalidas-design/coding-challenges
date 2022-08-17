package com.revature.views;

import java.util.Scanner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.InputUtil;



public class MainMenu {
    static Logger logger = LogManager.getLogger(MainMenu.class);

    UserService userService = new UserService();
    InputUtil inputUtil = new InputUtil();
    public void view(){
        Scanner scanner = new Scanner(System.in);
        Boolean done = false;

        while(!done){
            System.out.println( "Bank Account App!\n --Login\n --Register\n --Exit" );
            String input = scanner.nextLine();

            switch (input) {
                case "Login":
                    String usernameInput = inputUtil.retrieveString("Username: ");
                    String passwordInput = inputUtil.retrieveString("Password: ");

                    Boolean areCredentialsValid = userService.authenticate(new User(usernameInput, passwordInput));

                    if(areCredentialsValid){
                        logger.info("User " + usernameInput + " successfully logged into system");
                        User userFromDb = this.userService.getUser(usernameInput);
                        new UserActions().view_general(userFromDb);
                    }else{
                        System.out.println("Invalid username or password");
                    }

                    break;
                case "Register":
                    String usernameInputReg = inputUtil.retrieveString("Username: ");
                    String passwordInputReg = inputUtil.retrieveString("Password: ");
                    String firstNameInputReg = inputUtil.retrieveString("First Name: ");
                    String lastNameInputReg = inputUtil.retrieveString("Last Name: ");
                    String typeInputReg = inputUtil.retrieveString("Employee, customer, or both: ");
                    //System.out.println("First");
                    User userToCreate = new User(usernameInputReg, passwordInputReg, firstNameInputReg, lastNameInputReg, typeInputReg);
                    //System.out.println("Second");
                    User userFromDb = this.userService.createUser(userToCreate);
                    //System.out.println("Third");
                    if(userFromDb == null){
                        System.out.println("Username already exists... aborting");
                    }else{
                        System.out.println("User Created");
                    }
                    break;
                case "Exit":
                    done = true;
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }       
    }
    scanner.close();
}
}
