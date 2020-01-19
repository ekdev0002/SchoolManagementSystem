package com.app.sms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.IDao;
import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Candidature;
import com.app.sms.models.Diplome;
import com.app.sms.utils.Utilitaire;

public class CandidatureDao extends Dao implements IDao<Candidature> {
	
	public CandidatureDao() {
		super();
	}
	
	@Override
	public boolean create(Candidature candidature) throws SQLException, AlreadyExistDataException {
		
		String query = "Insert Into T_Candidatures (nom, prenom, email, telephone, genre, path, birthday, cv, state) "
				+ "values (?,?,?,?,?,?,?,?,?)";
		
		connection.setAutoCommit(false);
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
	    	
	    	preparedStatement.setString(1, candidature.getNom());
	    	preparedStatement.setString(2, candidature.getPrenom());
	    	preparedStatement.setString(3, candidature.getEmail());
	    	preparedStatement.setString(4, candidature.getTelephone());
	    	preparedStatement.setString(5, candidature.getGenre());
	    	preparedStatement.setString(6, candidature.getPicturePath());
	    	preparedStatement.setString(7, candidature.getBirthday());
	    	preparedStatement.setString(8, candidature.getCV());
	    	preparedStatement.setString(9, candidature.getState());
	    	List<Diplome> diplomes = candidature.getDiplomes();
	    	preparedStatement.execute();
	    
	    	query = "Select max(id) From T_Candidatures";
	    	preparedStatement = connection.prepareStatement(query);
	    	preparedStatement.executeQuery();
	    	ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	        	int idCandidature = resultSet.getInt("max(id)");
		    	for ( Diplome diplome : diplomes) {
		    		diplome.setIdCandidature (idCandidature);
		    		Utilitaire.ajouterDiplome (diplome);
		    	}
		    	
		    	if (candidature.getIdEnseignant() != -1) {
		    		query = "Update T_Candidatures set idEns = ? Where id = ?";
		    		preparedStatement = connection.prepareStatement(query);
		    		preparedStatement.setInt(1, candidature.getIdEnseignant());
			    	preparedStatement.setInt(2, idCandidature);
			    	preparedStatement.execute();
		    	}
	        }
	        connection.commit();
        } catch (SQLException e) {
        	connection.rollback();
        	throw new SQLException (e.getMessage()) ;
        } catch (AlreadyExistDataException e) {
        	connection.rollback();
        	throw new AlreadyExistDataException (e.getMessage()) ;
        }    	
        return true;
	}
	
	@Override
	public List<Candidature> list() throws SQLException {
		List<Candidature> candidatures = new ArrayList<>();
       
        String query = "SELECT * From T_Candidatures" ;
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
        	 int id = resultSet.getInt("id");
        	 int idEns = resultSet.getInt("idEns");
             String nom = resultSet.getString("nom");
             String prenom = resultSet.getString("prenom"); 
             String genre = resultSet.getString("genre");
             String telephone = resultSet.getString("telephone");
             String email = resultSet.getString("email");
             String birthday = resultSet.getString("birthday");
             String path = resultSet.getString("path");
             String cv = resultSet.getString("cv");
             String state = resultSet.getString("state");
             String commentaires = resultSet.getString("commentaires");
             Candidature candidature = new Candidature (id,idEns, nom, prenom, email, telephone, genre, path, birthday, cv, state, commentaires );
             candidatures.add(candidature);
        }
        return candidatures;
	}

	@Override
	public Candidature find(int id) throws SQLException {
		
        String query = "Select * From T_Candidatures Where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
        	int idEns = resultSet.getInt("idEns");
             String nom = resultSet.getString("nom");
             String prenom = resultSet.getString("prenom"); 
             String genre = resultSet.getString("genre");
             String telephone = resultSet.getString("telephone");
             String email = resultSet.getString("email");
             String birthday = resultSet.getString("birthday");
             String commentaires = resultSet.getString("commentaires");
             
             return new Candidature (id,idEns, nom, prenom, genre, telephone, email, birthday, commentaires);
        }
        return null;
	}

	@Override
	public boolean update(Candidature candidature) throws SQLException, NotFoundDataException {
        String query = "Update T_Candidatures Set nom = ?, prenom = ?, email = ?, telephone = ?,"
        		+ " commentaires = ?, state = ?, idEns = ? Where id = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, candidature.getNom());
    	preparedStatement.setString(2, candidature.getPrenom());
    	preparedStatement.setString(3, candidature.getEmail());
    	preparedStatement.setString(4, candidature.getTelephone());
    	preparedStatement.setString(5, candidature.getCommentaires());
    	preparedStatement.setString(6, candidature.getState());
    	preparedStatement.setInt(7, candidature.getIdEnseignant());
    	preparedStatement.setInt(8, candidature.getId());
    	
        preparedStatement.execute();
        return true;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		connection.setAutoCommit(false);
		PreparedStatement preparedStatement;
		/* 
		 * Supprimer tous les diplomes du candidats */
		String query = "Delete From T_Diplomes Where IdCandidature = ?";
		preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setInt(1, id);
    	preparedStatement.execute();
    	
		query = "Delete From T_Candidatures Where id = ?";
		preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setInt(1, id);
    	preparedStatement.execute();
    	
		connection.commit();
        connection.setAutoCommit(true);
        
        return true;
	}
	
	@Override
	public boolean find(Candidature candidature) throws SQLException {
		
        String query = "Select * From T_Candidatures Where nom = ? and prenom = ? and email = ? and telephone = ? and " + 
        		"birthday = ? and commentaires = ? ;";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, candidature.getNom());
    	preparedStatement.setString(2, candidature.getPrenom());
    	preparedStatement.setString(3, candidature.getEmail());
    	preparedStatement.setString(4, candidature.getTelephone());
    	preparedStatement.setString(5, candidature.getBirthday());
    	preparedStatement.setString(6, candidature.getCommentaires());
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
        	return true;
        }
		return false;
	}

	public List<Candidature> list(int selectedEnseignantId) throws SQLException {
		List<Candidature> candidatures = new ArrayList<>();
	       
        String query = "SELECT * From T_Candidatures Where IdEns = ?" ;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, selectedEnseignantId);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
        	 int id = resultSet.getInt("id");
        	 int idEns = resultSet.getInt("idEns");
             String nom = resultSet.getString("nom");
             String prenom = resultSet.getString("prenom"); 
             String genre = resultSet.getString("genre");
             String telephone = resultSet.getString("telephone");
             String email = resultSet.getString("email");
             String birthday = resultSet.getString("birthday");
             String path = resultSet.getString("path");
             String state = resultSet.getString("state");
             String commentaires = resultSet.getString("commentaires");
             Candidature candidature = new Candidature (id,idEns, nom, prenom, genre, telephone, email, birthday, commentaires );
             candidature.setPicturePath(path);
             candidature.setState(state);
             candidatures.add(candidature);
        }
       
        return candidatures;
	}
}