package com.app.sms.models;

import java.sql.SQLException;
import java.util.List;

import com.app.sms.dao.impl.GestionnaireDao;
import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;

public class Gestionnaire extends User {
	
	private String dateNaissance;
	
	public Gestionnaire(int id, String nom, String prenom, String adresse, String dateNaissance, String telephone,
			String email, String login, String password) {
		super(id, nom, prenom, adresse, dateNaissance, telephone, email, login, password);
		this.dateNaissance = dateNaissance;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * @param login
	 * @param password
	 */
	public Gestionnaire(String login, String password) {
		super(login, password);
	}

	public Gestionnaire(String login, String password, String userProfil) {
		this(login, password);
		this.userProfil = userProfil;
	}
	
	public Gestionnaire() {};
	
	
	
	public void create() throws AlreadyExistDataException, SQLException {
		GestionnaireDao gestionnaireDao = new GestionnaireDao();
		
		if ( gestionnaireDao.find(this) ) {
			throw new AlreadyExistDataException ( "An error occured : Enseignant already Exit !" ) ;
		} else {
			gestionnaireDao.create(this) ;
		}
	}
	
	public void update() throws NotFoundDataException, SQLException {
		GestionnaireDao gestionnaireDao = new GestionnaireDao();
		
		if (null != gestionnaireDao.find(id)) {
			gestionnaireDao.update(this);
		} else {
			throw new NotFoundDataException ( "An error occured : l'enseignant n'existe pas !" ) ;
		}
	}

	public void delete() throws NotFoundDataException, SQLException {
		GestionnaireDao gestionnaireDao = new GestionnaireDao();
		
		if (null != gestionnaireDao.find(id)) {
			gestionnaireDao.delete(id);
		} else {
			throw new NotFoundDataException ( "An error occured : l'enseignant n'existe pas !" ) ;
		}
	}
	
	public static List<Gestionnaire> list() throws SQLException {
		GestionnaireDao gestionnaireDao = new GestionnaireDao();
		return gestionnaireDao.list();
	}
	
	public static Gestionnaire getGestionnaireById(int id) throws SQLException {
		GestionnaireDao gestionnaireDao = new GestionnaireDao();
		return gestionnaireDao.find(id);
	}
}