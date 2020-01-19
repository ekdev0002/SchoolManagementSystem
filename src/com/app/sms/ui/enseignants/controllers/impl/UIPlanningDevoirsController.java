package com.app.sms.ui.enseignants.controllers.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Devoirs;
import com.app.sms.models.Classe;
import com.app.sms.models.Devoirs;
import com.app.sms.ui.enseignants.controllers.IUIConsulterClasseController;
import com.app.sms.ui.enseignants.controllers.IUIPlanningDevoirsController;
import com.app.sms.ui.enseignants.impl.UIConsulterClasse;
import com.app.sms.ui.enseignants.impl.UIPlanningDevoirs;
import com.app.sms.ui.impl.MainUIApplication;

public class UIPlanningDevoirsController implements IUIPlanningDevoirsController {
	private UIPlanningDevoirs uiPlanningDevoirs;
	private Devoirs devoirs;
	/**
	 * @param uINouveauClasse
	 */
	public UIPlanningDevoirsController(UIPlanningDevoirs uiPlanningDevoirs,Devoirs cour) {
		this.uiPlanningDevoirs = uiPlanningDevoirs;
		this.devoirs=cour;
		
		addValiderListener();
		addSupprimerListener();
		try {
			List<Devoirs> devoirs = Devoirs.list();
			this.uiPlanningDevoirs.loadData(devoirs);
			this.uiPlanningDevoirs.displayNotification("Done successfully !");
			
		} catch (SQLException exception) {
			this.uiPlanningDevoirs.displayErrorMessage(exception.getMessage());
		}	
	}
	
	@Override
	public void addValiderListener() {
		uiPlanningDevoirs.addValiderListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
	
				try {
					setDevoirs (uiPlanningDevoirs);
					
					
					if(uiPlanningDevoirs.ajouterSelected())
					{
						
						devoirs.create ();
						
					}
					else
						devoirs.update ();
						uiPlanningDevoirs.loadData(Devoirs.list());
						uiPlanningDevoirs.resetFormUI();
						uiPlanningDevoirs.displayNotification("Done successfully !");
				} catch (SQLException | AlreadyExistDataException | NotFoundDataException exception) {
					uiPlanningDevoirs.displayErrorMessage(exception.getMessage());
				}
			}
		});
	}
	
	@Override
	public void addSupprimerListener() {
		uiPlanningDevoirs.addSupprimerListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				try {					 
					setDevoirs (uiPlanningDevoirs);
					devoirs.delete () ;
					uiPlanningDevoirs.loadData(Devoirs.list());
					uiPlanningDevoirs.resetFormUI();
					uiPlanningDevoirs.displayNotification("Done successfully !");
				} catch (NotFoundDataException | SQLException exception) {
					uiPlanningDevoirs.displayErrorMessage(exception.getMessage());
				}
			}
		});		
	}
	
	public void run() {
		uiPlanningDevoirs.showMe();
	}
	
	private void setDevoirs (UIPlanningDevoirs uiPlanningDevoirs) {
		try
		{
			devoirs.setId(Integer.parseInt(uiPlanningDevoirs.getId()));
			devoirs.setIdModule(Integer.parseInt(uiPlanningDevoirs.getIdModule()));
			devoirs.setIdClasse(Integer.parseInt(uiPlanningDevoirs.getIdClasse()));
			devoirs.setStatut(uiPlanningDevoirs.getStatut());
			devoirs.setDateHeure(uiPlanningDevoirs.getDateHeure());
			devoirs.setDuree(uiPlanningDevoirs.getDuree());
			devoirs.setDescription(uiPlanningDevoirs.getDescription());
			
		}
		catch(NullPointerException exception) {
			uiPlanningDevoirs.displayErrorMessage(exception.getMessage());
		}
	}
}
