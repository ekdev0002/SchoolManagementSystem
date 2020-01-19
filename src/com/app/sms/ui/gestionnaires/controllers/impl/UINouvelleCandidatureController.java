package com.app.sms.ui.gestionnaires.controllers.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.BadFormatDataException;
import com.app.sms.exceptions.DateOutOfBoundsException;
import com.app.sms.exceptions.NotAvailableDateFormatException;
import com.app.sms.models.Candidature;
import com.app.sms.ui.gestionnaires.controllers.IUINouvelleCandidatureController;
import com.app.sms.ui.gestionnaires.impl.UINouvelleCandidature;
import com.app.sms.ui.gestionnaires.impl.UINouvelleCandidature2;

public class UINouvelleCandidatureController implements IUINouvelleCandidatureController {
	private UINouvelleCandidature2 uINouvelleCandidature;
	private Candidature candidature;
	/**
	 * @param uINouvelleCandidature
	 */
	public UINouvelleCandidatureController(UINouvelleCandidature2 uINouvelleCandidature, Candidature candidature) {
		this.uINouvelleCandidature = uINouvelleCandidature;
		this.candidature = candidature;
		addSubmitListener () ;
	}

	@Override
	public void addSubmitListener() {
		uINouvelleCandidature.addSubmitListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setCandidature ();
					candidature.create();
					uINouvelleCandidature.resetUI();
					uINouvelleCandidature.displayNotification("Done successfully !");
				} catch (AlreadyExistDataException | SQLException | BadFormatDataException | NumberFormatException | DateOutOfBoundsException | NotAvailableDateFormatException exception) {
					uINouvelleCandidature.displayErrorMessage("An error occurred : " + exception.getMessage());
				}
			}
			
			private void setCandidature () throws BadFormatDataException, NumberFormatException, DateOutOfBoundsException, NotAvailableDateFormatException {
				candidature.setId (uINouvelleCandidature.getId());
				candidature.setNom (uINouvelleCandidature.getNom());
				candidature.setPrenom (uINouvelleCandidature.getPrenom());
				candidature.setEmail (uINouvelleCandidature.getEmail());
				candidature.setTelephone (uINouvelleCandidature.getTelephone());
				candidature.setGenre(uINouvelleCandidature.getGenre());
				candidature.setBirthday(uINouvelleCandidature.getBirthday());
				candidature.setPicturePath(uINouvelleCandidature.getPicturePath());
				candidature.setCv(uINouvelleCandidature.getCVUrl());
				candidature.setDiplomes(uINouvelleCandidature.getDiplomes());
				candidature.setIdEnseignant(uINouvelleCandidature.getSelectedEnseignantId());
			}
		});
	}
	
	public void run() {
		uINouvelleCandidature.showMe();
	}
}
