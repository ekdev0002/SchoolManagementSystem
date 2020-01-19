package com.app.sms.models;

import java.sql.SQLException;
import java.util.List;

import com.app.sms.dao.impl.ModuleDao;
import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.ui.impl.MainUIApplication;

public class Module {
	
	private int id;
	private int coefficient;
	private String libelle;
	private String description;
	
	public Module(int coefficient, String libelle, String description) {
		this (libelle, description);
		this.coefficient = coefficient;
	}
	
	public Module( String libelle, String description) {
		this.libelle = libelle;
		this.description = description;
	}
	
	public Module(int id, int coefficient, String libelle, String description) {
		this(coefficient,libelle,description);
		this.id=id;
	}

	public Module() {}

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

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public void create() throws AlreadyExistDataException, SQLException {
		ModuleDao moduleDao = new ModuleDao ();
		
		if ( moduleDao.find(this) ) {
			throw new AlreadyExistDataException ( "An error occured : Module already Exit !" ) ;
		} else {
			moduleDao.create(this) ;
		}		
	}
	public void update() throws NotFoundDataException, SQLException {
		ModuleDao moduleDao = new ModuleDao ();
		
		if (null != moduleDao.find(id)) {
			moduleDao.update(this);
		} else {
			throw new NotFoundDataException ( "An error occured : le module n'existe pas !" ) ;
		}
	}

	public void delete() throws NotFoundDataException, SQLException {
		ModuleDao moduleDao = new ModuleDao ();
		
		if (null != moduleDao.find(id)) {
			moduleDao.delete(id);
		} else {
			throw new NotFoundDataException ( "An error occured : le module n'existe pas !" ) ;
		}
	}

	public static List<Module> list() throws SQLException {
		ModuleDao moduleDao = new ModuleDao ();
		return moduleDao.list();
	}

	public static List<Module> list(int id2) throws SQLException{
		ModuleDao moduleDao = new ModuleDao ();
		return moduleDao.list(MainUIApplication.getCurrentUser().getId());
	}

	public static void setEnseignantClasse(int parseInt, int parseInt2, int id2) throws SQLException {
		// TODO Auto-generated method stub
		ModuleDao moduleDao = new ModuleDao ();
		 moduleDao.setEnseignantClasse(parseInt, parseInt2, id2);
		
	}
}
