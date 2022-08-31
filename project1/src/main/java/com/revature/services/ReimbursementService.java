package com.revature.services;
import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;


public class ReimbursementService {
    ReimbursementDao adao = new ReimbursementDao();

    public void createAccount(Reimbursement account){
        this.adao.createAccount(account);
    }

    public List<Reimbursement> getAllAccounts(Integer userId){
        return this.adao.getAccounts(userId);
    }
    public List<Reimbursement> everything(boolean filtered)
    {
        return this.adao.recollectTotal(true, filtered);
    }
    public void authorize(int id, String action)
    {
        adao.authorize(id, action);
    }
    public void deleteAccount(Integer accountId){
        this.adao.deleteAccount(accountId);
    }

    public void updateBalance(Reimbursement account, int amount) throws Exception{
        this.adao.updateBalance(account, amount);
    }
}
