package com.revature.views;
import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.util.InputUtil;


public class UserActions {
    ReimbursementService accountService = new ReimbursementService();
    InputUtil inputUtil = new InputUtil();
    ReimbursementDao accountDao = new ReimbursementDao();
    public void view_employee(User user)
    {
        Boolean done = false;
        while(!done)
        {
            System.out.println("Hello " + user.getFirstName());
            System.out.println("\n\nOptions:\n --Apply \n --View\n --Exit");
            String input = inputUtil.retrieveString("Command: ");
            try
            {
                switch(input)
                {
                    case "Apply":
                        String accountName = inputUtil.retrieveString("Name of reimbursement: ");
                        int startBalance = inputUtil.retrieveInt("Amount: ");
                        String type = inputUtil.retrieveString("Type of Account(Lodging, Travel, Food, Other): ");
                        Reimbursement addition = new Reimbursement(accountName,startBalance,user.getId(),type);
                        addition.giveId();
                        accountDao.createAccount(addition);
                        System.out.println("You're reimbursement has been applied for. A manager will be needed to approve your reimbursement");
                        break;
                    case "View":
                        printAccounts(user.getId());
                        break;
                    case "Exit":
                        done = true;
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            }
            catch(Exception e)
            {
                System.out.println("Invalid input or transaction");
            }
        }
    }
    public void view_manager(User user)
    {
        Boolean done = false;
        while(!done)
        {
            System.out.println("Hello " + user.getFirstName());
            System.out.println("\n\nOptions:\n --View\n --Approve\n --Deny\n --View Filtered\n --Exit");
            String input = inputUtil.retrieveString("Command: ");
            try
            {
                switch(input)
                {
                    case "View":
                        accountDao.printAllAccounts(false);
                        break;
                    case "Approve":
                        int num = inputUtil.retrieveInt("ID number of reimbursement to approve: ");
                        accountDao.authorize(num, "approve");
                        System.out.println("Successfully approved reimbursement!");
                        break;
                    case "Deny":
                        int n = inputUtil.retrieveInt("ID number of reimbursement to deny: ");
                        accountDao.authorize(n, "deny");
                        System.out.println("Successfully denied reimbursement!");
                        break;
                    case "View Filtered":
                        accountDao.printAllAccounts(true);
                        break;
                    case "Exit":
                        done = true;
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            }
            catch(Exception e)
            {
                System.out.println("Invalid input or transaction");
            }
        }
    }
    public void view_general(User user)
    {
        if(user.getType().equals("employee") || user.getType().equals("both"))
        {
            view_employee(user);
        }
        if(user.getType().equals("manager") || user.getType().equals("both"))
        {
            view_manager(user);
        }
    }
    public List<Reimbursement> printAccounts(int userId)
    {
        List<Reimbursement> yourAccounts = accountDao.getAccounts(userId);
        System.out.println("Your reimbursements: ");
        for(int i = 0; i < yourAccounts.size(); i++)
        {
            System.out.println((i + 1) + ". " + yourAccounts.get(i));
        }
        return yourAccounts;
    }
}
