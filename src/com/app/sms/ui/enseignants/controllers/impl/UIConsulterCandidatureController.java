package com.app.sms.ui.enseignants.controllers.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import com.app.sms.dao.impl.CandidatureDao;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Candidature;
import com.app.sms.ui.enseignants.controllers.IUIConsulterCandidatureController;
import com.app.sms.ui.enseignants.impl.UIConsulterCandidature;
import com.app.sms.ui.impl.MainUIApplication;

public class UIConsulterCandidatureController implements IUIConsulterCandidatureController {
	private UIConsulterCandidature uIConsulterCandidature;
	private Candidature candidature;
	/**
	 * @param uINouveauCandidature
	 */
	public UIConsulterCandidatureController(UIConsulterCandidature uIConsulterCandidature, Candidature candidature) {
		this.uIConsulterCandidature = uIConsulterCandidature;
		this.candidature = candidature;
		
		addUpdateListener();
		addDeleteListener();
		
		try {

			List<Candidature> candidatures = Candidature.list(MainUIApplication.getCurrentUser().getId());
			this.uIConsulterCandidature.loadData(candidatures);
			this.uIConsulterCandidature.displayNotification("Done successfully !");
		} catch (SQLException exception) {
			this.uIConsulterCandidature.displayErrorMessage(exception.getMessage());
		}

	}
	
	@Override
	public void addUpdateListener() {
		
		uIConsulterCandidature.addUpdateListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				try {
					setCandidature (uIConsulterCandidature);
					candidature.update();
					uIConsulterCandidature.loadData(Candidature.list(MainUIApplication.getCurrentUser().getId()));
					uIConsulterCandidature.displayNotification("Done successfully !");
				} catch (SQLException | NotFoundDataException exception) {
					uIConsulterCandidature.displayErrorMessage(exception.getMessage());
				}
			}
		});
	}
	
	@Override
	public void addDeleteListener() {
		
		uIConsulterCandidature.addDeleteListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setCandidature (uIConsulterCandidature);
					candidature.delete();
					uIConsulterCandidature.loadData(Candidature.list(MainUIApplication.getCurrentUser().getId()));
					uIConsulterCandidature.resetFormUI();
					uIConsulterCandidature.displayNotification("Done successfully !");
				} catch (NotFoundDataException | SQLException exception) {
					uIConsulterCandidature.displayErrorMessage(exception.getMessage());
				}
			}
		});
	}
	
	public void run() {
		uIConsulterCandidature.showMe();
	}
	
	private void setCandidature (UIConsulterCandidature uIConsulterCandidature) {
		CandidatureDao candidatureDao = new CandidatureDao();
		try {
			 candidature = candidatureDao.find(Integer.parseInt(uIConsulterCandidature.getId()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		candidature.setState(uIConsulterCandidature.getState());
	}
}
