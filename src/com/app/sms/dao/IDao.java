package com.app.sms.dao;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;

/**
 * @author a459079
 *
 * @param <T>
 * CRUD operation on data base
 */
/**
 * @author a459079
 *
 * @param <T>
 */
public interface IDao<T> {
	
	/**
	 * return type : Integer 
	 * @param obj
	 * method : create
	 * Method to CREATE an entity in the database
	 * @throws SQLException 
	 * @throws NotFoundDataException 
	 * @throws AlreadyExistDataException 
	 */
	public boolean create ( T obj ) throws SQLException, NotFoundDataException, AlreadyExistDataException ;
	
	/**
	 * return type : List<T> 
	 * @return
	 * method : list
	 * Method to READ all the entities 
	 * @throws SQLException 
	 * @throws MalformedURLException 
	 */
	public List<T> list () throws SQLException, MalformedURLException ;
	
	/**
	 * return type : T 
	 * @param id
	 * @return
	 * method : find
	 * Method to  READ an entity
	 * @throws SQLException 
	 */
	public T find (int id) throws SQLException;
	
	
	/**
	 * return type : T 
	 * @param login
	 * @param password
	 * @return
	 * method : find
	 * @throws SQLException 
	 * @throws NotFoundDataException 
	 * 
	 */
	public boolean update ( T obj ) throws SQLException, NotFoundDataException ;
	
	/**
	 * return type : void 
	 * @param id
	 * method : delete
	 * Method to delete an entity
	 * @return 
	 * @throws SQLException 
	 */
	public boolean delete ( Integer id ) throws SQLException ;

	/**
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	boolean find(T obj) throws SQLException;
}