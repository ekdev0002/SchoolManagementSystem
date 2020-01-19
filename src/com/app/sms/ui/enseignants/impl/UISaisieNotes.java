package com.app.sms.ui.enseignants.impl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.swingx.JXDatePicker;

import com.app.sms.models.Classe;
import com.app.sms.models.Devoirs;
import com.app.sms.models.Eleve;
import com.app.sms.models.EleveNoteModel;
import com.app.sms.models.Module;
import com.app.sms.models.Note;
import com.app.sms.models.Devoirs;
import com.app.sms.models.enums.Criteria;
import com.app.sms.ui.enseignants.IUIPlanningDevoirs;
import com.app.sms.ui.enseignants.IUISaisieNote;
import com.app.sms.ui.gestionnaires.listeners.FilledFieldChecker;
import com.app.sms.ui.impl.AbstractUIOperation;
import com.app.sms.utils.DateTimePicker;
import com.app.sms.utils.Utilitaire;

import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;

public class UISaisieNotes extends AbstractUIOperation implements IUISaisieNote {
	private JTable listeEleves;
	private EleveNoteModel elevesNotesModel;

	
	private JComboBox devoirs;
	
	private JButton btnValider;
	private JButton btnSupprimer;
	private JButton btnQuitter;
	private DateTimePicker picker;
	protected String[] data;
	
	private List<Devoirs> listDevoirs;
	protected List<Note> notes;
	protected List<Eleve> eleves;
	private JComboBox coefField;
	
	public UISaisieNotes() {
		operationIcon.setIcon(new ImageIcon(UISaisieNotes.class.getResource("/images/saisiNote.png")));
		operationLabel.setText("Planning des devoirs");
		
		JPanel mainPanel = new JPanel();
		centerPanel.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(1, 2, 0, 0));
		elevesNotesModel = new EleveNoteModel();
		
		JPanel panel = new JPanel();
		mainPanel.add(panel);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_7.add(panel_2);
		panel_2.setBorder(new TitledBorder(null, "Ajouter Devoirs", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setHgap(30);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_6);
		
		JLabel lblNewLabel = new JLabel("Devoirs:");
		panel_6.add(lblNewLabel);
		
		
		devoirs = new JComboBox<>();
		panel_2.add(devoirs);
        
        JPanel panel_8 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_8.getLayout();
        flowLayout.setHgap(30);
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_8);
        
        JLabel lblNewLabel_1 = new JLabel("Coef:");
        panel_8.add(lblNewLabel_1);
        
        JPanel panel_5 = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
        flowLayout_2.setAlignment(FlowLayout.RIGHT);
        panel_2.add(panel_5);
        
        coefField = new JComboBox<>();
        panel_5.add(coefField);
        
		        
		        
        JPanel panel_9 = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) panel_9.getLayout();
        flowLayout_3.setHgap(30);
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        panel_2.add(panel_9);
        
        JLabel lblDateEtHeure = new JLabel("Date et Heure:");
        panel_9.add(lblDateEtHeure);
        picker = new DateTimePicker();
        Date date = new Date();
        panel_2.add(picker);
        picker.getEditor().setHorizontalAlignment(SwingConstants.RIGHT);
        picker.setFormats( DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.MEDIUM ) );
        picker.setTimeFormat( DateFormat.getTimeInstance( DateFormat.MEDIUM ) );   
        picker.setDate(date);
        picker.getEditor().setColumns(18);
		                
        panel_2.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_7.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(null, "Notification", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_15);
		panel_15.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_15.add(panel_4);
		
		panel_4.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		notification = new JTextArea();
		notification.setColumns(10);
		notification.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_4.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(notification);
		
				
		JPanel panel_3 = new JPanel();
		panel_15.add(panel_3);
		
		btnValider = new JButton("Valider");
		
		btnValider.setPreferredSize(new Dimension(65, 20));
		btnValider.setMinimumSize(new Dimension(65, 20));
		btnValider.setMaximumSize(new Dimension(65, 20));
		btnValider.setEnabled(true);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setPreferredSize(new Dimension(69, 20));
		btnSupprimer.setMinimumSize(new Dimension(69, 20));
		btnSupprimer.setMaximumSize(new Dimension(69, 20));
		btnSupprimer.setEnabled(false);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitWithConfirmation();
					}	
		});
		btnQuitter.setPreferredSize(new Dimension(67, 20));
		btnQuitter.setMinimumSize(new Dimension(67, 20));
		btnQuitter.setMaximumSize(new Dimension(67, 20));
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_3.add(btnValider);
		panel_3.add(btnSupprimer);
		panel_3.add(btnQuitter);
		
		JPanel listeDevoirsPanel = new JPanel();
		panel.add(listeDevoirsPanel);
		listeDevoirsPanel.setBackground(Color.WHITE);
		
		JScrollPane listeScrollPane = new JScrollPane();
		listeScrollPane.setBorder(new TitledBorder(null, "Liste des devoirs", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout listeDevoirsPanelLayout = new GroupLayout(listeDevoirsPanel);
		listeDevoirsPanelLayout.setHorizontalGroup(
			listeDevoirsPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(listeDevoirsPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(listeScrollPane, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
					.addContainerGap())
		);
		listeDevoirsPanelLayout.setVerticalGroup(
			listeDevoirsPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(listeDevoirsPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(listeScrollPane, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		listeEleves = new JTable();
		listeEleves.setModel(elevesNotesModel);
		listeScrollPane.setViewportView(listeEleves);
		listeDevoirsPanel.setLayout(listeDevoirsPanelLayout);
						
		
//		listeEleves.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				int selectedRow = listeDevoirs.getSelectedRow();
//				if ( selectedRow >= 0 ) {
//					data = getDevoirsDataByRow(selectedRow);
//					updateUIForm (data);
//						
//				}
//			}
//		});
		
		/*
		 * Contrôle de la saisie du champ Libelle et Coéfficient avant activation du bouton de validation 
		 * 
		 * */	
		ArrayList<JTextField> idTextFields = new ArrayList<>();
		
		loadData();

		devoirs.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				String [] splitInfos = devoirs.getSelectedItem().toString().split("::");
				String idClasse=splitInfos[2];
				String idDevoir=splitInfos[0];
				coefField.setSelectedIndex(devoirs.getSelectedIndex());
				try {
					notes=Note.list(idDevoir);
					eleves=Eleve.list(idClasse);
					if(notes.size()==0)
					{

						for(Eleve eleve : eleves)
						{
							Note note = new Note(Integer.parseInt(idDevoir),0,Integer.parseInt(coefField.getSelectedItem().toString()),eleve.getId());
							notes.add(note);
						}
					}
					elevesNotesModel.loadData(eleves,notes);
					
				} catch (SQLException exception) {
					// TODO Auto-generated catch block
					displayErrorMessage(exception.getMessage());
				}
				
			}
			
		});
	
	}
	
	@Override
	public void updateUIForm(String[] data) {
		try {
				
		} catch ( NullPointerException ignored) {}
	}
	
	
	public void loadData() {
		
		try {
			listDevoirs=Devoirs.list();
			if(devoirs.getItemCount()==0)
			{
			for ( Devoirs devoir : listDevoirs ) {
				devoirs.addItem( devoir.getId() + Utilitaire.SEPARATEUR + devoir.getModule()+Utilitaire.SEPARATEUR+devoir.getIdClasse()+Utilitaire.SEPARATEUR+devoir.getClasse());
				coefField.addItem(String.valueOf(devoir.getCoef()));
			}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	
	}
	
	
	private void setDateHeure(String time) {
		Date now =new Date();
		picker.setDate(now);

	}
			
	
	public List<Note> getNotes(){
		
		String [] values = new String [elevesNotesModel.getRowCount()];
		if(notes.size()>0)
		{
			for ( int i = 0 ; i < elevesNotesModel.getRowCount() ; i ++ ) {
				values [i] = (String) elevesNotesModel.getValueAt(i, 3);
				double valeur = Double.parseDouble(values[i]);
				notes.get(i).setNote(valeur);
			}			
		}
		return notes;
	}
	
	public void addValiderListener(ActionListener actionListener) {
		btnValider.addActionListener(actionListener);
			
	}

	public void addSupprimerListener(ActionListener actionListener) {
		btnSupprimer.addActionListener(actionListener);
	}
	

	public void resetFormUI() {
		devoirs.setSelectedIndex(0);
	}


	

}
