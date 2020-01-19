package com.app.sms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.IClasseDao;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Classe;

public class ClasseDao extends Dao implements IClasseDao<Classe>  {
	
	public ClasseDao() {
		super();
	}
	
	@Override
	public boolean create(Classe classe) throws SQLException {

		String query = "Insert Into T_Classes (libelle,description) values (?,?)";

    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	
    	preparedStatement.setString(1,classe.getLibelle());
    	preparedStatement.setString(2, classe.getDescription());
    	
    	preparedStatement.execute();

        return true;
	}

	@Override
	public List<Classe> list() throws SQLException {
		List<Classe> classes = new ArrayList<>();
      
        String query = "Select * " + "From T_Classes ";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String libelle = resultSet.getString("libelle");
            String description = resultSet.getString("description"); 
            
			Classe classe = new Classe (id, libelle, description);
            classes.add(classe);
        }
     
        return classes;
	}

	@Override
	public Classe find(int id) throws SQLException {
	
	    String query = "Select * From T_Classes Where id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, id);
	    ResultSet resultatSet = preparedStatement.executeQuery();
	    if (resultatSet.next()) {
	        return new Classe (id, resultatSet.getString("libelle"), resultatSet.getString("description"));
	    }

		return null;
	}

	@Override
	public boolean update(Classe classe) throws SQLException {

        String query = "Update T_Classes Set libelle = ?, description = ? Where id = ?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        
        prepareStatement.setString(1, classe.getLibelle());
        prepareStatement.setString(2, classe.getDescription());
        prepareStatement.setInt(3, classe.getId());
       
        prepareStatement.execute();
        
        return true;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		String query = "Delete From T_Classes Where id = ?";

        PreparedStatement preparedStatement;
       
    	preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setInt(1, id);
    	preparedStatement.execute();

        return true;
	}

	public boolean find(Classe classe) throws SQLException {
		boolean resultat = false;
		
        String query = "Select * From T_Classes Where libelle = ? and description = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, classe.getLibelle());
        preparedStatement.setString(2, classe.getDescription());
        
        ResultSet resultatSet = preparedStatement.executeQuery();
        
        if (resultatSet.next()) {
            resultat = true;
        }
		return resultat;
	}

	public int findIdClasseByLibelle(String libelleClasse) throws NotFoundDataException, SQLException {
		String query = "Select * From T_Classes Where libelle = ?";
	        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, libelleClasse);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        } else {
        	throw new NotFoundDataException ("Error : Situation anormale classe introuvable ...");
        }
	}

	public List<Classe> list(int id2) throws SQLException {
		List<Classe> classes = new ArrayList<>();
	      
        String query = "select * from t_classes where id in (select idClasse from t_enseignants_classes where idEnseignant=?);";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setInt(1, id2);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String libelle = resultSet.getString("libelle");
            String description = resultSet.getString("description"); 
            
			Classe classe = new Classe (id, libelle, description);
            classes.add(classe);
        }
     
        return classes;

	}
}
