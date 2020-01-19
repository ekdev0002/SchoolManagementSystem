package com.app.sms.ui.controllers.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.app.sms.dao.impl.EleveDao;
import com.app.sms.dao.impl.EnseignantDao;
import com.app.sms.dao.impl.GestionnaireDao;
import com.app.sms.models.Enseignant;
import com.app.sms.models.Gestionnaire;
import com.app.sms.models.Profils;
import com.app.sms.models.User;
import com.app.sms.ui.impl.MainUIApplication;
import com.app.sms.ui.impl.UILogin;
import com.app.sms.utils.SingleConnection;

public class UILoginController {
	protected UILogin uILogin;
	protected User user;
	
	public UILoginController (UILogin uILogin) {
		this.uILogin = uILogin;
		
		addActionListener () ;
	}
	
	public void addActionListener () {
		
		this.uILogin.addValiderListener ( new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				uILogin.displayNotification("");
			
				String login = uILogin.getLogin();
				String password = uILogin.getPassword();
				String userProfil = uILogin.getUserProfil() ;
				if ( userProfil == Profils.Gestionnaire.name ()) {
					User user = new Gestionnaire(login, password, userProfil) ;
					try {
						GestionnaireDao userDao = new GestionnaireDao() ;
						boolean status = userDao.findByLoginPassword(login, password);
						if ( status == true ) {
							uILogin.dispose();
							
							MainUIApplication mainApplication = new MainUIApplication(user) ;
							GestionnaireMainUIController gestionnaireMainUIController = new GestionnaireMainUIController (uILogin, mainApplication );
							gestionnaireMainUIController.run();
						} else {
							uILogin.displayErrorMessage ( "Authentification failure ! Please try again or contact admin ..." );
						}
					} catch ( SQLException e) {
						uILogin.displayErrorMessage ( "Fatal Error : " + e.getMessage() );
					}
				} else if ( userProfil == Profils.Enseignant.name ()) {
					User user ;
					try {
						EnseignantDao userDao = new EnseignantDao() ;
						boolean status = userDao.findByLoginPassword(login, password);
						if ( status == true ) {
							uILogin.dispose();
							user = userDao.findEnseignantByLoginPassword(login, password) ;

							MainUIApplication mainApplication = new MainUIApplication(user) ;
							EnseignantMainUIController enseignantMainUIController = new EnseignantMainUIController (uILogin, mainApplication );
							enseignantMainUIController.run();
						} else {
							uILogin.displayErrorMessage ( "Authentification failure ! Please try again or contact admin ..." );
						}
					} catch (SQLException e) {
						
						uILogin.displayErrorMessage ( "Fatal Error : " + e.getMessage() );
					}
				}
				else if ( userProfil == Profils.Eleve.name ()) {
					User user ;
					try {
						EleveDao userDao = new EleveDao() ;
						boolean status = userDao.findByLoginPassword(login, password);
						if ( status == true ) {
							uILogin.dispose();
							user = userDao.findElevetByLoginPassword(login, password) ;

							MainUIApplication mainApplication = new MainUIApplication(user) ;
							EleveMainUIController eleveMainUIController = new EleveMainUIController (uILogin, mainApplication );
							eleveMainUIController.run();
						} else {
							uILogin.displayErrorMessage ( "Authentification failure ! Please try again or contact admin ..." );
						}
					} catch (SQLException e) {
						
						uILogin.displayErrorMessage ( "Fatal Error : " + e.getMessage() );
					}
				}
			}
		});
	}

	public void setUp () {
		
		uILogin.setEnabledUIButton (false);
		uILogin.displayNotification( "Please wait while loading data base configuration ... " );
		try {
			SingleConnection.init() ;
			uILogin.setEnabledUIButton(true);
			uILogin.displayNotification("");
		} catch (Exception e) {			
			uILogin.displayErrorMessage(e.getMessage());
			uILogin.setEnabledUIButton (false);
		}
	}
}
