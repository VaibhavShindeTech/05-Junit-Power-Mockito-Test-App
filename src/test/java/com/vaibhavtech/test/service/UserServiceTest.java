package com.vaibhavtech.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import com.vaibhavtech.dao.UserDao;
import com.vaibhavtech.service.UserService;

public class UserServiceTest {
	@Test
	public void testGetNameByUserId() {
		// creating mock object for UserDao class
		UserDao userDaoMock = PowerMockito.mock(UserDao.class);
		PowerMockito.when(userDaoMock.findNameById(101)).thenReturn("Dhoni");

		// calling getNameByUserId() method of UserService class
		UserService userService = new UserService(userDaoMock);
		String actualName = userService.getNameByUserId(101);
		String expectedName = "Dhoni";
		assertEquals(expectedName, actualName);
	}

	@Test
	public void testGetEmailByUserId() {
		UserDao userDaoMock = PowerMockito.mock(UserDao.class);
		PowerMockito.when(userDaoMock.findEmailById(101)).thenReturn("dhoni@bcci.com");

		UserService userService = new UserService(userDaoMock);
		String actualEmail = userService.getEmailByUserId(101);
		String expectedEmail = "dhoni@bcci.com";

		assertEquals(expectedEmail, actualEmail);
	}

	@Test
	public void testDoProcess() {
		UserService userServiceMock = PowerMockito.mock(UserService.class);
		try {
			PowerMockito.doNothing().when(userServiceMock, "pushMsgToKafkaTopic", anyString());
			userServiceMock.doProcess();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDoWorkWithPrivateMethodMock() throws Exception {
		UserService service = new UserService();
		UserService spy = PowerMockito.spy(service);
		 PowerMockito.doReturn("TEST MSG").when(spy, "formatMsg", "test msg");
		String formattedMsg = service.doWork("test msg");
		assertEquals("TEST MSG", formattedMsg);

	}
}
