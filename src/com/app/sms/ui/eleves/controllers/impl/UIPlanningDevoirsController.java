package com.app.sms.ui.eleves.controllers.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Devoirs;
import com.app.sms.models.Classe;
import com.app.sms.models.Devoirs;
import com.app.sms.ui.eleves.controllers.IUIPlanningDevoirsController;
import com.app.sms.ui.eleves.impl.UIPlanningDevoirs;
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
		
		try {
			List<Devoirs> devoirs = Devoirs.list(MainUIApplication.getCurrentUser().getId());
			this.uiPlanningDevoirs.loadData(devoirs);
			this.uiPlanningDevoirs.displayNotification("Done successfully !");
			
		} catch (SQLException exception) {
			this.uiPlanningDevoirs.displayErrorMessage(exception.getMessage());
		}	
	}
	
	public void run() {
		uiPlanningDevoirs.showMe();
	}
}
