package com.app.sms.ui.gestionnaires.controllers.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Classe;
import com.app.sms.models.Module;
import com.app.sms.ui.gestionnaires.controllers.IUIConsulterModuleController;
import com.app.sms.ui.gestionnaires.impl.UIConsulterModule;

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

			List<Module> modules = Module.list();
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
					uIConsulterModule.loadData(Module.list());
					uIConsulterModule.resetFormUI();
					if(uIConsulterModule.getCheckboxStatut())
					{
						Module.setEnseignantClasse(Integer.parseInt(uIConsulterModule.getIdEns()),Integer.parseInt(uIConsulterModule.getIdClasse()),module.getId());
					}
					
					
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
					uIConsulterModule.loadData(Module.list());
					uIConsulterModule.resetFormUI();
					uIConsulterModule.displayNotification("Done successfully !");
				} catch (NotFoundDataException | SQLException exception) {
					uIConsulterModule.displayErrorMessage(exception.getMessage());
				}
			}
		});
	}
	
	@Override
	public void addGoListener() {
		uIConsulterModule.addGoListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				try {
					uIConsulterModule.loadData(Module.list());
					uIConsulterModule.resetFormUI();
					uIConsulterModule.displayNotification("Done successfully !");
				} catch (SQLException exception) {
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
