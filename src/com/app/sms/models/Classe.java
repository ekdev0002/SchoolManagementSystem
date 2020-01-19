package com.app.sms.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.impl.ClasseDao;
import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;

public class Classe {

	private int id;
	private String libelle;
	private String description;
	List<Enseignant> enseignants;
	List<Eleve> eleves;
	
	public Classe(int id, String libelle, String description) {
		this (libelle, description);
		this.id = id;
	}
	
	public Classe( String libelle, String description) {
		this.libelle = libelle;
		this.description = description;
		enseignants = new ArrayList<>();
		eleves = new ArrayList<>();
	}

	public Classe() {}


	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void addEnseignant (Enseignant enseignant) {
		enseignants.add(enseignant);
	}

	public void create() throws AlreadyExistDataException, SQLException {
		ClasseDao classeDao = new ClasseDao ();
		
		if ( classeDao.find(this) ) {
			throw new AlreadyExistDataException ( "An error occured : Classe already Exit !" ) ;
		} else {
			classeDao.create(this) ;
		}		
	}

	public void update() throws NotFoundDataException, SQLException {
		ClasseDao classeDao = new ClasseDao ();
		
		if (null != classeDao.find(id)) {
			classeDao.update(this);
		} else {
			throw new NotFoundDataException ( "An error occured : la classe n'existe pas !" ) ;
		}
	}

	public void delete() throws NotFoundDataException, SQLException {
		ClasseDao classeDao = new ClasseDao ();
		
		if (null != classeDao.find(id)) {
			classeDao.delete(id);
		} else {
			throw new NotFoundDataException ( "An error occured : la classe n'existe pas !" ) ;
		}
	}

	public static List<Classe> list() throws SQLException {
		ClasseDao classeDao = new ClasseDao ();
		return classeDao.list();
	}

	public static List<Classe> list(int id2)throws SQLException {
		// TODO Auto-generated method stub
		ClasseDao classeDao = new ClasseDao ();
		return classeDao.list(id2);
	}
}
