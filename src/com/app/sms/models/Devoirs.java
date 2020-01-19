package com.app.sms.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.impl.DevoirsDao;
import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;

public class Devoirs {

	private int id;
	private int idModule;
	private int idClasse;
	private String statut;
	private String dateHeure;
	private String description;
	private int duree;
	private int coef;
	
	public Devoirs(int id, int idModule,int idClasse,String statut,String dateHeure, String description,int duree,int coef) {
		this (idModule,idClasse,statut,dateHeure, description,duree,coef);
		this.id = id;
	}
	
	public Devoirs( int idModule,int idClasse,String statut,String dateHeure, String description,int duree,int coef) {
		this.dateHeure = dateHeure;
		this.description = description;
		this.duree = duree;
		this.setIdModule(idModule);
		this.statut = statut;
		this.setIdClasse(idClasse);
		this.setCoef(coef);

	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Devoirs() {}


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
		
		
		DevoirsDao devoirsDao = new DevoirsDao ();
		
		if ( devoirsDao.find(this) )  {
			System.out.println(this.getDateHeure()+this.getDescription());
			throw new AlreadyExistDataException ( "An error occured : Devoirs already Exit !" ) ;
		} else {
			
			devoirsDao.create(this) ;
		}	
		
	}

	public void update() throws NotFoundDataException, SQLException {
		DevoirsDao devoirsDao = new DevoirsDao ();
		
		if (null != devoirsDao.find(id)) {
			devoirsDao.update(this);
		} else {
			throw new NotFoundDataException ( "An error occured : la devoirs n'existe pas !" ) ;
		}
	}

	public void delete() throws NotFoundDataException, SQLException {
		DevoirsDao devoirsDao = new DevoirsDao ();
		
		if (null != devoirsDao.find(id)) {
			devoirsDao.delete(id);
		} else {
			throw new NotFoundDataException ( "An error occured : la devoirs n'existe pas !" ) ;
		}
	}

	public static List<Devoirs> list() throws SQLException {
		DevoirsDao devoirsDao = new DevoirsDao ();
		return devoirsDao.list();
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

	public int getCoef() {
		return coef;
	}

	public void setCoef(int coef) {
		this.coef = coef;
	}

	public static List<Devoirs> list(int id2) throws SQLException {
		DevoirsDao devoirsDao = new DevoirsDao ();
		return devoirsDao.list(id2);
	}
}
