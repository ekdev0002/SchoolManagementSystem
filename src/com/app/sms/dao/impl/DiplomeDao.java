package com.app.sms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.IDiplomeDao;
import com.app.sms.models.Diplome;

public class DiplomeDao extends Dao implements IDiplomeDao<Diplome>  {
	
	public DiplomeDao() {
		super();
	}
	
	@Override
	public boolean create(Diplome diplome) throws SQLException {
		String query = null;
		try {
			query = "Insert Into T_Diplomes (url,idCandidature) values (?,?)";
	
	    	PreparedStatement preparedStatement = connection.prepareStatement(query);
	    	
	    	preparedStatement.setString(1,diplome.getUrl());
	    	preparedStatement.setInt(2,diplome.getIdCandidature());
    	
			preparedStatement.execute();
		} catch (SQLException e) {
			System.err.println( "An error occured : " + query + " " + diplome.getUrl() + " " + diplome.getIdCandidature());
			throw new SQLException (e.getMessage());
		}

        return true;
	}

	@Override
	public List<Diplome> list() throws SQLException {
		List<Diplome> diplomes = new ArrayList<>();
      
        String query = "Select * " + "From T_Diplomes ";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int idCandidature = resultSet.getInt("idCandidature");
            String url = resultSet.getString("url"); 
            
			Diplome diplome = new Diplome (id, idCandidature, url);
            diplomes.add(diplome);
        }
     
        return diplomes;
	}

	@Override
	public Diplome find(int id) throws SQLException {
	
	    String query = "Select * From T_Diplomes Where id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, id);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (resultSet.next()) {
	        return new Diplome (id, resultSet.getInt("idCandidature"), resultSet.getString("url"));
	    }

		return null;
	}

	@Override
	public boolean update(Diplome diplome) throws SQLException {

        String query = "Update T_Diplomes Set url = ? Where id = ?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        
        prepareStatement.setString(1, diplome.getUrl());
        prepareStatement.setInt(2, diplome.getId());
       
        prepareStatement.execute();
        
        return true;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		String query = "Delete From T_Diplomes Where id = ?";

        PreparedStatement preparedStatement;
       
    	preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setInt(1, id);
    	preparedStatement.execute();

        return true;
	}

	public boolean find(Diplome diplome) throws SQLException {
		boolean resultat = false;
		
        String query = "Select * From T_Diplomes Where url = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, diplome.getUrl());
        
        ResultSet resultatSet = preparedStatement.executeQuery();
        
        if (resultatSet.next()) {
            resultat = true;
        }
		return resultat;
	}
}
