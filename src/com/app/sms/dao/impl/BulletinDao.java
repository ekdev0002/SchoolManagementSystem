package com.app.sms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.app.sms.dao.IBulletinDao;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Bulletin;

public class BulletinDao extends Dao  {
	

	public BulletinDao() {
		super();
	}
	
	public void create() throws SQLException {
		
		
		String query1 = "INSERT INTO t_bulletins (idEleve, nom,prenom, genre, date)"
							+"select id,nom,prenom,genre,DATE_FORMAT(SYSDATE(), '%Y-%m-%d') as date "
							+"FROM t_eleves;";
		
		PreparedStatement preparedStatement1 = connection.prepareStatement(query1);    	
    	preparedStatement1.execute();
		

    	Vector<int[]> rows = new Vector<>();
    	Vector<Integer> listeIdClasse=new Vector<>();
    	Vector<String> listeLibelleClasse =new Vector<>();
		String query2="select id, idEleve from t_bulletins;";
		
		
		PreparedStatement preparedStatement2 = connection.prepareStatement(query2);    	
    	 ResultSet resultSet = preparedStatement2.executeQuery();
         while (resultSet.next()) {
        	 int val[]=new int[2];
             val[0] = resultSet.getInt("id");
             val[1] = resultSet.getInt("idEleve");
             rows.add(val);			
         }
		
		for(int i=0;i<rows.size();i++)
		{
			String decision = "Semestre inValidé";
			double moyenne=0;
			int effectif=0;
			String query3= "INSERT INTO t_bulletins_notes (idEleve,idBulletin, note, coef, notePondere, libelle) "
							+" select ? , ? , avg(n.note) as note, n.coef, avg(n.note)*n.coef as notePondere, m.libelle from t_note n, t_devoirs d,t_modules m "
							+" where n.idDevoirs=d.id and d.idModule=m.id and n.idEleve=? "
								+" group by(d.idModule);";
			
			PreparedStatement preparedStatement3 = connection.prepareStatement(query3);	    	
	    	preparedStatement3.setInt(1,rows.get(i)[1]);
	    	preparedStatement3.setInt(2, rows.get(i)[0]);
	    	preparedStatement3.setInt(3, rows.get(i)[1]);
	    	preparedStatement3.execute();	

			String query4= "select sum(notePondere)/sum(coef) as MoyenneGenerale from t_bulletins_notes where idEleve=? and idBulletin=?;";
			PreparedStatement preparedStatement4 = connection.prepareStatement(query4);	    	
	    	preparedStatement4.setInt(1,rows.get(i)[1]);
	    	preparedStatement4.setInt(2, rows.get(i)[0]);
	    	ResultSet resultSet4 = preparedStatement4.executeQuery();
	         while (resultSet4.next()) {
	             moyenne = resultSet4.getDouble("MoyenneGenerale");
	         }

	    	String query5 = "select count(id) as effectif from t_eleves where idClasse in (select idClasse from t_eleves where id=?);";
			PreparedStatement preparedStatement5 = connection.prepareStatement(query5);	    	
	    	preparedStatement5.setInt(1,rows.get(i)[1]);
	    	ResultSet resultSet5 = preparedStatement5.executeQuery();
	         while (resultSet5.next()) {
	             effectif = resultSet5.getInt("effectif");
	         }


	 		String query6 = "update t_bulletins set decision=?, moyenneGenerale=?, effectif=? where id=? and idEleve=?;";

			PreparedStatement preparedStatement6 = connection.prepareStatement(query6);	    	
	    	if(moyenne>10)
	    	{
	    		decision = "Semestre Validé";
	    	}		
			preparedStatement6.setString(1,decision);
	    	preparedStatement6.setDouble(2, moyenne);
	    	preparedStatement6.setInt(3, effectif);	
	    	preparedStatement6.setInt(4,rows.get(i)[0]);
	    	preparedStatement6.setInt(5,rows.get(i)[1]);
	    	preparedStatement6.execute();		
			}
		
		
		String query7="select id,libelle from t_classes;";	
		PreparedStatement preparedStatement7 = connection.prepareStatement(query7);    	
    	 ResultSet resultSet7 = preparedStatement7.executeQuery();
         while (resultSet7.next()) {	 
             Integer val = resultSet7.getInt("id");
             String val2 = resultSet7.getString("libelle");
             listeIdClasse.add(val);
             listeLibelleClasse.add(val2);
         }

         for (int i=0;i<listeIdClasse.size();i++)
         {
            String query8="select idEleve from t_bulletins where idEleve in(select id from t_eleves where idClasse=?) order by (moyenneGenerale) desc;";
     		PreparedStatement preparedStatement8 = connection.prepareStatement(query8);    	
     		preparedStatement8.setInt(1,listeIdClasse.get(i));
     		ResultSet resultSet8 = preparedStatement8.executeQuery();
	       	 int j=1;
	            while (resultSet8.next()) {         	
	             String query9="update t_bulletins set rang=?, classe=? where idEleve=?";
	   	         PreparedStatement preparedStatement9 = connection.prepareStatement(query9); 
	   	   	     preparedStatement9.setInt(1,j);
	   	   	preparedStatement9.setString(2,listeLibelleClasse.get(i));
	   	   	     preparedStatement9.setInt(3,resultSet8.getInt("idEleve"));
	   	   	     preparedStatement9.execute();
	   	   	     j++;
	            }	            
	      }
         	
		}

	public List<Bulletin> list() throws SQLException {
		List<Bulletin> bulletins = new ArrayList<>();
      
        String query = "Select * From T_Bulletins ";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
        	int idEleve = resultSet.getInt("idEleve");
        	String nom = resultSet.getString("nom");
        	String prenom= resultSet.getString("prenom");
        	String genre= resultSet.getString("genre");
        	String date= resultSet.getString("date");
        	String decision= resultSet.getString("decision");
        	Double moyenneGenerale= resultSet.getDouble("moyenneGenerale");
        	String effectif= String.valueOf(resultSet.getInt("effectif"));
        	String rang= String.valueOf(resultSet.getInt("rang"));
        	String classe= resultSet.getString("classe");
			Bulletin bulletin = new Bulletin(id,idEleve,nom,prenom,genre,date,decision,moyenneGenerale,effectif,rang,classe);
			Vector<String[]> rows = new Vector<>();
			
			
		    String query2 = " Select * From T_Bulletins_notes where idBulletin=? ;";
	        PreparedStatement prepareStatement2 = connection.prepareStatement(query2);
	        prepareStatement2.setInt(1,id);
	        ResultSet resultSet2 = prepareStatement2.executeQuery();
		        while (resultSet2.next()) {
		        	rows.add( new String [] {
							resultSet2.getString("libelle"), 
							String.valueOf(resultSet2.getInt("coef")),
							String.valueOf(resultSet2.getDouble("note")), 
							String.valueOf(resultSet2.getDouble("notePondere"))
							});
		        }

		    String query3 = " Select sum(coef) as coef,sum(notePondere)/sum(coef) as note,sum(notePondere) as notePondere From T_Bulletins_notes where idBulletin=?";
	        PreparedStatement prepareStatement3 = connection.prepareStatement(query3);
	        prepareStatement3.setInt(1,id);
	        ResultSet resultSet3 = prepareStatement3.executeQuery();
		        while (resultSet3.next()) {
		        	rows.add( new String [] {
							"Total", 
							String.valueOf(resultSet3.getInt("coef")),
							String.valueOf(resultSet3.getDouble("note")), 
							String.valueOf(resultSet3.getDouble("notePondere"))
							});
		        }
		        
		        
		        
		    bulletin.setRows(rows);
            bulletins.add(bulletin);
        }
     
        return bulletins;
	}

	public List<Bulletin> list(int id2) throws SQLException {
		List<Bulletin> bulletins = new ArrayList<>();
	      
        String query = "Select * From T_Bulletins where idEleve=?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setInt(1,id2);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
        	int idEleve = resultSet.getInt("idEleve");
        	String nom = resultSet.getString("nom");
        	String prenom= resultSet.getString("prenom");
        	String genre= resultSet.getString("genre");
        	String date= resultSet.getString("date");
        	String decision= resultSet.getString("decision");
        	Double moyenneGenerale= resultSet.getDouble("moyenneGenerale");
        	String effectif= String.valueOf(resultSet.getInt("effectif"));
        	String rang= String.valueOf(resultSet.getInt("rang"));
        	String classe= resultSet.getString("classe");
			Bulletin bulletin = new Bulletin(id,idEleve,nom,prenom,genre,date,decision,moyenneGenerale,effectif,rang,classe);
			Vector<String[]> rows = new Vector<>();
			
			
		    String query2 = " Select * From T_Bulletins_notes where idBulletin=? ;";
	        PreparedStatement prepareStatement2 = connection.prepareStatement(query2);
	        prepareStatement2.setInt(1,id);
	        ResultSet resultSet2 = prepareStatement2.executeQuery();
		        while (resultSet2.next()) {
		        	rows.add( new String [] {
							resultSet2.getString("libelle"), 
							String.valueOf(resultSet2.getInt("coef")),
							String.valueOf(resultSet2.getDouble("note")), 
							String.valueOf(resultSet2.getDouble("notePondere"))
							});
		        }

		    String query3 = " Select sum(coef) as coef,sum(notePondere)/sum(coef) as note,sum(notePondere) as notePondere From T_Bulletins_notes where idBulletin=?";
	        PreparedStatement prepareStatement3 = connection.prepareStatement(query3);
	        prepareStatement3.setInt(1,id);
	        ResultSet resultSet3 = prepareStatement3.executeQuery();
		        while (resultSet3.next()) {
		        	rows.add( new String [] {
							"Total", 
							String.valueOf(resultSet3.getInt("coef")),
							String.valueOf(resultSet3.getDouble("note")), 
							String.valueOf(resultSet3.getDouble("notePondere"))
							});
		        }	        
		    bulletin.setRows(rows);
            bulletins.add(bulletin);
        }
     
        return bulletins;
	}
}