package com.app.sms.models;

import java.sql.SQLException;
import java.util.List;

import com.app.sms.dao.impl.EleveDao;
import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;

/**
 * @author a459079
 *
 */
public class Eleve extends User {

	private int idClasse;
	private String libelleClasse;
	
	public Eleve(int id, String nom, String prenom, String adresse, String genre, String telephone,
			String email, String login, String password) {
		super(id, nom, prenom, adresse, genre, telephone, email, login, password);
	}
	
	public Eleve(int id, String nom, String prenom, String genre, String telephone,
			String email, String login, String password) {
		super(id, nom, prenom, genre, telephone, email, login, password);
	}
	
	/**
	 * @param login
	 * @param password
	 */
	public Eleve(String login, String password) {
		super (login, password);
	}

	public Eleve() {}

	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
		
	}
	
	public void setLibelleClasse(String libelleClasse) {
		this.libelleClasse = libelleClasse;
	}
	
	public String getLibelleClasse () {
		return libelleClasse;
	}
	
	public int getIdClasse() {
		return idClasse;
	}

	public void create() throws AlreadyExistDataException, NotFoundDataException, SQLException {
		EleveDao eleveDao = new EleveDao ();
		
		if ( eleveDao.find(this) ) {
			throw new AlreadyExistDataException ( "An error occured : Eleve already Exit !" ) ;
		} else {
			eleveDao.create(this) ;
		}
	}
	
	public void update() throws NotFoundDataException, SQLException {
		EleveDao eleveDao = new EleveDao ();
		
		if (null != eleveDao.find(id)) {
			eleveDao.update(this);
		} else {
			throw new NotFoundDataException ( "An error occured : l'eleve n'existe pas !" ) ;
		}
	}

	public void delete() throws NotFoundDataException, SQLException {
		EleveDao eleveDao = new EleveDao ();
		
		if (null != eleveDao.find(id)) {
			eleveDao.delete(id);
		} else {
			throw new NotFoundDataException ( "An error occured : l'eleve n'existe pas !" ) ;
		}
	}
	
	public static List<Eleve> list() throws SQLException {
		EleveDao eleveDao = new EleveDao ();
		return eleveDao.list();
	}
	
	public static List<Eleve> list(String idClasse) throws SQLException {
		EleveDao eleveDao = new EleveDao ();
		return eleveDao.list(idClasse);
	}

	public static List<Eleve> list(int id)throws SQLException  {
		EleveDao eleveDao = new EleveDao ();
		return eleveDao.list(id);
	}
}