package com.app.sms.ui.eleves.controllers.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Cours;
import com.app.sms.ui.eleves.controllers.IUIPlanningCoursController;
import com.app.sms.ui.eleves.impl.UIPlanningCours;
import com.app.sms.ui.impl.MainUIApplication;

public class UIPlanningCoursController implements IUIPlanningCoursController {
	private UIPlanningCours uiPlanningCours;
	private Cours cours;
	/**
	 * @param uINouveauClasse
	 */
	public UIPlanningCoursController(UIPlanningCours uiPlanningCours,Cours cour) {
		this.uiPlanningCours = uiPlanningCours;
		this.cours=cour;
		try {
			System.out.println("idd="+MainUIApplication.getCurrentUser().getId());
			List<Cours> cours = Cours.list(MainUIApplication.getCurrentUser().getId());
			this.uiPlanningCours.loadData(cours);
			this.uiPlanningCours.displayNotification("Done successfully !");
			
		} catch (SQLException exception) {
			this.uiPlanningCours.displayErrorMessage(exception.getMessage());
		}	
	}
	
	
	public void run() {
		uiPlanningCours.showMe();
	}
	
	
}
