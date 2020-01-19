package com.app.sms.models;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.app.sms.dao.impl.BulletinDao;
import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;


public class Bulletin {
	private int id;
	private int idEleve;
	private String nom;
	private String prenom;
	private String genre;
	private String date;
	private String decision;
	private Double moyenneGenerale;
	private String effectif;
	private String rang;
	private String classe;
	private String[] nomColumns = {"Matière", "Coef", "Moyenne", "Moyenne Ponderée"};
	private Vector<String[]> rows = new Vector<>();
	
	
	
	
	public Bulletin() {
		super();
	}

	

	public Bulletin(int id, int idEleve, String nom, String prenom, String genre, String date, String decision,
			Double moyenneGenerale, String effectif, String rang, String classe) {
		super();
		this.id = id;
		this.idEleve = idEleve;
		this.nom = nom;
		this.prenom = prenom;
		this.genre = genre;
		this.date = date;
		this.decision = decision;
		this.moyenneGenerale = moyenneGenerale;
		this.effectif = effectif;
		this.rang = rang;
		this.classe = classe;
	}



	public int getIdEleve() {
		return idEleve;
	}

	public void setIdEleve(int idEleve) {
		this.idEleve = idEleve;
	}

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public Double getMoyenneGenerale() {
		return moyenneGenerale;
	}

	public void setMoyenneGenerale(Double moyenneGenerale) {
		this.moyenneGenerale = moyenneGenerale;
	}

	public String getEffectif() {
		return effectif;
	}

	public void setEffectif(String effectif) {
		this.effectif = effectif;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String[] getNomColumns() {
		return nomColumns;
	}

	public void setNomColumns(String[] nomColumns) {
		this.nomColumns = nomColumns;
	}

	public Vector<String[]> getRows() {
		return rows;
	}

	public void setRows(Vector<String[]> rows) {
		this.rows = rows;
	}

	public static void create() throws AlreadyExistDataException, SQLException {
		BulletinDao bulletinDao = new BulletinDao ();	
		bulletinDao.create();	
	}

	public static List<Bulletin> list() throws SQLException {
		// TODO Auto-generated method stub
		BulletinDao bulletinDao = new BulletinDao ();	
		return bulletinDao.list();	
	}

	public String getRang() {
		return rang;
	}

	public void setRang(String rang) {
		this.rang = rang;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}



	public static List<Bulletin> list(int id2) throws SQLException {
		BulletinDao bulletinDao = new BulletinDao ();	
		return bulletinDao.list(id2);	
	}


}