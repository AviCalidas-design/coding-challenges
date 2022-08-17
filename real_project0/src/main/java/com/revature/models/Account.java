package com.revature.models;
public class Account {
    private static int total = 0;
    private String name;
    private int balance;
    private int id;
    private int userId;
    public Account(String n, int bal,int u)
    {
        name = n;
        balance = bal;
        id = 0;
        userId = u;
    }
    public void giveId()
    {
        total += 1;
        id = total;
    }
    public Account(String n, int bal,int u, int i)
    {
        name = n;
        balance = bal;
        id = i;
        userId = u;
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
            ", balance='" + getBalance() + "'" +
            ", user ID='" + getUserId() + "'" +
            "}";
    }
}
