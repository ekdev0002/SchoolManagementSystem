package com.app.sms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.IDao;
import com.app.sms.dao.IUserDao;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Eleve;
import com.app.sms.models.User;
import com.app.sms.utils.Utilitaire;

public class EleveDao extends Dao implements IDao<Eleve>, IUserDao {
	
	public EleveDao() {
		super();
	}
	
	@Override
	public boolean create(Eleve eleve) throws SQLException, NotFoundDataException {

		String query = "Insert Into T_Eleves (nom, prenom, email, telephone, genre, path, login, password, idClasse) "
				+ "values (?,?,?,?,?,?,?,?,?)";
		
    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	
    	preparedStatement.setString(1, eleve.getNom());
    	preparedStatement.setString(2, eleve.getPrenom());
    	preparedStatement.setString(3, eleve.getEmail());
    	preparedStatement.setString(4, eleve.getTelephone());
    	preparedStatement.setString(5, eleve.getGenre());
    	preparedStatement.setString(6, eleve.getPicturePath());
    	preparedStatement.setString(7, eleve.getLogin());
    	preparedStatement.setString(8, eleve.getPassword());
    	preparedStatement.setInt(9, eleve.getIdClasse());
    	preparedStatement.execute();

        return true;
	}
	
	@Override
	public List<Eleve> list() throws SQLException {
		List<Eleve> eleves = new ArrayList<>();
       
        String query = "Select * From T_Eleves e JOIN T_Classes c On e.idClasse = c.id;";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
        	 int id = resultSet.getInt("id");
             String nom = resultSet.getString("nom");
             String prenom = resultSet.getString("prenom"); 
             String genre = resultSet.getString("genre");
             String telephone = resultSet.getString("telephone");
             String email = resultSet.getString("email");
             String login = resultSet.getString("login");
             String password = resultSet.getString("password");
             String path = resultSet.getString("path");
             String libelleClasse = resultSet.getString("libelle");
             String idClasse = resultSet.getString("IdClasse");
             Eleve eleve = new Eleve (id, nom, prenom, genre, telephone, email, login, password);
             eleve.setPicturePath(path);
             eleve.setLibelleClasse(libelleClasse);
             eleve.setIdClasse(Integer.parseInt(idClasse));
             eleves.add(eleve);
        }
       
        return eleves;
	}

	@Override
	public Eleve find(int id) throws SQLException {
		
        String query = "Select * From T_Eleves Where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
             String nom = resultSet.getString("nom");
             String prenom = resultSet.getString("prenom"); 
             String genre = resultSet.getString("genre");
             String telephone = resultSet.getString("telephone");
             String email = resultSet.getString("email");
             String login = resultSet.getString("login");
             String password = resultSet.getString("password");
             
             return new Eleve (id, nom, prenom, genre, telephone, email, login, password);
        }
        return null;
	}

	@Override
	public boolean update(Eleve eleve) throws SQLException, NotFoundDataException {
		
        String query = "Update T_Eleves Set nom = ?, prenom = ?, email = ?, telephone = ?,"
        		+ "login = ?, password = ?, idClasse = ?, path = ? Where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        
        preparedStatement.setString(1, eleve.getNom());
    	preparedStatement.setString(2, eleve.getPrenom());
    	preparedStatement.setString(3, eleve.getEmail());
    	preparedStatement.setString(4, eleve.getTelephone());        	
    	preparedStatement.setString(5, eleve.getLogin());
    	preparedStatement.setString(6, eleve.getPassword());
    	preparedStatement.setInt(7, Utilitaire.findIdClasseByLibelle(eleve.getLibelleClasse()));
    	preparedStatement.setString(8, eleve.getPicturePath());
    	preparedStatement.setInt(9, eleve.getId());

        preparedStatement.execute();
        
        return true;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		String query = "Delete From T_Eleves Where id = ?";

        PreparedStatement preparedStatement;
       
    	preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setInt(1, id);
    	preparedStatement.execute();

        return true;
	}
	
	@Override
	public boolean findByLoginPassword (String login, String password) throws SQLException {
		boolean resultat = false;
		
		
        String query = "Select * From T_Eleves Where login = ? and password = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
       
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
            
        ResultSet resultatSet = preparedStatement.executeQuery();
        
        if (resultatSet.next()) {
            resultat = true;
        }
            
		return resultat;
	}
	
	@Override
	public boolean find(Eleve eleve) throws SQLException {
		
        String query = "Select * From T_Eleves Where nom = ? and prenom = ? and email = ? and telephone = ? and " + 
        		"login = ? and password = ? and path = ? ;";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, eleve.getNom());
    	preparedStatement.setString(2, eleve.getPrenom());
    	preparedStatement.setString(3, eleve.getEmail());
    	preparedStatement.setString(4, eleve.getTelephone());
    	preparedStatement.setString(5, eleve.getLogin());
    	preparedStatement.setString(6, eleve.getPassword());
    	preparedStatement.setString(7, eleve.getPicturePath());
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
        	return true;
        }
		return false;
	}

	public List<Eleve> list(String idClasse) throws SQLException  {
		List<Eleve> eleves = new ArrayList<>();
		   
        String query = "Select * From T_Eleves where idClasse = ?;";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setString(1, idClasse);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
        	 int id = resultSet.getInt("id");
             String nom = resultSet.getString("nom");
             String prenom = resultSet.getString("prenom"); 
             String genre = resultSet.getString("genre");
             String telephone = resultSet.getString("telephone");
             String email = resultSet.getString("email");
             String login = resultSet.getString("login");
             String password = resultSet.getString("password");
             String path = resultSet.getString("path");
             String idClass = resultSet.getString("IdClasse");
             Eleve eleve = new Eleve (id, nom, prenom, genre, telephone, email, login, password);
             eleve.setPicturePath(path);
             eleve.setIdClasse(Integer.parseInt(idClasse));
             eleves.add(eleve);

        }
       
        return eleves;
	}

	public List<Eleve> list(int id) throws SQLException  {
		List<Eleve> eleves = new ArrayList<>();
		   
        String query = "select * from t_eleves where idClasse in (select id from t_classes where id in (select idClasse from t_enseignants_classes where idEnseignant=?));";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setInt(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
        	 int id2 = resultSet.getInt("id");
             String nom = resultSet.getString("nom");
             String prenom = resultSet.getString("prenom"); 
             String genre = resultSet.getString("genre");
             String telephone = resultSet.getString("telephone");
             String email = resultSet.getString("email");
             String login = resultSet.getString("login");
             String password = resultSet.getString("password");
             String path = resultSet.getString("path");
             String idClasse = resultSet.getString("IdClasse");
             Eleve eleve = new Eleve (id2, nom, prenom, genre, telephone, email, login, password);
             eleve.setPicturePath(path);
             eleve.setIdClasse(Integer.parseInt(idClasse));
             eleves.add(eleve);

        }
       
        return eleves;
	}

	public Eleve findElevetByLoginPassword(String login, String password) throws SQLException {
	       String query = "Select * From T_Eleves Where login = ? and password = ?";
	        
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	       
	        preparedStatement.setString(1, login);
	        preparedStatement.setString(2, password);
	            
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	        	 int id = resultSet.getInt("id");
	             String nom = resultSet.getString("nom");
	             String prenom = resultSet.getString("prenom"); 
	             String genre = resultSet.getString("genre");
	             String telephone = resultSet.getString("telephone");
	             String email = resultSet.getString("email");
	             
	             return new Eleve (id, nom, prenom, genre, telephone, email, login, password);
	        }
	        return null;
	 
	}
}