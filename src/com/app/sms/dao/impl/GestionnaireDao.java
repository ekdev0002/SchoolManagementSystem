package com.app.sms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.IDao;
import com.app.sms.dao.IUserDao;
import com.app.sms.models.Gestionnaire;

public class GestionnaireDao extends Dao implements IDao<Gestionnaire>, IUserDao {
	
	public GestionnaireDao() {
		super();
	}
	
	@Override
	public boolean create(Gestionnaire gestionnaire) throws SQLException {

		String query = "Insert Into T_Gestionnaires (nom, prenom, adresse, date_naissance, telephone, email, login, password) "
				+ "values (?,?,?,?,?,?,?,?)";
		
        	PreparedStatement preparedStatement = connection.prepareStatement(query);
        	
        	preparedStatement.setString(1, gestionnaire.getNom());
        	preparedStatement.setString(2, gestionnaire.getPrenom());
        	preparedStatement.setString(3, gestionnaire.getAdresse());
        	preparedStatement.setString(4, gestionnaire.getDateNaissance());
        	preparedStatement.setString(5, gestionnaire.getTelephone());
        	preparedStatement.setString(6, gestionnaire.getEmail());
        	preparedStatement.setString(7, gestionnaire.getLogin());
        	preparedStatement.setString(8, gestionnaire.getPassword());
        	
        	preparedStatement.execute();
        	
 
        return true;
	}
	
	@Override
	public List<Gestionnaire> list() {
		List<Gestionnaire> gestionnaires = new ArrayList<>();
        try {
            String query = "Select * " + "From T_Gestionnaires ";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                int idGestionnaire = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String adresse = resultSet.getString("adresse");
                String dateDeNaissance = resultSet.getString("date_naissance");
                String telephone = resultSet.getString("telephone");
                String email = resultSet.getString("email");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                Gestionnaire gestionnaire = new Gestionnaire(idGestionnaire, nom, prenom, adresse, dateDeNaissance, telephone, email, login, password);
                gestionnaires.add(gestionnaire);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return gestionnaires;
	}

	@Override
	public Gestionnaire find(int id) {
		try {
            String query = "Select * From T_Gestionnaires Where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultatSet = preparedStatement.executeQuery();
            if (resultatSet.next()) {
            	int idGestionnaire = resultatSet.getInt("id");
                String nom = resultatSet.getString("nom");
                String prenom = resultatSet.getString("prenom");
                String adresse = resultatSet.getString("adresse");
                String dateDeNaissance = resultatSet.getString("date_naissance");
                String telephone = resultatSet.getString("telephone");
                String email = resultatSet.getString("email");
                String login = resultatSet.getString("login");
                String password = resultatSet.getString("password");
                Gestionnaire gestionnaire = new Gestionnaire(idGestionnaire, nom, prenom, adresse, dateDeNaissance, telephone, email, login, password);
                return gestionnaire;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
		
		return null;
	}

	@Override
	public boolean update(Gestionnaire gestionnaire) {
		try {
            String query = "Update T_Gestionnaires Set nom = ?, prenom = ?, adresse = ?, date_naissance = ?,"
            		+ "telephone = ?, email = ? Where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, gestionnaire.getNom());
        	preparedStatement.setString(2, gestionnaire.getPrenom());
        	preparedStatement.setString(3, gestionnaire.getAdresse());
        	preparedStatement.setString(4, gestionnaire.getDateNaissance());
        	preparedStatement.setString(5, gestionnaire.getTelephone());
        	preparedStatement.setString(6, gestionnaire.getEmail());
        	preparedStatement.setString(7, gestionnaire.getLogin());
        	preparedStatement.setString(8, gestionnaire.getPassword());
           
            preparedStatement.execute();
            
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		String query = "Delete From T_Gestionnaires Where id = ?";

        PreparedStatement preparedStatement;
        try {

        	preparedStatement = connection.prepareStatement(query);
        	preparedStatement.setInt(1, id);
        	preparedStatement.execute();

            return true;

        } catch (SQLException e) {
        	 System.err.println(e.getMessage());
        }

        return false;
	}
	
	@Override
	public boolean findByLoginPassword (String login, String password) throws SQLException {
		boolean resultat = false;

        String query = "Select * From T_Gestionnaires Where login = ? and password = ?";
        
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
	public boolean find(Gestionnaire obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}