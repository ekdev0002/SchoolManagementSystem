package com.app.sms.ui.impl;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

import com.app.sms.models.User;
import com.app.sms.ui.IApplication;
import com.app.sms.ui.gestionnaires.impl.UIConsulterCandidature;
import com.app.sms.utils.SingleConnection;
import com.app.sms.utils.Utilitaire;

public class MainUIApplication extends JFrame implements IApplication {
	
	
	private static User user;
	private JPanel headerPanel;
	private JPanel menuPanel;
	private JButton btnListeGestionnaires;
	private JButton btnListeEnseignants;
	private JButton btnListeEleves;
	private JButton btnListeCandidatures;
	private JButton btnListeModules;
	private JButton btnListeClasse;
	private JButton btnPlanningCours;
	private JButton btnPlanningDevoir;
	private JButton btnAccueil;
	private JButton btnDeconnexion;
	private JPanel mainPanel;
	private JPanel toolbarPanel;
	private JButton btnAjoutGestionnaire;
	private JButton btnAjoutEnseignant;
	private JButton btnAjoutEleve;
	private JToolBar toolBar_1;
	private JButton btnAjoutModules;
	private JButton btnAjoutClasse;
	private JButton btnPlanningCours2;
	private JButton btnPlanningDevoirs2;
	private JPanel panelWorkspace;
	private JButton btnAjoutCandidature;
	private JPanel panelW1;
	private JPanel panelW2;
	private CardLayout card;
	private JButton btnSaisiNote;
	private JButton btnGestionDesBulletins;
	private JButton btnSaisieDesNotes;
	private JButton btnGestionDesBulletins_1;


	public MainUIApplication(User user) {
		
		this.user = user;
				
		setUp ();
	}
	
	public static User getCurrentUser()
	{
		return user;
	}
	
	public void setUp () {
		setPreferredSize(new Dimension(1000, 700));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		headerPanel = new JPanel();
		getContentPane().add(headerPanel, BorderLayout.NORTH);
		
		JLabel lblSchoolSystemManagement = new JLabel("SCHOOL SYSTEM MANAGEMENT");
		lblSchoolSystemManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchoolSystemManagement.setFont(new Font("Tahoma", Font.PLAIN, 20));
		headerPanel.add(lblSchoolSystemManagement);
		
		menuPanel = new JPanel();
		menuPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(menuPanel, BorderLayout.WEST);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {150, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		menuPanel.setLayout(gridBagLayout);
		
		btnListeGestionnaires = new JButton("Liste Gestionnaires");
		btnListeGestionnaires.setHorizontalAlignment(SwingConstants.LEFT);
		btnListeGestionnaires.setToolTipText("Vissualiser Liste des gestionnaire ...");
		GridBagConstraints gbc_btnListeGestionnaires = new GridBagConstraints();
		gbc_btnListeGestionnaires.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnListeGestionnaires.insets = new Insets(0, 0, 5, 0);
		gbc_btnListeGestionnaires.gridx = 0;
		gbc_btnListeGestionnaires.gridy = 7;
		menuPanel.add(btnListeGestionnaires, gbc_btnListeGestionnaires);
		
		btnListeEnseignants = new JButton("Liste enseignants");
		btnListeEnseignants.setHorizontalAlignment(SwingConstants.LEFT);
		btnListeEnseignants.setBackground(new Color(173, 216, 230));
		btnListeEnseignants.setToolTipText("Vissualiser Liste des enseignants ...");
		GridBagConstraints gbc_btnListeEnseignants = new GridBagConstraints();
		gbc_btnListeEnseignants.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnListeEnseignants.insets = new Insets(0, 0, 5, 0);
		gbc_btnListeEnseignants.gridx = 0;
		gbc_btnListeEnseignants.gridy = 8;
		menuPanel.add(btnListeEnseignants, gbc_btnListeEnseignants);
		
		btnListeEleves = new JButton("Liste eleves");
		btnListeEleves.setHorizontalAlignment(SwingConstants.LEFT);
		btnListeEleves.setToolTipText("Visualiser Liste des eleves ...");
		GridBagConstraints gbc_btnListeEleves = new GridBagConstraints();
		gbc_btnListeEleves.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnListeEleves.insets = new Insets(0, 0, 5, 0);
		gbc_btnListeEleves.gridx = 0;
		gbc_btnListeEleves.gridy = 9;
		menuPanel.add(btnListeEleves, gbc_btnListeEleves);
		
		btnListeCandidatures = new JButton("Liste candidatures");
		btnListeCandidatures.setHorizontalAlignment(SwingConstants.LEFT);
		btnListeCandidatures.setToolTipText("Visualiser Liste des candidatures ...");
		btnListeCandidatures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnListeCandidatures.setBackground(new Color(173, 216, 230));
		GridBagConstraints gbc_btnListeCandidatures = new GridBagConstraints();
		gbc_btnListeCandidatures.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnListeCandidatures.insets = new Insets(0, 0, 5, 0);
		gbc_btnListeCandidatures.gridx = 0;
		gbc_btnListeCandidatures.gridy = 10;
		menuPanel.add(btnListeCandidatures, gbc_btnListeCandidatures);
		
		btnListeModules = new JButton("Liste modules");
		btnListeModules.setHorizontalAlignment(SwingConstants.LEFT);
		btnListeModules.setBackground(new Color(173, 216, 230));
		btnListeModules.setToolTipText("Visualiser Liste des modules ...");
		btnListeModules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnListeModules = new GridBagConstraints();
		gbc_btnListeModules.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnListeModules.insets = new Insets(0, 0, 5, 0);
		gbc_btnListeModules.gridx = 0;
		gbc_btnListeModules.gridy = 11;
		menuPanel.add(btnListeModules, gbc_btnListeModules);
		
		btnListeClasse = new JButton("Liste classes");
		btnListeClasse.setHorizontalAlignment(SwingConstants.LEFT);
		btnListeClasse.setToolTipText("Visualiser Liste des classes ...");
		btnListeClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnListeClasse.setBackground(new Color(173, 216, 230));
		GridBagConstraints gbc_btnListeClasse = new GridBagConstraints();
		gbc_btnListeClasse.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnListeClasse.insets = new Insets(0, 0, 5, 0);
		gbc_btnListeClasse.gridx = 0;
		gbc_btnListeClasse.gridy = 12;
		menuPanel.add(btnListeClasse, gbc_btnListeClasse);
		
		btnPlanningCours = new JButton("Planning Cours");
		btnPlanningCours.setHorizontalAlignment(SwingConstants.LEFT);
		btnPlanningCours.setToolTipText("Visualiser Planning cours ...");
		GridBagConstraints gbc_btnPlanningCours = new GridBagConstraints();
		gbc_btnPlanningCours.fill = GridBagConstraints.BOTH;
		gbc_btnPlanningCours.insets = new Insets(0, 0, 5, 0);
		gbc_btnPlanningCours.gridx = 0;
		gbc_btnPlanningCours.gridy = 13;
		menuPanel.add(btnPlanningCours, gbc_btnPlanningCours);
		
		btnPlanningDevoir = new JButton("Planning Devoir");
		btnPlanningDevoir.setHorizontalAlignment(SwingConstants.LEFT);
		btnPlanningDevoir.setToolTipText("Visualiser Planning devoirs ...");
		GridBagConstraints gbc_btnPlanningDevoir = new GridBagConstraints();
		gbc_btnPlanningDevoir.fill = GridBagConstraints.BOTH;
		gbc_btnPlanningDevoir.insets = new Insets(0, 0, 5, 0);
		gbc_btnPlanningDevoir.gridx = 0;
		gbc_btnPlanningDevoir.gridy = 14;
		menuPanel.add(btnPlanningDevoir, gbc_btnPlanningDevoir);
		
		btnSaisieDesNotes = new JButton("Saisie des Notes");
		btnSaisieDesNotes.setToolTipText("Saisie des notes des eleves");
		btnSaisieDesNotes.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnSaisieDesNotes = new GridBagConstraints();
		gbc_btnSaisieDesNotes.fill = GridBagConstraints.BOTH;
		gbc_btnSaisieDesNotes.insets = new Insets(0, 0, 5, 0);
		gbc_btnSaisieDesNotes.gridx = 0;
		gbc_btnSaisieDesNotes.gridy = 15;
		menuPanel.add(btnSaisieDesNotes, gbc_btnSaisieDesNotes);
		
		btnGestionDesBulletins_1 = new JButton("Gestion des Bulletins");
		btnGestionDesBulletins_1.setToolTipText("Visualiser et imprimer les bulletins");
		btnGestionDesBulletins_1.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnGestionDesBulletins_1 = new GridBagConstraints();
		gbc_btnGestionDesBulletins_1.fill = GridBagConstraints.BOTH;
		gbc_btnGestionDesBulletins_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnGestionDesBulletins_1.gridx = 0;
		gbc_btnGestionDesBulletins_1.gridy = 16;
		menuPanel.add(btnGestionDesBulletins_1, gbc_btnGestionDesBulletins_1);
		
		btnAccueil = new JButton("Accueil");
		btnAccueil.setHorizontalAlignment(SwingConstants.LEFT);
		btnAccueil.setBackground(new Color(173, 216, 230));
		btnAccueil.setToolTipText("Retour acceuil ...");
		GridBagConstraints gbc_btnAccueil = new GridBagConstraints();
		gbc_btnAccueil.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAccueil.insets = new Insets(0, 0, 5, 0);
		gbc_btnAccueil.gridx = 0;
		gbc_btnAccueil.gridy = 17;
		menuPanel.add(btnAccueil, gbc_btnAccueil);
		
		btnAccueil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ShowPanel("panel1");
			}
			
		});
		
		btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeconnexion.setBackground(Color.RED);
		btnDeconnexion.setToolTipText("Retour Login ...");
		GridBagConstraints gbc_btnDeconnexion = new GridBagConstraints();
		gbc_btnDeconnexion.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeconnexion.insets = new Insets(0, 0, 5, 0);
		gbc_btnDeconnexion.gridx = 0;
		gbc_btnDeconnexion.gridy = 18;
		menuPanel.add(btnDeconnexion, gbc_btnDeconnexion);
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		toolbarPanel = new JPanel();
		toolbarPanel.setBackground(new Color(173, 216, 230));
		mainPanel.add(toolbarPanel, BorderLayout.NORTH);
		
		JToolBar toolBar = new JToolBar();
		toolbarPanel.add(toolBar);
		
		btnAjoutGestionnaire = new JButton("");
		btnAjoutGestionnaire.setIcon(new ImageIcon(MainUIApplication.class.getResource("/images/téléchargement - Copie.jpg")));
		btnAjoutGestionnaire.setToolTipText("Ajouter un nouveau gestionnaire ...");
		toolBar.add(btnAjoutGestionnaire);
		
		btnAjoutEnseignant = new JButton("");
		btnAjoutEnseignant.setIcon(new ImageIcon(MainUIApplication.class.getResource("/images/nouveau-prof - Copie.jpg")));
		btnAjoutEnseignant.setToolTipText("Ajouter un nouvel enseignant ...");
		toolBar.add(btnAjoutEnseignant);
		
		btnAjoutEleve = new JButton("");
		btnAjoutEleve.setIcon(new ImageIcon(MainUIApplication.class.getResource("/images/téléchargement (3) - Copie.jpg")));
		btnAjoutEleve.setToolTipText("Ajouter un nouvel élève ...");
		toolBar.add(btnAjoutEleve);
		
		toolBar_1 = new JToolBar();
		toolbarPanel.add(toolBar_1);
		
		btnAjoutModules = new JButton("");
		btnAjoutModules.setIcon(new ImageIcon(MainUIApplication.class.getResource("/images/86b97956377c679f1b4a3c597358036f - Copie.jpg")));
		btnAjoutModules.setToolTipText("Ajouter un nouveau module ...");
		btnAjoutModules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		toolBar_1.add(btnAjoutModules);
		
		btnAjoutCandidature = new JButton("");
		btnAjoutCandidature.setIcon(new ImageIcon(MainUIApplication.class.getResource("/images/images (1) - Copie.jpg")));
		btnAjoutCandidature.setToolTipText("Ajouter une nouvelle candidature ...");
		toolbarPanel.add(btnAjoutCandidature);
		
		btnAjoutClasse = new JButton("");
		btnAjoutClasse.setIcon(new ImageIcon(MainUIApplication.class.getResource("/images/1095901-parcourssup-amphijpg - Copie.jpg")));
		btnAjoutClasse.setToolTipText("Ajouter une nouvelle classe ...");
		toolbarPanel.add(btnAjoutClasse);
		
		btnPlanningCours2 = new JButton("");
		btnPlanningCours2.setIcon(new ImageIcon(MainUIApplication.class.getResource("/images/planning-cours-icone.png")));
		btnPlanningCours2.setToolTipText("Visualiser / Modifier le planning de vos cours ...");
		toolbarPanel.add(btnPlanningCours2);
		
		btnPlanningDevoirs2 = new JButton("");
		btnPlanningDevoirs2.setToolTipText("Visualiser / Modifier le planning de vos cours ...");
		btnPlanningDevoirs2.setIcon(new ImageIcon(MainUIApplication.class.getResource("/images/planning-devoir-icone.png")));
		toolbarPanel.add(btnPlanningDevoirs2);
		
		btnSaisiNote = new JButton(" ");
		btnSaisiNote.setIcon(new ImageIcon(MainUIApplication.class.getResource("/images/saisiNote.png")));
		btnSaisiNote.setToolTipText("saisie des notes d'un devoir");
		toolbarPanel.add(btnSaisiNote);
		
		btnGestionDesBulletins = new JButton("");
		btnGestionDesBulletins.setToolTipText("Gestion des bulletins");
		btnGestionDesBulletins.setIcon(new ImageIcon(MainUIApplication.class.getResource("/images/imageBulletin2.png")));
		toolbarPanel.add(btnGestionDesBulletins);
		
		panelWorkspace = new JPanel();
		panelW1 = new JPanel();
		
		panelW1.setBackground(new Color(255, 255, 255));
		
		panelW1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(MainUIApplication.class.getResource("/images/esmt.jpg")));
		label.setBounds(167, 116, 480, 253);
		panelW1.add(label);
		
		JLabel lblBienvenue = new JLabel("BIENVENUE...");
		lblBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenue.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblBienvenue.setBounds(213, 53, 424, 70);
		panelW1.add(lblBienvenue);
		

		mainPanel.add(panelWorkspace, BorderLayout.CENTER);
		card=new CardLayout();
		panelWorkspace.setLayout(card);
		panelWorkspace.add(panelW1,"accueil");

		
		deactivateComponents();
		this.pack();
		this.setLocationRelativeTo(null);
		}
	
	public void addPanel(JPanel panel,String nomPanel) {
		panelWorkspace.add(panel,nomPanel);
	}

	
	public void ShowPanel(String nomPanel) {
		CardLayout cl = (CardLayout)(panelWorkspace.getLayout());
	    cl.show(panelWorkspace, nomPanel);
	}	
	
	private void exitWithConfirmation() {
		if ( JOptionPane.YES_OPTION == Utilitaire.showConfirmationMessage( "Voulez-vous vraiment quitter l'application ?" ) ) {
			try {
				SingleConnection.releaseConnection();				
			} catch (SQLException e) {
				System.err.println("Error while closing data base connection.");
			} finally {
				dispose();
			}
		}
	}




	private void deactivateComponents () {
		
		btnListeGestionnaires.setEnabled(false);
		btnListeEnseignants.setEnabled(false);
		btnListeEleves.setEnabled(false);
		btnListeCandidatures.setEnabled(false);
		btnListeModules.setEnabled(false);
		btnListeClasse.setEnabled(false);
		btnPlanningCours.setEnabled(false);
		btnPlanningCours2.setEnabled(false);
		btnPlanningDevoir.setEnabled(false);
		btnPlanningDevoirs2.setEnabled(false);
		btnAjoutGestionnaire.setEnabled(false);
		btnAjoutEnseignant.setEnabled(false);
		btnAjoutEleve.setEnabled(false);
		btnAjoutCandidature.setEnabled(false);
		btnAjoutModules.setEnabled(false);
		btnAjoutClasse.setEnabled(false);
		btnSaisiNote.setEnabled(false);
		btnGestionDesBulletins.setEnabled(false);
		btnSaisieDesNotes.setEnabled(false);
		btnGestionDesBulletins_1.setEnabled(false);

	}
	
	
	@Override
	public void activateGestionnaireComponent () {
		btnListeGestionnaires.setEnabled(true);
		btnListeEnseignants.setEnabled(true);
		btnListeEleves.setEnabled(true);
		btnListeCandidatures.setEnabled(true);
		btnListeModules.setEnabled(true);
		btnListeClasse.setEnabled(true);

		btnAjoutGestionnaire.setEnabled(true);
		btnAjoutEnseignant.setEnabled(true);
		btnAjoutEleve.setEnabled(true);
		btnAjoutCandidature.setEnabled(true);
		
		btnAjoutModules.setEnabled(true);
		btnAjoutClasse.setEnabled(true);
		
		btnGestionDesBulletins.setEnabled(true);
		btnGestionDesBulletins_1.setEnabled(true);
		
	}
	
	@Override
	public void activateEnseignantComponent () {		
		
		btnListeEleves.setEnabled(true);
		btnListeCandidatures.setEnabled(true);
		btnListeModules.setEnabled(true);
		btnListeClasse.setEnabled(true);

		
		btnPlanningCours.setEnabled(true);
		btnPlanningDevoir.setEnabled(true);		
		btnPlanningCours2.setEnabled(true);
		btnPlanningDevoirs2.setEnabled(true);		

		btnSaisiNote.setEnabled(true);
		btnSaisieDesNotes.setEnabled(true);

		
	}	
	
	@Override
	public void activateEleveComponent () {		
		
		btnPlanningCours.setEnabled(true);
		btnPlanningDevoir.setEnabled(true);		
		btnPlanningCours2.setEnabled(true);
		btnPlanningDevoirs2.setEnabled(true);	
		
		btnGestionDesBulletins.setEnabled(true);
		btnGestionDesBulletins_1.setText("Consulter Bulletin");;
		btnGestionDesBulletins_1.setEnabled(true);


	
		btnPlanningCours.setToolTipText("Consulter vos cours");
		btnPlanningDevoir.setToolTipText("Consulter vos devoirs");		
		btnPlanningCours2.setToolTipText("Consulter vos cours");
		btnPlanningDevoirs2.setToolTipText("Consulter vos devoirs");	
		
		btnGestionDesBulletins.setToolTipText("Visualiser et imprimer vos bulletins");
		btnGestionDesBulletins_1.setToolTipText("Visualiser et imprimer vos bulletins");		
	}

	@Override
	public void addNouveauGestionnaireComponentsListener(ActionListener listenerForNouveauGestionnaireComponent) {
		btnAjoutGestionnaire.addActionListener(listenerForNouveauGestionnaireComponent);
	}

	@Override
	public void addNouvelEnseignantComponentsListener(ActionListener listenerForNouvelEnseignantComponent) {
		btnAjoutEnseignant.addActionListener(listenerForNouvelEnseignantComponent);
	}

	@Override
	public void addNouvelEleveComponentsListener(ActionListener listenerForNouvelEleveComponent) {
		btnAjoutEleve.addActionListener(listenerForNouvelEleveComponent);
	}

	@Override
	public void addNouvelleClasseComponentsListener(ActionListener listenerForNouvelleClasseComponent) {
		btnAjoutClasse.addActionListener(listenerForNouvelleClasseComponent);
	}

	@Override
	public void addNouveauModuleComponentsListener(ActionListener listenerForNouveauModuleComponent) {
		btnAjoutModules.addActionListener(listenerForNouveauModuleComponent);
	}

	@Override
	public void addNouvelleCandidatureComponentsListener(ActionListener listenerForNouvelleCandidatureComponent) {
		btnAjoutCandidature.addActionListener(listenerForNouvelleCandidatureComponent);
	}
	
	@Override
	public void addConsulterGestionnaireComponentsListener(ActionListener listenerForConsulterGestionnaireComponent) {
		btnListeCandidatures.addActionListener(listenerForConsulterGestionnaireComponent);
	}

	@Override
	public void addConsulterEnseignantComponentsListener(ActionListener listenerForConsulterEnseignantComponent) {
		btnListeEnseignants.addActionListener(listenerForConsulterEnseignantComponent);
	}

	@Override
	public void addConsulterEleveComponentsListener(ActionListener listenerForConsulterEleveComponent) {
		btnListeEleves.addActionListener(listenerForConsulterEleveComponent);
	}

	@Override
	public void addConsulterClasseComponentsListener(ActionListener listenerForConsulterClasseComponent) {
		btnListeClasse.addActionListener(listenerForConsulterClasseComponent);
	}

	@Override
	public void addConsulterModuleComponentsListener(ActionListener listenerForConsulterModuleComponent) {
		btnListeModules.addActionListener(listenerForConsulterModuleComponent);
	}


	@Override
	public void addConsulterCandidatureComponentsListener(ActionListener listenerForConsulterCandidatureComponent) {
		btnListeCandidatures.addActionListener(listenerForConsulterCandidatureComponent);
	}

	@Override
	public void addPlanningDevoirComponentsListener(ActionListener listenerForPlanningCoursComponent) {
		btnPlanningDevoir.addActionListener(listenerForPlanningCoursComponent);
		btnPlanningDevoirs2.addActionListener(listenerForPlanningCoursComponent);
	}

	@Override
	public void addPlanningCoursComponentsListener(ActionListener listenerForPlanningDevoirComponent) {
		btnPlanningCours.addActionListener(listenerForPlanningDevoirComponent);
		btnPlanningCours2.addActionListener(listenerForPlanningDevoirComponent);
	}

	public void addSaisieNoteComponentsListener(ActionListener listenerSaisieNoteComponent) {
		btnSaisiNote.addActionListener(listenerSaisieNoteComponent);
		btnSaisieDesNotes.addActionListener(listenerSaisieNoteComponent);
	}

	
	@Override
	public void addImpressionBulletinComponentsListener(ActionListener listenerForImpressionBulletinComponent) {

	}

	@Override
	public void addBatchProcessComponentsListener(ActionListener listenerForBatchProcessComponent) {

	}
	
	
	
	public void addDeconnexionListener(ActionListener actionListener) {
		btnDeconnexion.addActionListener(actionListener);
		
	}

	@Override
	public void addConsulterCandidatureComponentsListener(JPanel panel) {
		// TODO Auto-generated method stub
		
	}

	public void addGestionBulletinsComponentsListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
		btnGestionDesBulletins.addActionListener(actionListener);
		btnGestionDesBulletins_1.addActionListener(actionListener);
		
	}
}
