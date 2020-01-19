package com.app.sms.ui.eleves.impl;
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
import com.app.sms.models.DevoirsModel;
import com.app.sms.models.Module;
import com.app.sms.models.Devoirs;
import com.app.sms.models.enums.Criteria;
import com.app.sms.ui.eleves.IUIPlanningDevoir;
import com.app.sms.ui.gestionnaires.listeners.FilledFieldChecker;
import com.app.sms.ui.impl.AbstractUIOperation;
import com.app.sms.utils.DateTimePicker;
import com.app.sms.utils.Utilitaire;

import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;

public class UIPlanningDevoirs extends AbstractUIOperation implements IUIPlanningDevoir {
	private JTable listeDevoirs;
	private DevoirsModel devoirsModel;

	private JTextField idTextField2;
	private JButton btnQuitter;
	protected String[] data;
	
	public UIPlanningDevoirs() {
		operationIcon.setIcon(new ImageIcon(UIPlanningDevoirs.class.getResource("/images/planning-devoir-icone.png")));
		operationLabel.setText("Planning des devoirs");
		
		JPanel mainPanel = new JPanel();
		centerPanel.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel listeDevoirsPanel = new JPanel();
		listeDevoirsPanel.setBackground(Color.WHITE);
		mainPanel.add(listeDevoirsPanel);
		
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
		
		listeDevoirs = new JTable();
		devoirsModel = new DevoirsModel();
		listeDevoirs.setModel(devoirsModel);
		listeScrollPane.setViewportView(listeDevoirs);
		listeDevoirsPanel.setLayout(listeDevoirsPanelLayout);
		
		JPanel panel = new JPanel();
		mainPanel.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
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
		panel_4.add(scrollPane, BorderLayout.CENTER);
		notification.setColumns(10);
		notification.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(notification);

		
		JPanel panel_3 = new JPanel();
		panel_15.add(panel_3);
        
        Date date1 = new Date();
		
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
		panel_3.add(btnQuitter);
        
		
		
		notification.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		/*
		 * Contrôle de la saisie du champ Libelle et Coéfficient avant activation du bouton de validation 
		 * 
		 * */	
		ArrayList<JTextField> idTextFields = new ArrayList<>();
	}
	
	
	@Override
	public void loadData(List<Devoirs> devoirs) {
		devoirsModel.loadData(devoirs);
	}
	
	
	private String[] getDevoirsDataByRow(int row) {
		
		String [] values = new String [devoirsModel.getColumnCount()];
		for ( int i = 0 ; i < devoirsModel.getColumnCount() ; i ++ ) {
			values [i] = (String) devoirsModel.getValueAt(row, i);
		}
		return values;
	}

}
