package com.app.sms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.IDevoirsDao;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Devoirs;
import com.app.sms.ui.impl.MainUIApplication;

public class DevoirsDao extends Dao implements IDevoirsDao<Devoirs>  {
	
	public DevoirsDao() {
		super();
	}
	
	@Override
	public boolean create(Devoirs devoirs) throws SQLException {

		
		String quer = "Select coefficient From t_Modules Where id = ? ";

    	PreparedStatement preparedStatemen = connection.prepareStatement(quer);
    	preparedStatemen.setInt(1,devoirs.getIdModule());
    	
    	ResultSet resut=preparedStatemen.executeQuery();
    	while (resut.next()) {
    		devoirs.setCoef(resut.getInt("coefficient"));
    	}
				
		String query = "Insert Into T_Devoirs (idModule,idClasse,statut,dateHeure,description,duree,coef) values (?,?,?,?,?,?,?)";

    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setInt(1,devoirs.getIdModule());
        preparedStatement.setInt(2, devoirs.getIdClasse());
        preparedStatement.setString(3, devoirs.getStatut());
    	preparedStatement.setString(4, devoirs.getDateHeure());    	
    	preparedStatement.setString(5,devoirs.getDescription());
    	preparedStatement.setInt(6, devoirs.getDuree());
    	preparedStatement.setInt(7, devoirs.getCoef());
    	
    	preparedStatement.execute();
    	
		String query1 = "Select id From t_Devoirs Where idModule = ? and dateHeure = ?";

    	PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
    	preparedStatement1.setInt(1,devoirs.getIdModule());
    	preparedStatement1.setString(2,devoirs.getDateHeure());
    	
    	ResultSet result=preparedStatement1.executeQuery();
    	while (result.next()) {
    		devoirs.setId(result.getInt("Id"));
    	}
    	
    	
		String query2 = "Insert Into T_Enseignants_Devoirs (idEnseignant,idDevoirs) values (?,?)";

    	PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
    	preparedStatement2.setInt(1,MainUIApplication.getCurrentUser().getId());
    	preparedStatement2.setInt(2,devoirs.getId());
    	
    	preparedStatement2.execute();

        return true;
	}

	@Override
	public List<Devoirs> list() throws SQLException {
		List<Devoirs> devoirs = new ArrayList<>();
      
        String query =  "select * from T_Devoirs where Id in (select IdDevoirs from t_enseignants_devoirs where idEnseignant=?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,MainUIApplication.getCurrentUser().getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int idModule = resultSet.getInt("idModule");
            int idClasse = resultSet.getInt("idClasse");
            String statut = resultSet.getString("statut");
            String dateHeure = resultSet.getString("dateHeure");
            String description = resultSet.getString("description");
            int duree = resultSet.getInt("duree");
            int coef = resultSet.getInt("coef");
            
			Devoirs cour = new Devoirs (id,idModule,idClasse,statut, dateHeure, description,duree,coef);
            devoirs.add(cour);
        }
     
        return devoirs;
	}

	@Override
	public Devoirs find(int id) throws SQLException {
	
	    String query = "Select * From T_Devoirs Where id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, id);
	    ResultSet resultatSet = preparedStatement.executeQuery();
	    if (resultatSet.next()) {
	        return new Devoirs (id, resultatSet.getInt("idModule"), resultatSet.getInt("idClasse"),resultatSet.getString("statut"), resultatSet.getString("dateHeure"), resultatSet.getString("description"), resultatSet.getInt("duree"),resultatSet.getInt("coef"));
	    }

		return null;
	}

	@Override
	public boolean update(Devoirs devoirs) throws SQLException {

		String quer = "Select coefficient From t_Modules Where id = ? ";

    	PreparedStatement preparedStatemen = connection.prepareStatement(quer);
    	preparedStatemen.setInt(1,devoirs.getIdModule());
    	
    	ResultSet result=preparedStatemen.executeQuery();
    	while (result.next()) {
    		devoirs.setCoef(result.getInt("coefficient"));
    	}
		
		
        String query = "Update T_Devoirs Set idModule = ?, idClasse =?, statut = ?, dateHeure = ?, description = ? , duree = ? , coef = ? Where id = ?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setInt(1, devoirs.getIdModule());        
        prepareStatement.setInt(2, devoirs.getIdClasse());
        prepareStatement.setString(3, devoirs.getStatut());
        prepareStatement.setString (4, devoirs.getDateHeure());
        prepareStatement.setString(5, devoirs.getDescription());
        prepareStatement.setInt(6, devoirs.getDuree());
        prepareStatement.setInt(7, devoirs.getCoef());
        prepareStatement.setInt(8, devoirs.getId());        
       
        prepareStatement.execute();
        
        return true;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		String query = "Delete From T_Devoirs Where id = ?";

        PreparedStatement preparedStatement;
       
    	preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setInt(1, id);
    	preparedStatement.execute();

        return true;
	}

	public boolean find(Devoirs devoirs) throws SQLException {
		boolean resultat = false;
		
        String query = "Select * From T_Devoirs Where idModule = ? and idClasse = ? and Statut = ? and dateHeure = ? and description = ? and duree = ? and coef = ?";

  
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, devoirs.getIdModule());
        preparedStatement.setInt(2, devoirs.getIdClasse());
        preparedStatement.setString(3, devoirs.getStatut());
        preparedStatement.setString(4, devoirs.getDateHeure());
        preparedStatement.setString(5, devoirs.getDescription());
        preparedStatement.setInt(6, devoirs.getDuree());
        preparedStatement.setInt(7, devoirs.getCoef());
        ResultSet resultatSet = preparedStatement.executeQuery();
        
        if (resultatSet.next()) {
            resultat = true;
        }
         
		return resultat;
	}

	public int findIdDevoirsByDateHeure(String dateHeureDevoirs) throws NotFoundDataException, SQLException {
		String query = "Select * From T_Devoirs Where dateHeure = ?";
	        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, dateHeureDevoirs);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        } else {
        	throw new NotFoundDataException ("Error : Situation anormale devoirs introuvable ...");
        }
	}
	
	public List<Devoirs> list(int idEleve) throws SQLException {
		List<Devoirs> devoirs = new ArrayList<>();
      
        String query =  "select * from T_devoirs where IdClasse in (select IdClasse from t_eleves where id=?)";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,MainUIApplication.getCurrentUser().getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int idModule = resultSet.getInt("idModule");
            int idClasse = resultSet.getInt("idClasse");
            String statut = resultSet.getString("statut");
            String dateHeure = resultSet.getString("dateHeure");
            String description = resultSet.getString("description");
            int duree = resultSet.getInt("duree");
            int coef = resultSet.getInt("coef");
            
			Devoirs cour = new Devoirs (id,idModule,idClasse,statut, dateHeure, description,duree,coef);
            devoirs.add(cour);
        }
     
        return devoirs;
	}

}
