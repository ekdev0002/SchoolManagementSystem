/**
 * 
 */
package com.app.sms.dao;

import java.sql.SQLException;

import com.app.sms.models.Module;

/**
 * @author a459079
 *
 */
public interface IModuleDao<T> extends IDao<T> {

	boolean find(Module module) throws SQLException;
}
