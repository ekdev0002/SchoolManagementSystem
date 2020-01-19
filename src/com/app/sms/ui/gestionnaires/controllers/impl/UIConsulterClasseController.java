package com.app.sms.ui.gestionnaires.controllers.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Candidature;
import com.app.sms.models.Classe;
import com.app.sms.ui.gestionnaires.controllers.IUIConsulterClasseController;
import com.app.sms.ui.gestionnaires.impl.UIConsulterClasse;

public class UIConsulterClasseController implements IUIConsulterClasseController {
	private UIConsulterClasse uIConsulterClasse;
	private Classe classe;
	/**
	 * @param uINouveauClasse
	 */
	public UIConsulterClasseController(UIConsulterClasse uIConsulterClasse, Classe classe) {
		this.uIConsulterClasse = uIConsulterClasse;
		this.classe = classe;
		
		addUpdateListener();
		addDeleteListener();
		
		try {

			List<Classe> classes = Classe.list();
			this.uIConsulterClasse.loadData(classes);
			this.uIConsulterClasse.displayNotification("Done successfully !");
		} catch (SQLException exception) {
			this.uIConsulterClasse.displayErrorMessage(exception.getMessage());
		}

		
		
	}
	
	@Override
	public void addUpdateListener() {
		uIConsulterClasse.addUpdateListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setClasse (uIConsulterClasse);
					classe.update ();
					uIConsulterClasse.loadData(Classe.list());
					uIConsulterClasse.resetFormUI();
					uIConsulterClasse.displayNotification("Done successfully !");
				} catch (SQLException | NotFoundDataException exception) {
					uIConsulterClasse.displayErrorMessage(exception.getMessage());
				}
			}
		});
	}
	
	@Override
	public void addDeleteListener() {
		uIConsulterClasse.addDeleteListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				try {					 
					setClasse (uIConsulterClasse);
					classe.delete () ;
					uIConsulterClasse.loadData(Classe.list());
					uIConsulterClasse.resetFormUI();
					uIConsulterClasse.displayNotification("Done successfully !");
				} catch (NotFoundDataException | SQLException exception) {
					uIConsulterClasse.displayErrorMessage(exception.getMessage());
				}
			}
		});		
	}
	
	@Override
	public void addGoListener() {
		uIConsulterClasse.addGoListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					uIConsulterClasse.loadData(Classe.list());
					uIConsulterClasse.resetFormUI();
					uIConsulterClasse.displayNotification("Done successfully !");
				} catch (SQLException exception) {
					uIConsulterClasse.displayErrorMessage(exception.getMessage());
				}
			}
		});
	}

	public void run() {
		uIConsulterClasse.showMe();
	}
	
	private void setClasse (UIConsulterClasse uIConsulterClasse) {
		classe.setId(Integer.parseInt(uIConsulterClasse.getId()));
		classe.setLibelle(uIConsulterClasse.getLibelle());
		classe.setDescription(uIConsulterClasse.getDescription());
	}
}
