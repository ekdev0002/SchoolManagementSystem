package com.app.sms.dao;

import java.sql.SQLException;

public interface IUserDao {
	/**
	 * @param login
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	boolean findByLoginPassword(String login, String password) throws SQLException;
}
