package com.app.sms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sms.dao.IModuleDao;
import com.app.sms.models.Module;

public class ModuleDao extends Dao implements IModuleDao<Module>  {
	
	public ModuleDao() {
		super();
	}
	
	@Override
	public boolean create(Module module) throws SQLException {

		String query = "Insert Into T_Modules (coefficient,libelle,description) values (?,?,?)";
		
    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	
    	preparedStatement.setInt(1,module.getCoefficient());
    	preparedStatement.setString(2,module.getLibelle());
    	preparedStatement.setString(3, module.getDescription());
    	
    	preparedStatement.execute();
 
        return true;
	}
	
	@Override
	public List<Module> list() throws SQLException {
		List<Module> modules = new ArrayList<>();
      
        String query = "Select * From T_Modules";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int coefficient = resultSet.getInt("coefficient");
            String libelle = resultSet.getString("libelle");
            String description = resultSet.getString("description");             
			Module module = new Module (id, coefficient , libelle, description);
            modules.add(module);
        }
        return modules;
	}

	@Override
	public Module find(int id) throws SQLException {
	
        String query = "Select * From T_Modules Where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultatSet = preparedStatement.executeQuery();
        if (resultatSet.next()) {
            return new Module (id, resultatSet.getString("libelle"), resultatSet.getString("description"));
        }
   
		return null;
	}

	@Override
	public boolean update(Module module) throws SQLException {
        String query = "Update T_Modules Set coefficient = ?, libelle = ?, description = ? Where id = ?";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        
        prepareStatement.setInt(1, module.getCoefficient());
        prepareStatement.setString(2, module.getLibelle());
        prepareStatement.setString(3, module.getDescription());
        prepareStatement.setInt(4, module.getId());
       
        prepareStatement.execute();
            
        return true;
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		String query = "Delete From T_Modules Where id = ?";

        PreparedStatement preparedStatement;
    	preparedStatement = connection.prepareStatement(query);
    	preparedStatement.setInt(1, id);
    	preparedStatement.execute();

        return true;
	}
	
	@Override
	public boolean find(Module module) throws SQLException {
		boolean resultat = false;
	
        String query = "Select * From T_Modules Where coefficient = ? and libelle = ?";
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, module.getCoefficient());
        preparedStatement.setString(2, module.getLibelle());
        
        ResultSet resultatSet = preparedStatement.executeQuery();
        
        if (resultatSet.next()) {
            resultat = true;
        }
      
		return resultat;
	}

	public List<Module> list(int idEns) throws SQLException {
		List<Module> modules = new ArrayList<>();
		System.out.print("id= "+idEns);
	      
        String query = "Select * From T_Modules where id in (select idModule from t_enseignants_classes_modules where idEns=?)";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setInt(1,idEns);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int coefficient = resultSet.getInt("coefficient");
            String libelle = resultSet.getString("libelle");
            String description = resultSet.getString("description");             
			Module module = new Module (id, coefficient , libelle, description);
            modules.add(module);
        }
        return modules;
	}

	public void setEnseignantClasse(int parseInt, int parseInt2, int id2) throws SQLException {
		String query = "Insert Into  t_enseignants_classes_modules (idEns,idClasse,idModule) values (?,?,?)";
		
    	PreparedStatement preparedStatement = connection.prepareStatement(query);
    	
    	preparedStatement.setInt(1,parseInt);
    	preparedStatement.setInt(2,parseInt2);
    	preparedStatement.setInt(3, id2); 	
    	preparedStatement.execute();

	}
}