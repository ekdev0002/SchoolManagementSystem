package com.app.sms.ui.enseignants.controllers.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Eleve;
import com.app.sms.models.Module;
import com.app.sms.ui.enseignants.controllers.IUIConsulterModuleController;
import com.app.sms.ui.enseignants.impl.UIConsulterModule;
import com.app.sms.ui.impl.MainUIApplication;

public class UIConsulterModuleController implements IUIConsulterModuleController {
	private UIConsulterModule uIConsulterModule;
	private Module module;

	/**
	 * @param uINouveauModule
	 */
	public UIConsulterModuleController(UIConsulterModule uIConsulterModule, Module module) {
		this.uIConsulterModule = uIConsulterModule;
		this.module = module;
		
		addUpdateListener();
		addDeleteListener();
		try {
			List<Module> modules = Module.list(MainUIApplication.getCurrentUser().getId());
			this.uIConsulterModule.loadData(modules);
			this.uIConsulterModule.displayNotification("Done successfully !");
			
		} catch (SQLException exception) {
			this.uIConsulterModule.displayErrorMessage(exception.getMessage());
		}			

	
	}
	
	@Override
	public void addUpdateListener() {
		uIConsulterModule.addUpdateListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				try {
					setModule (uIConsulterModule);
					module.update();
					uIConsulterModule.loadData(Module.list(MainUIApplication.getCurrentUser().getId()));
					uIConsulterModule.resetFormUI();
					uIConsulterModule.displayNotification("Done successfully !");
				} catch (SQLException | NotFoundDataException exception) {
					uIConsulterModule.displayErrorMessage(exception.getMessage());
				}
			}
		});
	}
	
	@Override
	public void addDeleteListener() {
		uIConsulterModule.addDeleteListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setModule (uIConsulterModule);
					module.delete();
					uIConsulterModule.loadData(Module.list(MainUIApplication.getCurrentUser().getId()));
					uIConsulterModule.resetFormUI();
					uIConsulterModule.displayNotification("Done successfully !");
				} catch (NotFoundDataException | SQLException exception) {
					uIConsulterModule.displayErrorMessage(exception.getMessage());
				}
			}
		});
	}

	public void run() {
		uIConsulterModule.showMe();
	}
	
	private void setModule (UIConsulterModule uIConsulterModule) {
		module.setId(Integer.parseInt(uIConsulterModule.getId()));
		module.setCoefficient(Integer.parseInt(uIConsulterModule.getCoefficient()));
		module.setLibelle(uIConsulterModule.getLibelle());
		module.setDescription(uIConsulterModule.getDescription());
	}
}
