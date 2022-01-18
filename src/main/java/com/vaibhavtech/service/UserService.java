package com.vaibhavtech.service;

import com.vaibhavtech.dao.UserDao;

public class UserService {
	private UserDao userDao;

	public UserService() {
	}

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getNameByUserId(Integer id) {
		return userDao.findNameById(id);
	}

	public String getEmailByUserId(Integer id) {
		return userDao.findEmailById(id);
	}

	public void doProcess() {
		System.out.println("doProcess() method started");
		pushMsgToKafkaTopic("msg");
		System.out.println("doProcess() method ended");
	}

	public void pushMsgToKafkaTopic(String msg) {
		System.out.println("msg pushing to kafka......");
	}

	public String doWork(String msg) {
		String formattedMsg = formatMsg(msg);
		return formattedMsg;
	}

	private String formatMsg(String msg) {
		return msg.toUpperCase();
	}

}
