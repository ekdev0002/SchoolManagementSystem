package com.app.sms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.ICoursDao;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Cours;
import com.app.sms.ui.impl.MainUIApplication;

public class CoursDao extends Dao implements ICoursDao<Cours>  {
	
	public CoursDao() {
		super();
	}
	
	@Override
	public boolean create(Cours cours) throws SQLException {

		String query = "Insert Into T_Cours (idModule,idClasse,dateHeure,description,duree) values (?,?,?,?,?)";

    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setInt(1,cours.getIdModule());
    	preparedStatement.setInt(2,cours.getIdClasse());
    	preparedStatement.setString(3, cours.getDateHeure());    	
    	preparedStatement.setString(4,cours.getDescription());
    	preparedStatement.setInt(5, cours.getDuree());
    	
    	preparedStatement.execute();
    	
		String query1 = "Select id From t_Cours Where idModule = ? and dateHeure = ?";

    	PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
    	preparedStatement1.setInt(1,cours.getIdModule());
    	preparedStatement1.setString(2,cours.getDateHeure());
    	
    	ResultSet result=preparedStatement1.executeQuery();
    	while (result.next()) {
    		cours.setId(result.getInt("Id"));
    	}
    	
    	
		String query2 = "Insert Into T_Enseignants_Cours (idEnseignant,idCours) values (?,?)";

    	PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
    	preparedStatement2.setInt(1,MainUIApplication.getCurrentUser().getId());
    	preparedStatement2.setInt(2,cours.getId());
    	
    	preparedStatement2.execute();

        return true;
	}

	@Override
	public List<Cours> list() throws SQLException {
		List<Cours> cours = new ArrayList<>();
      
        String query =  "select * from T_Cours where Id in (select IdCours from t_enseignants_cours where idEnseignant=?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,MainUIApplication.getCurrentUser().getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int idModule = resultSet.getInt("idModule");
            int idClasse = resultSet.getInt("idClasse");
            String dateHeure = resultSet.getString("dateHeure");
            String description = resultSet.getString("description");
            int duree = resultSet.getInt("duree");
            
			Cours cour = new Cours (id,idModule,idClasse, dateHeure, description,duree);
            cours.add(cour);
        }
     
        return cours;
	}

	@Override
	public Cours find(int id) throws SQLException {
	
	    String query = "Select * From T_Cours Where id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, id);
	    ResultSet resultatSet = preparedStatement.executeQuery();
	    if (resultatSet.next()) {
	        return new Cours (id, resultatSet.getInt("idModule"),resultatSet.getInt("idClasse"),resultatSet.getString("dateHeure"), resultatSet.getString("description"), resultatSet.getInt("duree"));
	    }

		return null;
	}

	@Override
	public boolean update(Cours cours) throws SQLException {

        String query = "Update T_Cours Set idModule = ?,idClasse = ?, dateHeure = ?, description = ? , duree = ? Where id = ?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setInt(1, cours.getIdModule()); 
        prepareStatement.setInt(2,cours.getIdClasse());
        prepareStatement.setString (3, cours.getDateHeure());
        prepareStatement.setString(4, cours.getDescription());
        prepareStatement.setInt(5, cours.getDuree());
        prepareStatement.setInt(6, cours.getId());        
       
        prepareStatement.execute();
        
        return true;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		String query = "Delete From T_Cours Where id = ?";

        PreparedStatement preparedStatement;
       
    	preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setInt(1, id);
    	preparedStatement.execute();

        return true;
	}

	public boolean find(Cours cours) throws SQLException {
		boolean resultat = false;
		
        String query = "Select * From T_Cours Where idModule = ? and idClasse = ? and dateHeure = ? and description = ? and duree = ?";

  
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, cours.getIdModule());
        preparedStatement.setInt(2,cours.getIdClasse());
        preparedStatement.setString(3, cours.getDateHeure());
        preparedStatement.setString(4, cours.getDescription());
        preparedStatement.setInt(5, cours.getDuree());
        ResultSet resultatSet = preparedStatement.executeQuery();
        
        if (resultatSet.next()) {
            resultat = true;
        }
         
		return resultat;
	}

	public int findIdCoursByDateHeure(String dateHeureCours) throws NotFoundDataException, SQLException {
		String query = "Select * From T_Cours Where dateHeure = ?";
	        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, dateHeureCours);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        } else {
        	throw new NotFoundDataException ("Error : Situation anormale cours introuvable ...");
        }
	}

	public List<Cours> list(int idEleve)throws SQLException {
		List<Cours> cours = new ArrayList<>();
        String query =  "select * from T_Cours where IdClasse in (select IdClasse from t_eleves where id=?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        
        preparedStatement.setInt(1,idEleve);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int idModule = resultSet.getInt("idModule");
            int idClasse = resultSet.getInt("idClasse");
            String dateHeure = resultSet.getString("dateHeure");
            String description = resultSet.getString("description");
            int duree = resultSet.getInt("duree");
            
			Cours cour = new Cours (id,idModule,idClasse, dateHeure, description,duree);
            cours.add(cour);
        }
     
        return cours;

	}
}
