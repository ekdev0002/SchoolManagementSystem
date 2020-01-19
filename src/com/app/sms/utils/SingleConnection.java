package com.app.sms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import com.app.sms.exceptions.FileNotFoundException;

public class SingleConnection {

	private static Configuration parameters;
	private static Connection connect;

	// Singleton Pattern
	private SingleConnection (Configuration parameters) throws ClassNotFoundException, SQLException {		
	    connect = DriverManager.getConnection (parameters.getUrl(), parameters.getUser(), parameters.getPassword());
	}
	
	public static Connection getInstance() {		
	    return connect;
	}
	
	// utile pour le démarrage de l'application - vérification de l'état de la connexion à la base.
	public static void init () throws ClassNotFoundException, SQLException, FileNotFoundException, JAXBException {
		
		if (connect == null) {
			parameters = Utilitaire.getParameters ();
			new SingleConnection (parameters);
	    }
	}
	
	public static void releaseConnection() throws SQLException {
		if (connect != null) {
			connect.close();
		}
	}
}
