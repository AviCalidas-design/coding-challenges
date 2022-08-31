package com.revature.models;
public class Reimbursement {
    private static int total = 0;
    private String name;
    private int balance;
    private int id;
    private int userId;
    private String type;
    private String status;
    public Reimbursement()
    {
        id = -1;
        status = "pending";
    }
    public Reimbursement(String n, int bal,int u, String t)
    {
        name = n;
        balance = bal;
        id = 0;
        userId = u;
        type = t;
        status = "pending";
    }
    public Reimbursement(String n, int bal, String t)
    {
        name = n;
        balance = bal;
        id = 0;
        userId = -1;
        type = t;
        status = "pending";
    }
    public void giveId()
    {
        total += 1;
        id = total;
    }
    public void approve()
    {
        status = "approved";
    }
    public void deny()
    {
        status = "denied";
    }
    public String getStatus()
    {
        return status;
    }
    public String getType()
    {
        return type;
    }
    public Reimbursement(String n, int bal,int u, int i, String t,String s)
    {
        name = n;
        balance = bal;
        id = i;
        userId = u;
        type = t;
        status = s;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    public static void setTotal(int x)
    {
        total = x;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int x)
    {
        this.id = x;
    }
    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", amount='" + getBalance() + "'" +
            ", user ID='" + getUserId() + "'" +
            ", type='" + getType() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
