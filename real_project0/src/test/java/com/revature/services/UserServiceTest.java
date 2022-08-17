package com.revature.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.mockito.Mockito;

import com.revature.dao.UserDao;
import com.revature.models.User;
public class UserServiceTest {
    UserDao userDao = Mockito.mock(UserDao.class);
    UserService userService = new UserService(userDao);
    @Test
    public void testAuthenticate() {
        //arrange
        User objToPass = new User("avicalidas", "hollywood");
        Mockito.when(userDao.findUser(objToPass.getUsername())).thenReturn(objToPass);

        //act
        Boolean actualResult = userService.authenticate(objToPass);

        //assert
        assertTrue(actualResult);
    }
    @Test
    public void testAuthenticateFail() {
        //arrange
        User objToPass = new User("avicalidas", "hollywood");
        Mockito.when(userDao.findUser(objToPass.getUsername())).thenReturn(null);

        //act
        Boolean actualResult = userService.authenticate(objToPass);

        //assert
        assertFalse(actualResult);
    }

    @Test
    public void testGetUser() {
        //arrange
        User objToPass = new User("avicalidas", "hollywood");
        Mockito.when(userDao.findUser(objToPass.getUsername())).thenReturn(objToPass);

        //act
        User actualResult = userService.getUser("avicalidas");

        //assert
        assertTrue(actualResult != null);
    }
}