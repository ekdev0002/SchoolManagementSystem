package com.app.sms.dao.impl;

import java.sql.Connection;

import com.app.sms.utils.SingleConnection;

public abstract class Dao {
	
	protected Connection connection ;

	public Dao() {
		this.connection = SingleConnection.getInstance();
	}
	
}
