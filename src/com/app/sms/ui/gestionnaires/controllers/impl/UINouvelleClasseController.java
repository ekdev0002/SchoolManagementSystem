package com.app.sms.ui.gestionnaires.controllers.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.models.Classe;
import com.app.sms.ui.gestionnaires.controllers.IUINouvelleClasseController;
import com.app.sms.ui.gestionnaires.impl.UINouvelleClasse;

public class UINouvelleClasseController implements IUINouvelleClasseController {
	private UINouvelleClasse uINouvelleClasse;
	private Classe classe;
	/**
	 * @param uINouvelleClasse
	 */
	public UINouvelleClasseController(UINouvelleClasse uINouvelleClasse, Classe classe) {
		this.uINouvelleClasse = uINouvelleClasse;
		this.classe = classe;
		addSubmitListener () ;
	}

	@Override
	public void addSubmitListener() {
		uINouvelleClasse.addSubmitListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {					
				try {
					classe.setLibelle(uINouvelleClasse.getLibelle());
					classe.setDescription(uINouvelleClasse.getDescription());
					classe.create();
					uINouvelleClasse.resetUI();
					uINouvelleClasse.displayNotification("Done successfully !");
				} catch (AlreadyExistDataException | SQLException exception) {
					uINouvelleClasse.displayErrorMessage(exception.getMessage());
				}
			}
		});
	}

	public void run() {
		uINouvelleClasse.showMe();
	}
}
