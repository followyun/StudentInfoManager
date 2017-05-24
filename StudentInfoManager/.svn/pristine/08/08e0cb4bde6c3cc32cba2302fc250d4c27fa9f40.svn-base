package com.my.dao;


import static org.junit.Assert.*;

import org.junit.Test;

import com.my.dao.UsersDao;
import com.my.dao.UsersDaoImpl;
import com.my.entity.Users;



public class UsersDaoImplTest {
	@Test
	public void usersLoginTest() {
		UsersDao usersdao = new UsersDaoImpl();
		assertEquals(true,usersdao.usersLogin(new Users(1, "xm", "1234")));
	}
}
