package com.app.sms.ui.impl;
/**
 * 
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.app.sms.models.Profils;
import com.app.sms.ui.IUILogin;
import com.app.sms.ui.gestionnaires.listeners.ValiderListener;
import com.app.sms.utils.Utilitaire;

/**
 * @author a459079
 *
 */
@SuppressWarnings("serial")
public class UILogin extends JFrame implements IUILogin {
	
	private JTextField loginText;
	private JPasswordField passwordField;
	private JButton validerButton;
	private JButton annulerButton;
	private JButton resetButton;
	private JLabel messageLabel;
	private JComboBox<String> userProfilComboBox ;
	private JPanel headerPanel;
	private JLabel logoEsmtLabel;
	private JLabel labelConnectezvous;
	
	public static final Profils[] PROFIL_UTILISATEURS = Profils.values() ;
	
	public UILogin() {
	
		
		setResizable(false);
		setTitle("Authentification");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(173, 216, 230));
		mainPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JPanel banniere = new JPanel();
		getContentPane().add(banniere, BorderLayout.NORTH);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		banniere.setLayout(new BorderLayout(0, 0));
		
		headerPanel = new JPanel();
		headerPanel.setBackground(new Color(255, 255, 255));
		headerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout flowLayout = (FlowLayout) headerPanel.getLayout();
		flowLayout.setHgap(3);
		flowLayout.setVgap(2);
		flowLayout.setAlignment(FlowLayout.LEFT);
		banniere.add(headerPanel, BorderLayout.NORTH);
		
		logoEsmtLabel = new JLabel("");
		logoEsmtLabel.setIcon(new ImageIcon(UILogin.class.getResource("/images/School.png")));
		headerPanel.add(logoEsmtLabel);
		
		labelConnectezvous = new JLabel("Sign in");
		labelConnectezvous.setForeground(new Color(173, 216, 230));
		labelConnectezvous.setFont(new Font("Tahoma", Font.PLAIN, 20));
		headerPanel.add(labelConnectezvous);
		
		validerButton = new JButton("Valider");
		validerButton.setEnabled(false);
		validerButton.setBackground(new Color(173, 216, 230));
		
		validerButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		validerButton.setBounds(48, 70, 70, 20);
		
		mainPanel.add(validerButton);
		
		JLabel loginLabel = new JLabel("Login :");
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		loginLabel.setBounds(79, 14, 39, 14);
		mainPanel.add(loginLabel);
		
		loginText = new JTextField();
		loginText.setBounds(124, 11, 155, 22);
		
		mainPanel.add(loginText);
		loginText.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(55, 44, 68, 14);
		mainPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(124, 40, 155, 22);
		mainPanel.add(passwordField);
				
		/* tant que les deux champs Login et password ne sont pas 
		 * renseignés le button est desabled */
		ValiderListener validerListener = new ValiderListener( validerButton, loginText, passwordField) ;		
		loginText.getDocument().addDocumentListener(validerListener);
		passwordField.getDocument().addDocumentListener(validerListener);
		
		annulerButton = new JButton("Quitter");
		annulerButton.setBackground(new Color(173, 216, 230));
		annulerButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		annulerButton.setBounds(145, 70, 70, 20);
		annulerButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitWithConfirmation ();
			}
		});
		mainPanel.add(annulerButton);
		
		messageLabel = new JLabel("");
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		messageLabel.setForeground(Color.RED);
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setBounds(10, 115, 400, 14);
		mainPanel.add(messageLabel);
		
		resetButton = new JButton("Reset");
		resetButton.setBackground(new Color(173, 216, 230));
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		resetButton.setBounds(233, 70, 70, 20);
		resetButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reinitUI();
			}
		});
		mainPanel.add(resetButton);
		
		JLabel labelSelection = new JLabel("profil User :");
		labelSelection.setHorizontalAlignment(SwingConstants.CENTER);
		labelSelection.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelSelection.setBounds(317, 12, 70, 19);
		mainPanel.add(labelSelection);
		
		userProfilComboBox = new JComboBox<>();
		userProfilComboBox.setBackground(new Color(173, 216, 230));
		userProfilComboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		userProfilComboBox.setModel(new DefaultComboBoxModel<String> ());
		userProfilComboBox.addItem(Profils.Gestionnaire.name());
		userProfilComboBox.addItem(Profils.Enseignant.name());
		userProfilComboBox.addItem(Profils.Eleve.name());
		userProfilComboBox.setBounds(317, 29, 77, 22);
		mainPanel.add(userProfilComboBox);
		
		Utilitaire.center (this, new Dimension (407, 210));
		Utilitaire.setLookAndFeel (this);
	}
	
	@Override
	public void displayErrorMessage(String errorMessage) {
		messageLabel.setForeground(Color.RED);
		messageLabel.setText(errorMessage);
	}

	@Override
	public void displayNotification(String message) {
		messageLabel.setForeground( new Color (32, 166, 72));
		messageLabel.setText(message);
	}

	@Override
	public void addValiderListener(ActionListener listenForValiderButton) {
		validerButton.addActionListener(listenForValiderButton);
	}

	private void reinitUI () {
		messageLabel.setText("");
		loginText.setText("");
		passwordField.setText("");
	}

	@Override
	public void setEnabledUIButton (boolean status) {
		resetButton.setEnabled(status);
		annulerButton.setEnabled(status);
	}
	
	@Override
	public String getUserProfil() {
		return userProfilComboBox.getSelectedItem().toString() ;
	}

	@Override
	public String getLogin() {
		return loginText.getText() ;
	}

	@Override
	public String getPassword() {
		return new String (passwordField.getPassword()) ;
	}
	
	private void exitWithConfirmation () {
		this.setAlwaysOnTop(false);
		int response = Utilitaire.showConfirmationMessage("Voulez-vous vraiment quitter ?") ;
		if ( response == JOptionPane.YES_OPTION ) {
			this.dispose();
		} else {
			this.setAlwaysOnTop(true);
		}
		
	}
}

