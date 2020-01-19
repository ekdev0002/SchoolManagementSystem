package com.app.sms.ui.gestionnaires.controllers.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Eleve;
import com.app.sms.models.Enseignant;
import com.app.sms.ui.gestionnaires.controllers.IUIConsulterEleveController;
import com.app.sms.ui.gestionnaires.impl.UIConsulterEleve;

public class UIConsulterEleveController implements IUIConsulterEleveController {
	private UIConsulterEleve uIConsulterEleve;
	private Eleve eleve; 
	

	/**
	 * @param uINouveauEleve
	 */
	public UIConsulterEleveController(UIConsulterEleve uIConsulterEleve, Eleve eleve) {
		this.uIConsulterEleve = uIConsulterEleve;
		this.eleve = eleve;
		
		addUpdateListener();
		addDeleteListener();
		try {

			List<Eleve> eleves = eleve.list();
			this.uIConsulterEleve.loadData(eleves);
			this.uIConsulterEleve.displayNotification("Done successfully !");
		} catch (SQLException exception) {
			this.uIConsulterEleve.displayErrorMessage(exception.getMessage());
		}
	}
	
	@Override
	public void addUpdateListener() {
		uIConsulterEleve.addUpdateListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				try {
					setEleve (uIConsulterEleve);
					eleve.update();
					uIConsulterEleve.loadData(Eleve.list());
					uIConsulterEleve.resetFormUI();
					uIConsulterEleve.displayNotification("Done successfully !");
				} catch (SQLException | NotFoundDataException exception) {
					uIConsulterEleve.displayErrorMessage(exception.getMessage());
				}
			}
		});
	}
	
	@Override
	public void addDeleteListener() {
		uIConsulterEleve.addDeleteListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setEleve (uIConsulterEleve);
					eleve.delete();
					uIConsulterEleve.loadData(Eleve.list());
					uIConsulterEleve.resetFormUI();
					uIConsulterEleve.displayNotification("Done successfully !");
				} catch (NotFoundDataException | SQLException exception) {
					uIConsulterEleve.displayErrorMessage(exception.getMessage());
				}
			}
		});		
	}
	
	@Override
	public void addGoListener() {
		uIConsulterEleve.addGoListener (new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				try {
					uIConsulterEleve.loadData(Eleve.list());
					uIConsulterEleve.resetFormUI();
					uIConsulterEleve.displayNotification("Done successfully !");
				} catch (SQLException exception) {
					uIConsulterEleve.displayErrorMessage(exception.getMessage());
				}
			}
		});
	}

	public void run() {
		uIConsulterEleve.showMe();
	}
	
	private void setEleve (UIConsulterEleve uIConsulterEleve) {
		eleve.setId(Integer.parseInt(uIConsulterEleve.getId()));
		eleve.setNom(uIConsulterEleve.getNom());
		eleve.setPrenom(uIConsulterEleve.getPrenom());
		eleve.setGenre(uIConsulterEleve.getGenre());
		eleve.setTelephone(uIConsulterEleve.getTelephone());
		eleve.setEmail(uIConsulterEleve.getEmail());
		eleve.setLogin(uIConsulterEleve.getLogin());
		eleve.setPassword (uIConsulterEleve.getPassword());
		eleve.setLibelleClasse(uIConsulterEleve.getLibelleClasse());
		eleve.setPicturePath(uIConsulterEleve.getPicturePath());
	}
}
