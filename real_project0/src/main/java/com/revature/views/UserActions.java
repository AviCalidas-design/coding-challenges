package com.revature.views;
import java.util.List;

import com.revature.dao.AccountDao;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.util.InputUtil;


public class UserActions {
    AccountService accountService = new AccountService();
    InputUtil inputUtil = new InputUtil();
    AccountDao accountDao = new AccountDao();
    public void view_customer(User user)
    {
        List<Account> yourAccounts = printAccounts(user.getId());
        Boolean done = false;
        while(!done)
        {
            System.out.println("Hello " + user.getFirstName());
            System.out.println("\n\nOptions:\n --Apply\n --View\n --Withdraw\n --Deposit\n --Transfer\n --Delete\n --Exit");
            String input = inputUtil.retrieveString("Command: ");
            try
            {
                switch(input)
                {
                    case "Apply":
                        String accountName = inputUtil.retrieveString("Name of account: ");
                        int startBalance = inputUtil.retrieveInt("Starting balance: ");
                        accountDao.addAccount(new Account(accountName,startBalance,user.getId()));
                        System.out.println("You're request has been sent. An employee will be needed to approve your account");
                        break;
                    case "View":
                        Integer index = inputUtil.retrieveInt("Input the number of the account you'd like to see: ");
                        System.out.println("$" + yourAccounts.get(index-1).getBalance());
                        break;
                    case "Deposit":
                        Integer i = inputUtil.retrieveInt("Input the number of the account you'd like to deposit to: ");
                        int amount = inputUtil.retrieveInt("How much money to deposit?");
                        accountService.updateBalance(yourAccounts.get(i-1), yourAccounts.get(i-1).getBalance() + amount);
                        System.out.println("Successfully deposited $" + amount);
                        accountDao.updateLog(user.getFirstName() + " " + user.getLastName() + " deposited $" + amount + " into " + yourAccounts.get(i).getName());
                        break;
                    case "Withdraw":
                        Integer x = inputUtil.retrieveInt("Input the number of the account you'd like to withdraw from: ");
                        int amt = inputUtil.retrieveInt("How much money to withdraw?");
                        accountService.updateBalance(yourAccounts.get(x-1), yourAccounts.get(x-1).getBalance() - amt);
                        System.out.println("Successfully withdrew $" + amt);
                        accountDao.updateLog(user.getFirstName() + " " + user.getLastName() + " withdrew $" + amt + " from " + yourAccounts.get(x).getName());
                        break;
                    case "Transfer":
                        Integer from = inputUtil.retrieveInt("Input the number of the account you are transferring from: ");
                        Account f = yourAccounts.get(from-1);
                        Integer to = inputUtil.retrieveInt("Input the number of the account you are transferring to: ");
                        Account t = yourAccounts.get(to-1);
                        int y = inputUtil.retrieveInt("How much do you want to transfer? ");
                        accountService.updateBalance(f, f.getBalance() - y);
                        accountService.updateBalance(t, t.getBalance() + y);
                        System.out.println("Transfer complete!");
                        accountDao.updateLog(user.getFirstName() + " " + user.getLastName() + " transferred $" + y + " from " + f.getName() + " to " + t.getName());
                        break;
                    case "Delete":
                        Integer which = inputUtil.retrieveInt("What number of account would you like to delete? ");
                        Account gone = yourAccounts.get(which-1);
                        accountService.deleteAccount(gone.getId());
                        System.out.println("Successfully deleted account");
                        break;
                    case "Exit":
                        done = true;
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
                if(!done)
                {
                    printAccounts(user.getId());
                }
            }
            catch(Exception e)
            {
                System.out.println("Invalid input or transaction");
            }
        }
    }
    public void view_employee(User user)
    {
        Boolean done = false;
        while(!done)
        {
            System.out.println("Hello " + user.getFirstName());
            System.out.println("\n\nOptions:\n --View Unapproved\n --Approve\n --View Customer\n --View Log\n --Exit");
            String input = inputUtil.retrieveString("Command: ");
            try
            {
                switch(input)
                {
                    case "View Unapproved":
                        accountDao.printUnapproved();
                        break;
                    case "Approve":
                        int num = inputUtil.retrieveInt("Number of account to approve: ");
                        accountDao.approve(num-1);
                        System.out.println("Successfully approved account!");
                        break;
                    case "View Customer":
                        int id = inputUtil.retrieveInt("Enter the ID of the customer you'd like to view: ");
                        printAccounts(id);
                        break;
                    case "View Log":
                        accountDao.printLog();
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
        if(user.getType().equals("customer") || user.getType().equals("both"))
        {
            view_customer(user);
        }
        if(user.getType().equals("employee") || user.getType().equals("both"))
        {
            view_employee(user);
        }
    }
    public List<Account> printAccounts(int userId)
    {
        List<Account> yourAccounts = accountDao.getAccounts(userId);
        System.out.println("Your accounts: ");
        for(int i = 0; i < yourAccounts.size(); i++)
        {
            System.out.println((i + 1) + ". " + yourAccounts.get(i).getName());
        }
        return yourAccounts;
    }
}
