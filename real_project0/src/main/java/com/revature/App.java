package com.revature;




/**
 * Hello world!
 *
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.UserDao;
import com.revature.views.MainMenu;
public class App 
{
    static Logger logger = LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
        UserDao dummyDao = new UserDao();
        AccountDao dummyDaoDos = new AccountDao();
        dummyDaoDos.recollectTotal();
        dummyDao.getUsers();
        MainMenu menu = new MainMenu();
        menu.view();
    }
}