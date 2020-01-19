package com.app.sms.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.impl.CandidatureDao;
import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.utils.Utilitaire;
/**
 * @author a459079
 *
 */
public class Candidature {

	private int id;
	private int idEnseignant;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	private String genre;
	private String birthday ;
	private String picturePath;
	private String cv=null;
	private List<Diplome> diplomes;
	private String state;
	private String commentaires=null;
	
	public static final String REJETEE = "Rejetée" ;
	public static final String RETENUE = "Retenue" ;
	public static final String EN_COURS = "Analyse en cours" ;
	public static final String CLOSE = "Clôturée" ;
	public static final String OUVERTE = "Ouverte - Non encore affectée" ;
	
	public Candidature(int id,int idEns, String nom, String prenom, String email, String telephone,
			String genre, String path, String birthday, String cv, String state, String commentaires) {
		this(id,idEns, nom, prenom, email, telephone, genre, path, birthday);
		this.commentaires = commentaires;
		this.cv = cv;
		this.state = state;
		
	}
	
	public Candidature(int id,int idEns, String nom, String prenom, String email, String telephone, String genre, String path, String birthday) {
		this(id,idEns, nom, prenom, email, telephone, genre );
		this.picturePath = path;
		this.birthday = birthday;
		this.idEnseignant=idEns;
	}

	public Candidature() {
		diplomes = new ArrayList<>();
		state = OUVERTE;
	}

	public Candidature(int id, int idEns,String nom, String prenom, String email, String telephone, String genre) {
		this();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.genre = genre;
		this.telephone = telephone;
		this.email = email;
		this.idEnseignant=idEns;
	}

	public void setId(String id) {
		this.id = Integer.valueOf(id);
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCV() {
		return cv;
	}

	public List<Diplome> getDiplomes() {
		return diplomes;
	}
	
	public void addDiplome (Diplome diplome) {
		diplomes.add(diplome);
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public void setDiplomes(List<String> urls) {
		for (String url : urls) {
			this.diplomes.add( new Diplome (-1, this.id, url));
		}		
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}

	/**
	 * @return the idEnseignant
	 */
	public int getIdEnseignant() { 
		return idEnseignant;
	}

	/**
	 * @param idEnseignant the idEnseignant to set
	 */
	public void setIdEnseignant(int idEnseignant) {
		this.idEnseignant = idEnseignant;
		/*if ( idEnseignant != -1 ) {
			setState(EN_COURS);
		}*/
	}

	public String getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

	public void create() throws AlreadyExistDataException, SQLException {
		CandidatureDao candidatureDao = new CandidatureDao ();
		
		if ( candidatureDao.find(this) ) {
			throw new AlreadyExistDataException ( "An error occured : Candidature already Exit !" ) ;
		} else {
			candidatureDao.create(this) ;
		}
	}
	
	public static List<Candidature> list() throws SQLException {
		CandidatureDao candidatureDao = new CandidatureDao ();
		return candidatureDao.list();
	}


	public static List<Candidature> list(int selectedIdEnseignant) throws SQLException {
		CandidatureDao candidatureDao = new CandidatureDao ();
		return candidatureDao.list(selectedIdEnseignant);
	}
	
	
	public void update() throws NotFoundDataException, SQLException {
		CandidatureDao candidatureDao = new CandidatureDao ();
		
		if (null != candidatureDao.find(id)) {
			candidatureDao.update(this);
		} else {
			throw new NotFoundDataException ( "An error occured : la candidature n'existe pas !" ) ;
		}
	}

	public void delete() throws NotFoundDataException, SQLException {
		CandidatureDao candidatureDao = new CandidatureDao ();
		
		if (null != candidatureDao.find(id)) {
			candidatureDao.delete(id);
		} else {
			throw new NotFoundDataException ( "An error occured : la candidature n'existe pas !" ) ;
		}
	}

	public String getEnseignant() {
		
			try {
				for ( Enseignant enseignant : Enseignant.list() ) {
					
					if(this.idEnseignant == enseignant.getId() ) 
					{
						return enseignant.getNom()+" "+enseignant.getPrenom();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
	}
}