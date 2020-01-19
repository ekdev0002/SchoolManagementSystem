package com.app.sms.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.impl.CoursDao;
import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;

public class Cours {

	private int id;
	private int idModule;
	private int idClasse;	
	private String dateHeure;
	private String description;
	private int duree;
	
	public Cours(int id, int idModule,int idClasse,String dateHeure, String description,int duree) {
		this (idModule,idClasse,dateHeure, description,duree);
		this.id = id;
		this.setIdModule(idModule);
	}
	
	public Cours( int idModule,int idClasse,String dateHeure, String description,int duree) {
		this.dateHeure = dateHeure;
		this.description = description;
		this.duree = duree;
		this.setIdModule(idModule);
		this.idClasse=idClasse;

	}

	public Cours() {}


	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String getDateHeure() {
		return dateHeure;
	}
	
	public void setDateHeure(String dateHeure) {
		this.dateHeure = dateHeure;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void create() throws AlreadyExistDataException, SQLException {
		
		
		CoursDao coursDao = new CoursDao ();
		
		if ( coursDao.find(this) )  {
			System.out.println(this.getDateHeure()+this.getDescription());
			throw new AlreadyExistDataException ( "An error occured : Cours already Exit !" ) ;
		} else {
			
			coursDao.create(this) ;
		}	
		
	}

	public void update() throws NotFoundDataException, SQLException {
		CoursDao coursDao = new CoursDao ();
		
		if (null != coursDao.find(id)) {
			coursDao.update(this);
		} else {
			throw new NotFoundDataException ( "An error occured : la cours n'existe pas !" ) ;
		}
	}

	public void delete() throws NotFoundDataException, SQLException {
		CoursDao coursDao = new CoursDao ();
		
		if (null != coursDao.find(id)) {
			coursDao.delete(id);
		} else {
			throw new NotFoundDataException ( "An error occured : la cours n'existe pas !" ) ;
		}
	}

	public static List<Cours> list() throws SQLException {
		CoursDao coursDao = new CoursDao ();
		return coursDao.list();
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getIdModule() {
		return idModule;
	}

	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}
	
	public String getModule() {
		
		try {
			for ( Module module : Module.list() ) {
				
				if(this.idModule == module.getId() ) 
				{
					return module.getLibelle();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return null;
	}
	
	public String getClasse() {
		try {
			for ( Classe classe : Classe.list() ) {
				
				if(this.idClasse == classe.getId() ) 
				{
					return classe.getLibelle();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public int getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(int idClasse) {
		this.idClasse = idClasse;
	}

	public static List<Cours> list(int id2) throws SQLException {
		CoursDao coursDao = new CoursDao ();
		return coursDao.list(id2);
	}
}
