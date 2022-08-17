package com.revature.services;
import java.util.List;

import com.revature.dao.AccountDao;
import com.revature.models.Account;


public class AccountService {
    AccountDao adao = new AccountDao();

    public void createAccount(Account account){
        this.adao.createAccount(account);
    }

    public List<Account> getAllAccounts(Integer userId){
        return this.adao.getAccounts(userId);
    }

    public void deleteAccount(Integer accountId){
        this.adao.deleteAccount(accountId);
    }

    public void updateBalance(Account account, int amount) throws Exception{
        this.adao.updateBalance(account, amount);
    }
}
