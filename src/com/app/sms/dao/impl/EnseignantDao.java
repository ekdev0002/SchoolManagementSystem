package com.app.sms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.IDao;
import com.app.sms.dao.IUserDao;
import com.app.sms.models.Candidature;
import com.app.sms.models.Classe;
import com.app.sms.models.Enseignant;

public class EnseignantDao extends Dao implements IDao<Enseignant>, IUserDao {
	
	public EnseignantDao() {
		super();
	}

	@Override
	public boolean create(Enseignant enseignant) throws SQLException {

		String query = "Insert Into T_Enseignants (nom, prenom, email, telephone, genre, path, login, password) "
				+ "values (?,?,?,?,?,?,?,?)";
		
		connection.setAutoCommit(false);
		try {
			/* Début Transaction */
	    	PreparedStatement preparedStatement = connection.prepareStatement(query);
	    	
	    	preparedStatement.setString(1, enseignant.getNom());
	    	preparedStatement.setString(2, enseignant.getPrenom());
	    	preparedStatement.setString(3, enseignant.getEmail());
	    	preparedStatement.setString(4, enseignant.getTelephone());
	    	preparedStatement.setString(5, enseignant.getGenre());
	    	preparedStatement.setString(6, enseignant.getPicturePath());
	    	preparedStatement.setString(7, enseignant.getLogin());
	    	preparedStatement.setString(8, enseignant.getPassword());
	    	preparedStatement.execute();
	    	
	    	query = "Select max(id) From T_Enseignants";
	    	preparedStatement = connection.prepareStatement(query);
	    	preparedStatement.executeQuery();
	    	ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	        	int idEnseignant = resultSet.getInt("max(id)");
	    	
		    	List<Integer> idClasses = enseignant.getIdClasses();
		    	if ( ! idClasses.isEmpty() ) {
			    	for (Integer idClasse : idClasses) {
			    		query = "Insert Into T_Enseignants_Classes (IdEnseignant, IdClasse) values (?,?)";
			    		preparedStatement = connection.prepareStatement(query);
			    		preparedStatement.setInt(1, idEnseignant);
			    		preparedStatement.setInt(2, idClasse);
			    		preparedStatement.execute();
			    	}
		    	}
	        }
	        /* Fin Transaction */
	        connection.commit();
	        connection.setAutoCommit(true);
		} catch ( SQLException e) {
			connection.rollback();
			throw new SQLException ( "SQLException in method create : " + e.getMessage() ) ;
		}
        return true;
	}
	
	@Override
	public List<Enseignant> list() throws SQLException {
		List<Enseignant> enseignants = new ArrayList<>();
       
        String query = "Select * From T_Enseignants";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
        	 int idEnseignant = resultSet.getInt("id");
             String nom = resultSet.getString("nom");
             String prenom = resultSet.getString("prenom");
             String genre = resultSet.getString("genre");
             String telephone = resultSet.getString("telephone");
             String email = resultSet.getString("email");
             String login = resultSet.getString("login");
             String password = resultSet.getString("password");
             String path = resultSet.getString("path");
             Enseignant enseignant = new Enseignant (idEnseignant, nom, prenom, genre, telephone, email, login, password);
             enseignant.setPicturePath(path);
             
             query = "Select IdClasse from T_Enseignants_Classes Where IdEnseignant=?";
             prepareStatement = connection.prepareStatement(query);
             prepareStatement.setInt(1, idEnseignant);
             ResultSet result = prepareStatement.executeQuery();
             List<Classe> classes = new ArrayList<>();
             while (result.next()) {
            	 int idClasse = result.getInt("IdClasse") ;
            	 query = "Select * from T_Classes Where Id=?";
            	 prepareStatement = connection.prepareStatement(query);
            	 prepareStatement.setInt(1, idClasse);
            	 ResultSet resultSetClasses = prepareStatement.executeQuery();
            	 if (resultSetClasses.next()) {
            		 Classe classe = new Classe (resultSetClasses.getInt("id"), resultSetClasses.getString("libelle"), resultSetClasses.getString("description")) ;
            		 classes.add(classe);
            	 }            	 
             }
             enseignant.setClasses(classes);
             query = "Select * from T_Candidatures Where IdEns=?";
             prepareStatement = connection.prepareStatement(query);
             prepareStatement.setInt(1, idEnseignant);
             result = prepareStatement.executeQuery();
             List<Candidature> candidatures = new ArrayList<>();
             while (result.next()) {
            	 Candidature candidature = new Candidature (
            			 result.getInt("Id"), 
            			 result.getInt("idEns"),
            			 result.getString("nom"), 
            			 result.getString("prenom"), 
            			 result.getString("email"), 
            			 result.getString("telephone"), 
            			 result.getString("genre"), 
            			 result.getString("path"), 
            			 result.getString("birthday"), 
            			 result.getString("cv"), 
            			 result.getString("state"), 
            			 result.getString("commentaires") );
            	 candidatures.add(candidature);
             }
             enseignant.setCandidatures(candidatures);
             enseignants.add(enseignant);
        }
        return enseignants;
	}

	@Override
	public Enseignant find(int id) throws SQLException {
		
        String query = "Select * From T_Enseignants Where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
        	int idEnseignant = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String genre = resultSet.getString("genre");
            String telephone = resultSet.getString("telephone");
            String email = resultSet.getString("email");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            String path = resultSet.getString("path");
            Enseignant enseignant = new Enseignant (idEnseignant, nom, prenom, genre, telephone, email, login, password);
            enseignant.setPicturePath(path);
            
            query = "Select IdClasse from T_Enseignants_Classes Where IdEnseignant=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idEnseignant);
            ResultSet result = preparedStatement.executeQuery();
            List<Classe> classes = new ArrayList<>();
            while (result.next()) {
            	int idClasse = result.getInt("IdClasse") ;
           		query = "Select * from T_Classes Where Id=?";
           		preparedStatement = connection.prepareStatement(query);
           		preparedStatement.setInt(1, idClasse);
           		ResultSet resultSetClasses = preparedStatement.executeQuery();
           		if (resultSetClasses.next()) {
           			Classe classe = new Classe (resultSetClasses.getInt("id"), resultSetClasses.getString("libelle"), resultSetClasses.getString("description")) ;
           			classes.add(classe);
           		}
            }
            enseignant.setClasses(classes);
            query = "Select * from T_Candidatures Where IdEns=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idEnseignant);
            result = preparedStatement.executeQuery();
            List<Candidature> candidatures = new ArrayList<>();
            while (result.next()) {
           	 Candidature candidature = new Candidature (
           			 result.getInt("Id"),
           			 result.getInt("idEns"),
           			 result.getString("nom"), 
           			 result.getString("prenom"), 
           			 result.getString("email"), 
           			 result.getString("telephone"), 
           			 result.getString("genre"), 
           			 result.getString("path"), 
           			 result.getString("birthday"), 
           			 result.getString("cv"), 
           			 result.getString("state"), 
           			 result.getString("commentaires") );
           	 candidatures.add(candidature);
            }
            enseignant.setCandidatures(candidatures);
            return enseignant;
        }
        return null;
	}

	@Override
	public boolean update(Enseignant enseignant) throws SQLException {
		
        String query = "Update T_Enseignants Set nom = ?, prenom = ?, email = ?, telephone = ?,"
        		+ "login = ?, password = ?, path = ? Where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        
        int idEnseignant = enseignant.getId();
        
        preparedStatement.setString(1, enseignant.getNom());
    	preparedStatement.setString(2, enseignant.getPrenom());
    	preparedStatement.setString(3, enseignant.getEmail());
    	preparedStatement.setString(4, enseignant.getTelephone());	
    	preparedStatement.setString(5, enseignant.getLogin());
    	preparedStatement.setString(6, enseignant.getPassword());
    	preparedStatement.setString(7, enseignant.getPicturePath());
    	preparedStatement.setInt(8, enseignant.getId());

		String queryTest = "Delete From T_Enseignants_Classes Where IdEnseignant = ?";
        
        PreparedStatement preparedStatementTest = connection.prepareStatement(queryTest);
       
        preparedStatementTest.setInt(1, idEnseignant);
            
        preparedStatementTest.execute();
    	
    	List<Integer> idClasses = enseignant.getIdClasses();
    	if ( ! idClasses.isEmpty() ) {
	    	for (Integer idClasse : idClasses) {

	    		query = "Insert Into T_Enseignants_Classes (IdEnseignant, IdClasse) values (?,?)";
	    		preparedStatement = connection.prepareStatement(query);
	    		preparedStatement.setInt(1, idEnseignant);
	    		preparedStatement.setInt(2, idClasse);
	    		preparedStatement.execute();

	    	}
    	} 	
        return true;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		String query = "Delete From T_Enseignants Where id = ?";

        PreparedStatement preparedStatement;
       
    	preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setInt(1, id);
    	preparedStatement.execute();

        return true;
	}
	
	@Override
	public boolean findByLoginPassword (String login, String password) throws SQLException {
		boolean resultat = false;
		
		
        String query = "Select * From T_Enseignants Where login = ? and password = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
       
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
            
        ResultSet resultatSet = preparedStatement.executeQuery();
        
        if (resultatSet.next()) {
            resultat = true;
        }
            
		return resultat;
	}


	public Enseignant findEnseignantByLoginPassword (String login, String password) throws SQLException {
		
		
        String query = "Select * From T_Enseignants Where login = ? and password = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
       
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
            
        ResultSet resultSet = preparedStatement.executeQuery();
  
            
        if (resultSet.next()) {
        	int idEnseignant = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String genre = resultSet.getString("genre");
            String telephone = resultSet.getString("telephone");
            String email = resultSet.getString("email");
            String path = resultSet.getString("path");
            Enseignant enseignant = new Enseignant (idEnseignant, nom, prenom, genre, telephone, email, login, password);
            enseignant.setPicturePath(path);
            
            query = "Select IdClasse from T_Enseignants_Classes Where IdEnseignant=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idEnseignant);
            ResultSet result = preparedStatement.executeQuery();
            List<Classe> classes = new ArrayList<>();
            while (result.next()) {
            	int idClasse = result.getInt("IdClasse") ;
           		query = "Select * from T_Classes Where Id=?";
           		preparedStatement = connection.prepareStatement(query);
           		preparedStatement.setInt(1, idClasse);
           		ResultSet resultSetClasses = preparedStatement.executeQuery();
           		if (resultSetClasses.next()) {
           			Classe classe = new Classe (resultSetClasses.getInt("id"), resultSetClasses.getString("libelle"), resultSetClasses.getString("description")) ;
           			classes.add(classe);
           		}
            }
            enseignant.setClasses(classes);
            query = "Select * from T_Candidatures Where IdEns=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idEnseignant);
            result = preparedStatement.executeQuery();
            List<Candidature> candidatures = new ArrayList<>();
            while (result.next()) {
           	 Candidature candidature = new Candidature (
           			 result.getInt("Id"),
           			 result.getInt("idEns"),
           			 result.getString("nom"), 
           			 result.getString("prenom"), 
           			 result.getString("email"), 
           			 result.getString("telephone"), 
           			 result.getString("genre"), 
           			 result.getString("path"), 
           			 result.getString("birthday"), 
           			 result.getString("cv"), 
           			 result.getString("state"), 
           			 result.getString("commentaires") );
           	 candidatures.add(candidature);
            }
            enseignant.setCandidatures(candidatures);
            return enseignant;
        }
        return null;
	}
	
	//non utilisee
	@Override
	public boolean find(Enseignant enseignant) throws SQLException {
		
        String query = "Select * From T_Enseignants Where nom = ? and prenom = ? and email = ? and telephone = ? and " + 
        		"login = ? and password = ? and path = ? ;";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, enseignant.getNom());
    	preparedStatement.setString(2, enseignant.getPrenom());
    	preparedStatement.setString(3, enseignant.getEmail());
    	preparedStatement.setString(4, enseignant.getTelephone());
    	preparedStatement.setString(5, enseignant.getLogin());
    	preparedStatement.setString(6, enseignant.getPassword());
    	preparedStatement.setString(7, enseignant.getPicturePath());
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
        	return true;
        }
		return false;
	}
}