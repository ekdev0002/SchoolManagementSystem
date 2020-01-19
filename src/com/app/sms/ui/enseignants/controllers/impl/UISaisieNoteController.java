package com.app.sms.ui.enseignants.controllers.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import com.app.sms.exceptions.AlreadyExistDataException;
import com.app.sms.exceptions.NotFoundDataException;
import com.app.sms.models.Devoirs;
import com.app.sms.models.Note;
import com.app.sms.models.Classe;
import com.app.sms.models.Devoirs;
import com.app.sms.ui.enseignants.controllers.IUIConsulterClasseController;
import com.app.sms.ui.enseignants.controllers.IUISaisieNoteController;
import com.app.sms.ui.enseignants.impl.UIConsulterClasse;
import com.app.sms.ui.enseignants.impl.UISaisieNotes;
import com.app.sms.ui.impl.MainUIApplication;

public class UISaisieNoteController implements IUISaisieNoteController {
	private UISaisieNotes uiSaisieNotes;
	/**
	 * @param uINouveauClasse
	 */
	private List<Note> notes;
	
	public UISaisieNoteController(UISaisieNotes uiSaisieNotes) {
		this.uiSaisieNotes = uiSaisieNotes;
		
		addValiderListener();
		
			this.uiSaisieNotes.loadData();
			this.uiSaisieNotes.resetFormUI();
			this.uiSaisieNotes.displayNotification("Done successfully !");
				
	}
	
	@Override
	public void addValiderListener() {
		uiSaisieNotes.addValiderListener( new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				notes=uiSaisieNotes.getNotes();
				
				try {	
					
					if(notes.get(0).getId()==0)
					{
						for(int i=0;i<notes.size();i++)
						  (notes.get(i)).create ();
					}
					else
					{
						for(int i=0;i<notes.size();i++)
							  (notes.get(i)).update();
					}
					
					uiSaisieNotes.resetFormUI();
					
					uiSaisieNotes.displayNotification("Done successfully !");
				} catch (SQLException | AlreadyExistDataException | NotFoundDataException exception) {
					uiSaisieNotes.displayErrorMessage(exception.getMessage());
				}
			}
		});
	}
	
	public void run() {
		uiSaisieNotes.showMe();
	}
}
