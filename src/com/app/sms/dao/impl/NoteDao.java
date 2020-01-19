package com.app.sms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.INoteDao;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Devoirs;
import com.app.sms.models.Note;
import com.app.sms.ui.impl.MainUIApplication;

public class NoteDao extends Dao implements INoteDao<Note>  {
	


	public NoteDao() {
		super();
	}
	
	@Override
	public boolean create(Note note) throws SQLException {

		String query = "Insert Into T_Note (idDevoirs,note,coef,idEleve) values (?,?,?,?)";

    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setInt(1,note.getIdDevoirs());
    	preparedStatement.setDouble(2,note.getNote());
    	preparedStatement.setInt(3, note.getCoef());    	
    	preparedStatement.setInt(4,note.getIdEleve());
    	
    	preparedStatement.execute();
    	
        return true;
	}

	@Override
	public List<Note> list() throws SQLException {
		List<Note> notes = new ArrayList<>();
      
        String query =  "select * from T_Note where Id in (select IdNote from t_enseignants_note where idEnseignant=?)";        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,MainUIApplication.getCurrentUser().getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int idDevoirs = resultSet.getInt("idDevoirs");
            double not = resultSet.getDouble("note");
            int coef = resultSet.getInt("coef");
            int idEleve = resultSet.getInt("idEleve");
			Note note = new Note (id,idDevoirs,not, coef, idEleve);
            notes.add(note);
        }
       return notes;
	}

	@Override
	public Note find(int id) throws SQLException {
	
	    String query = "Select * From T_Note Where id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, id);
	    ResultSet resultatSet = preparedStatement.executeQuery();
	    if (resultatSet.next()) {
	        return new Note (id, resultatSet.getInt("idDevoirs"),resultatSet.getDouble("Note"),resultatSet.getInt("coef"), resultatSet.getInt("idEleve"));
	    }

		return null;
	}

	@Override
	public boolean update(Note note) throws SQLException {

        String query = "Update T_Note Set idDevoirs = ?,Note = ?, coef = ?, idEleve = ? Where id = ?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setInt(1, note.getIdDevoirs()); 
        prepareStatement.setDouble(2,note.getNote());
        prepareStatement.setInt(3, note.getCoef());
        prepareStatement.setInt(4, note.getIdEleve());
        prepareStatement.setInt(5, note.getId());        
       
        prepareStatement.execute();
        
        return true;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		String query = "Delete From T_Note Where id = ?";

        PreparedStatement preparedStatement;
       
    	preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setInt(1, id);
    	preparedStatement.execute();

        return true;
	}

	public boolean find(Note note) throws SQLException {
		boolean resultat = false;
		
        String query = "Select * From T_Note Where idDevoirs = ? and Note = ? and coef = ? and idEleve = ?";

  
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setInt(1, note.getIdDevoirs()); 
        prepareStatement.setDouble(2,note.getNote());
        prepareStatement.setInt(3, note.getCoef());
        prepareStatement.setInt(4, note.getIdEleve());
        ResultSet resultatSet = prepareStatement.executeQuery();
        
        if (resultatSet.next()) {
            resultat = true;
        }
         
		return resultat;
	}

	public List<Note> list(List<Devoirs> devoirs) throws SQLException {
			List<Note> notes = new ArrayList<>();
			String query =  "select * from T_Note";        
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        ResultSet resultSet = preparedStatement.executeQuery();    
			for(int i=0;i<devoirs.size();i++)
			{
		        while (resultSet.next()) {
		            int id = resultSet.getInt("id");
		            int idDevoirs = resultSet.getInt("idDevoirs");
		            double not = resultSet.getDouble("note");
		            int coef = resultSet.getInt("coef");
		            int idEleve = resultSet.getInt("idEleve");
		            if(idDevoirs==devoirs.get(i).getId())
		            {
						Note note = new Note (id,idDevoirs,not, coef, idEleve);
			            notes.add(note);
		            }
		        }
			}
	     
	        return notes;
		}
	

	public List<Note> list(String idDevoirs) throws SQLException {
			List<Note> notes = new ArrayList<>();
			String query =  "select * from T_Note";        
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        ResultSet resultSet = preparedStatement.executeQuery();    
	    	int id;
	    	int idDevoir;
	    	int coef;
	    	double not;
	    	int idEleve;

	        
	        while (resultSet.next()) {
	             id = resultSet.getInt("id");
	             idDevoir = resultSet.getInt("idDevoirs");
	             not = resultSet.getDouble("note");
	             coef = resultSet.getInt("coef");
	             idEleve = resultSet.getInt("idEleve");
	            
	            if(idDevoirs.equals(String.valueOf(idDevoir)))
	            {
					Note note = new Note (id,Integer.parseInt(idDevoirs),not, coef, idEleve);
		            notes.add(note);
	            }
		        }
	     
	        return notes;
		}
}
