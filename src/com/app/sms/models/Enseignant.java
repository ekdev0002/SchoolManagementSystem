package com.app.sms.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.impl.EnseignantDao;
import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;

public class Enseignant extends User {

	private List<Integer> idClasses = new ArrayList<>();
	private List<String> libelleClasses = new ArrayList<>();
	private List<Integer> idCandidatures = new ArrayList<>();
		
	private List<Classe> classes = new ArrayList<>();
	private List<Candidature> candidatures = new ArrayList<>();
	
	private List<Cours> cours = new ArrayList<>();
	private List<Devoirs> devoirs = new ArrayList<>();
	
	public Enseignant() {}
	
	public Enseignant(int id, String nom, String prenom, String adresse, String telephone,
			String email, String login, String password) {
		super(id, nom, prenom, adresse, telephone, email, login, password);
	}

	/**
	 * @param login
	 * @param password
	 */
	public Enseignant(String login, String password) {
		super(login, password);
	}

	public Enseignant(String login, String password, String userProfil) {
		super(login, password);
		this.userProfil = userProfil;
	}

	public List<Integer> getIdClasses() {
		return idClasses ;
	}

	public void setIdClasses(List<Integer> idClasses) {
		this.idClasses = idClasses;
	}
	
	public List<Integer> getIdCandidatures() {
		return idCandidatures;
	}

	public void setIdCandidatures(List<Integer> idCandidatures) {
		this.idCandidatures = idCandidatures;
	}

	public List<String> getLibelleClasses() {
		return libelleClasses;
	}

	public void setLibelleClasses(List<String> libelleClasses) {
		this.libelleClasses = libelleClasses;
	}

	public List<Classe> getClasses() {
		return classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

	public List<Candidature> getCandidatures() {
		return candidatures;
	}

	public void setCandidatures(List<Candidature> candidatures) {
		this.candidatures = candidatures;
	}
	
	
	public void create() throws AlreadyExistDataException, SQLException {
		EnseignantDao enseignantDao = new EnseignantDao ();
		
		if ( enseignantDao.find(this) ) {
			throw new AlreadyExistDataException ( "An error occured : Enseignant already Exit !" ) ;
		} else {
			enseignantDao.create(this) ;
		}
	}
		
	public void update() throws NotFoundDataException, SQLException {
		EnseignantDao enseignantDao = new EnseignantDao ();
		
		if (null != enseignantDao.find(id)) {
			enseignantDao.update(this);
		} else {
			throw new NotFoundDataException ( "An error occured : l'enseignant n'existe pas !" ) ;
		}
	}

	public void delete() throws NotFoundDataException, SQLException {
		EnseignantDao enseignantDao = new EnseignantDao ();
		
		if (null != enseignantDao.find(id)) {
			enseignantDao.delete(id);
		} else {
			throw new NotFoundDataException ( "An error occured : l'enseignant n'existe pas !" ) ;
		}
	}
	
	public static List<Enseignant> list() throws SQLException {
		EnseignantDao enseignantDao = new EnseignantDao ();
		return enseignantDao.list();
	}
	
	public static Enseignant getEnseignantById(int id) throws SQLException {
		EnseignantDao enseignantDao = new EnseignantDao ();
		return enseignantDao.find(id);
	}

	public List<Cours> getCours() {
		return cours;
	}

	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}

	public List<Devoirs> getDevoirss() {
		return devoirs;
	}

	public void setDevoirss(List<Devoirs> devoirs) {
		this.devoirs = devoirs;
	}
}