package com.revature.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReimbursementDao {
    static Logger logger = LogManager.getLogger(ReimbursementDao.class);
    //private static List<Reimbursement> unapproved = new ArrayList<Reimbursement>();
    private static String log = new String();
    public void createAccount(Reimbursement rei) {
        //approved.add(account);
        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "insert into reimbursements (reimbursement_name, amount, reimbursement_id, user_id, typeOf, status) values (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, rei.getName());
            ps.setInt(2, rei.getBalance());
            ps.setInt(3, rei.getId());
            ps.setInt(4, rei.getUserId());
            ps.setString(5, rei.getType());
            ps.setString(6,rei.getStatus());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Reimbursement> recollectTotal(boolean real, boolean filtered)
    {
        int total = 0;
        List<Reimbursement> reis = new ArrayList<Reimbursement>();
        try {
            Connection c = ConnectionUtil.getConnection();

            String s = "select * from reimbursements";
            if(filtered)
            {
                s += " order by status";
            }
            PreparedStatement p = c.prepareStatement(s);

            ResultSet r = p.executeQuery();

            while(r.next()){
                reis.add(new Reimbursement(r.getString(1),r.getInt(2),r.getInt(4), r.getInt(3),r.getString(5),r.getString(6)));
                if(!real)
                {
                    total += 1;
                }
            }

            c.close();
            
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
            logger.error("Sql Exception Occured", e);
        }
        if(!real)
        {
            Reimbursement.setTotal(total);
        }
        return reis;
    }
    /*
    public static void initialize()
    {
        try
        {
        Scanner dataCollect = new Scanner(new File("AccountData.txt"));
        int i = 0;
        while(dataCollect.hasNextLine())
        {
            String data = dataCollect.nextLine();
            String[] values = data.split(",");
            Account addition = new Account(values[0],Integer.parseInt(values[1]),Integer.parseInt(values[3]));
            addition.setId(Integer.parseInt(values[2]));
            i++;
            approved.add(addition);
        }
        Account.setTotal(i);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void close() throws Exception
    {
        dataWriter = new FileWriter("AccountData.txt");
        for(Account account : approved)
        {
            dataWriter.write(account.getName() + "," + account.getBalance() + "," + account.getId() + "," + account.getUserId() + "\n");
        }
        dataWriter.close();
    }
    */
    public void updateLog(String addition)
    {
        log += addition + "\n";
    }
    public void printLog()
    {
        System.out.println(log);
    }
    /*
    public void addAccount(Reimbursement acc)
    {
        unapproved.add(acc);
    }
    */
    public void authorize(int id, String action)
    {
        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "update reimbursements set status = ? where reimbursement_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(2, id);

            if(action.equals("approve"))
            {
                ps.setString(1,"approved");
            }
            else if(action.equals("deny"))
            {
                ps.setString(1,"denied");
            }
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /*
    public void printUnapproved()
    {
        System.out.println("Unapproved Accounts:");
        for(int i = 0; i < unapproved.size() ; i++)
        {
            System.out.println((i+1) + ". " + unapproved.get(i));
        }
    }
    */
    public void printAllAccounts(boolean filtered)
    {
        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "select * from reimbursements;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Reimbursement> pendings = new ArrayList<Reimbursement>();
            List<Reimbursement> denieds = new ArrayList<Reimbursement>();
            while(rs.next()){
                Reimbursement addition = new Reimbursement(rs.getString(1),rs.getInt(2),rs.getInt(4), rs.getInt(3),rs.getString(5),rs.getString(6));
                if((!filtered) || rs.getString(6).equals("approved"))
                {
                    System.out.println(addition);
                }
                else
                {
                    if(rs.getString(6).equals("pending"))
                    {
                        pendings.add(addition);
                    }
                    else if(rs.getString(6).equals("denied"))
                    {
                        denieds.add(addition);
                    }
                }
            }
            for(int i = 0; i < pendings.size(); i++)
            {
                System.out.println(pendings.get(i));
            }
            for(int i = 0; i < denieds.size(); i++)
            {
                System.out.println(denieds.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Reimbursement> getAccounts(Integer userId)
    {
        List<Reimbursement> accounts = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "select * from reimbursements where user_id = ? order by reimbursement_id;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);


            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                accounts.add(new Reimbursement(rs.getString(1),rs.getInt(2),rs.getInt(4), rs.getInt(3),rs.getString(5),rs.getString(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
        /*
        List<Account> endList = new ArrayList<Account>();
        for(int i = 0; i < approved.size() ; i++)
        {
            if(approved.get(i).getUserId() == userId)
            {
                endList.add(approved.get(i));
            }
        }
        return endList;
        */
    }
    /*
    private int findBalance(Integer accountId)
    {
        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "select * from accounts where account_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, accountId);


            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getInt(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    */
    public void deleteAccount(Integer accountId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "delete from reimbursements where reimbursement_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, accountId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        for(int i = 0; i < approved.size(); i++)
        {
            if(approved.get(i).getId() == accountId)
            {
                approved.remove(i);
            }
        }
        */
    }
    public void updateBalance(Reimbursement account, int amount) throws Exception{
        if(amount < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        account.setBalance(amount);
        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "update reimbursements set amount = ? where reimbursement_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,amount);
            ps.setInt(2, account.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        for(int i = 0; i < approved.size() ; i++)
        {
            if(approved.get(i).getId() == accountId)
            {
                approved.get(i).setBalance(amount);
            }
        }
        */
    }
}
