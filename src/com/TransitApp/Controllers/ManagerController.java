package com.TransitApp.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.TransitApp.Controllers.TransporteurController;

import com.TransitApp.Modeles.Transporteur;

import com.TransitApp.Dao.AdminDao;
import com.TransitApp.Dao.IAdminDao;
import com.TransitApp.Dao.ILigneCommandeClientDao;
import com.TransitApp.Dao.IOrdremissionDao;
import com.TransitApp.Dao.IProduitDao;
import com.TransitApp.Dao.ITransporteurDao;
import com.TransitApp.Dao.LigneCommandeClientDao;
import com.TransitApp.Dao.OrdremissionDao;
import com.TransitApp.Dao.ProduitDao;
import com.TransitApp.Dao.TransporteurDao;
import com.TransitApp.Modeles.Admin;
import com.TransitApp.Modeles.Categorie;
import com.TransitApp.Modeles.Contenir;
import com.TransitApp.Modeles.Contenir2;
import com.TransitApp.Modeles.Contenir2Id;
import com.TransitApp.Modeles.Entrepot;
import com.TransitApp.Modeles.Fournisseur;
import com.TransitApp.Modeles.Ordremission;
import com.TransitApp.Modeles.Produit;
import com.TransitApp.Modeles.Transporteur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ManagerController implements Initializable {

	@FXML
	private TextField text_moyenpaiement;

	@FXML
	private ComboBox<?> combabox;

	@FXML
	private Button addComboBoxordredemission;

	@FXML
	private Button add_ligne_commande;

	@FXML
	private TextField txtIdcmdClient;

	private String[] Role = { "Manager", "Transporteur", "Respo_Reapro" };

	@FXML
	private TextField text_produit;

	@FXML
	private TextField text_quantité;

	@FXML
	private TextField textdevise;

	@FXML
	private TableColumn<?, ?> datecréation;

	@FXML
	public ComboBox<?> comboboxTranspoteur;

	@FXML
	private ComboBox<?> comboboxRole;

	@FXML
	private DatePicker dcreation;

	@FXML
	private TableColumn<?, ?> RAPPORT;

	@FXML
	private TableColumn<?, ?> debut;

	@FXML
	private ComboBox<?> comboBoxProduit;

	@FXML
	private TableColumn<?, ?> devise;

	@FXML
	private TableColumn<?, ?> idcomande;

	@FXML
	private TableColumn<?, ?> moyenpaiement;

	@FXML
	private Button ajouuser;

	@FXML
	private Button modifuser;

	@FXML
	private TextField password;

	@FXML
	private Button suppuser;

	@FXML
	private TextField username;

	@FXML
	private TextField rapport_TXT;
	@FXML
	private DatePicker datedebut;

	@FXML
	private DatePicker datefin;

	@FXML
	private Button Logoutbtn;

	@FXML
	private TextField idtransporteur;

	@FXML
	private TextField textidcommande;

	@FXML
	private TextField nummission;

	@FXML
	private TextField chercher;

	@FXML
	private TableColumn<?, ?> col_idmission;

	@FXML
	private TableColumn<?, ?> col_mission;

	@FXML
	private TableView<Ordremission> tableau_rapport;

	@FXML
	private TextField text_datedebut;

	@FXML
	private TextField text_datefin;

	@FXML
	private TextField text_idmission;

	@FXML
	private MenuBar menu;

	@FXML
	private AnchorPane ajoutuser;

	@FXML
	private Button ajouter;

	@FXML
	private Button boutton_gestion_commandes;

	@FXML
	private Button boutton_gestionmission;

	@FXML
	private Button boutton_suivi;

	@FXML
	private Button adduser;

	@FXML
	private AnchorPane choisir_nom;

	@FXML
	private ChoiceBox<?> choisirquantite;

	@FXML
	private TableColumn<?, ?> description;

	@FXML
	private Button logoutBtn;

	@FXML
	private TextArea entrerDescription;

	@FXML
	private TextField entrer_id;

	@FXML
	private TextField entrer_nom;

	@FXML
	private TableColumn<?, ?> fin;

	@FXML
	private TableColumn<?, ?> idcommande;

	@FXML
	private Button modifier;

	@FXML
	private TableColumn<?, ?> nomclient;

	@FXML
	private TableColumn<?, ?> nomproduit;

	@FXML
	private AnchorPane page_ajout_commande;

	@FXML
	private AnchorPane page_ajout_mission;

	@FXML
	private TableColumn<?, ?> quantité;

	@FXML
	private TextField recherchecommande;

	@FXML
	private TextField text_lieu;
	@FXML
	private TextField statut;

	@FXML
	private TextField role;

	IOrdremissionDao ordremissionDao = new OrdremissionDao();

	IAdminDao adminDao = new AdminDao();

	@FXML
	private AnchorPane suivi;

	@FXML
	private TableColumn<?, ?> STAT;

	@FXML
	private Button gestionuser;

	@FXML
	private TableView<Admin> tablrau_user;

	@FXML
	private TableColumn<?, ?> idmission;

	@FXML
	private TableColumn<?, ?> user_name;

	@FXML
	private TableColumn<?, ?> mot_de_passe;

	@FXML
	private TableColumn<?, ?> roles;

	@FXML
	private Button ButtonDeletemission;

	@FXML
	private TableColumn<?, ?> mission;

	@FXML
	private TableColumn<?, ?> transporteur;

	@FXML
	private TextField txtIdLignecmdClient;

	private ObservableList<Admin> addAdminList;

	private ObservableList<Ordremission> addOdremissionList;

	@FXML
	private TableView<Ordremission> tableau_mission;

	@FXML
	private Button ButtonDeleteProduit;

	@FXML
	private Button ButtonModifyProduit;

	@FXML
	private Button ButtonModifymission;

	@FXML
	private TableView<Contenir2> TableLigneCmd;

	@FXML
	private TableColumn<?, ?> TableLigneCmd_col_Cout;

	@FXML
	private TableColumn<?, ?> TableLigneCmd_col_IdCmd;

	@FXML
	private TableColumn<?, ?> TableLigneCmd_col_IdLigneCmd;

	@FXML
	private TableColumn<?, ?> TableLigneCmd_col_Produit;

	@FXML
	private TableColumn<?, ?> TableLigneCmd_col_Quantite;

	@FXML
	private Button addComboBoxFournisseur;

	ITransporteurDao TransporteurDao = new TransporteurDao();

	IProduitDao produitDao = new ProduitDao();

	ILigneCommandeClientDao lignecmdDao = new LigneCommandeClientDao();

	private ObservableList<Contenir2> addLigneCmdClientsList;

	public void addRoleComboBox() {
		List<String> RoleList = new ArrayList<>();

		for (String data : Role) {
			RoleList.add(data);
		}

		ObservableList oblist = FXCollections.observableArrayList(RoleList);
		comboboxRole.setItems(oblist);
	}

	public void addComboBoxProduit() {
		List<String> ProduitList = new ArrayList<>();

		for (Produit data : produitDao.getAllProduit()) {
			ProduitList.add(data.getNomproduit());
		}

		ObservableList oblist = FXCollections.observableArrayList(ProduitList);
		comboBoxProduit.setItems(oblist);
	}

	public void addComboBoxtransporteur() {
		List<String> trasporteurList = new ArrayList<>();

		for (Transporteur data : TransporteurDao.getAllTransporteur()) {
			trasporteurList.add(data.getNomtransporteur());
		}

		ObservableList oblist = FXCollections.observableArrayList(trasporteurList);
		comboboxTranspoteur.setItems(oblist);
	}

	public void close() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CONFIRMATION MESSAGE");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to quit !");
		Optional<ButtonType> option = alert.showAndWait();

		if (option.get().equals(ButtonType.OK)) {
			System.exit(0);
		} else
			return;
	}

	public void switchForm(ActionEvent event) {
		if (event.getSource() == boutton_gestion_commandes) {
			page_ajout_commande.setVisible(true);
			page_ajout_mission.setVisible(false);
			suivi.setVisible(false);
			ajoutuser.setVisible(false);
			addComboBoxProduit();

			addStyle(boutton_gestion_commandes, "#34a39c");

			removeStyleBtn(boutton_gestionmission, boutton_suivi, gestionuser);
		}

		else if (event.getSource() == boutton_gestionmission) {
			page_ajout_commande.setVisible(false);
			page_ajout_mission.setVisible(true);
			suivi.setVisible(false);
			ajoutuser.setVisible(false);

			addStyle(boutton_gestionmission, "#34a39c");
			removeStyleBtn(boutton_gestion_commandes, boutton_suivi, gestionuser);
		}

		else if (event.getSource() == gestionuser) {
			page_ajout_commande.setVisible(false);
			page_ajout_mission.setVisible(false);
			suivi.setVisible(false);
			ajoutuser.setVisible(true);

			addStyle(gestionuser, "#34a39c");
			removeStyleBtn(boutton_gestion_commandes, boutton_suivi, boutton_gestionmission);

		}

//			

		else if (event.getSource() == boutton_suivi) {
			page_ajout_commande.setVisible(false);
			page_ajout_mission.setVisible(false);
			suivi.setVisible(true);
			ajoutuser.setVisible(false);

			addStyle(boutton_suivi, "#34a39c");
			removeStyleBtn(boutton_gestion_commandes, boutton_gestionmission, gestionuser);

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre

		}
	}

	private void addStyle(Button btn, String color) {
		btn.setStyle("-fx-background-color:" + color);
	}

	private void removeStyleBtn(Button btn1, Button btn2, Button btn3) {
		btn1.setStyle("-fx-background-color: transparent");
		btn2.setStyle("-fx-background-color: transparent");
		btn3.setStyle("-fx-background-color: transparent");

	}

	public void addOrdremission() {
		Alert alert;
		Ordremission ordremission = new Ordremission();
		if (textidcommande.getText().isEmpty() || nummission.getText().isEmpty()
				|| comboboxTranspoteur.getSelectionModel().getSelectedItem() == null || statut.getText().isEmpty()
				|| rapport_TXT.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Remplissez tous les champs s'il vous plait ");
			alert.showAndWait();
		} else {
			Boolean verif = false;

			if (verif == true) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("La mission   exite déja. Entrer un autre id de mission");
				alert.showAndWait();
			} else {
				ordremission.setRapport(statut.getText());
				ordremission.setIdcommandeclient(textidcommande.getText());
				ordremission.setNumeroordremission(nummission.getText());
				ordremission.setRapport(rapport_TXT.getText());

				Date date = new Date();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				ordremission.setDatedebut(sqlDate);
				ordremission.setDatefin(null);

				ordremission.setStatue(statut.getText());

				int id1 = 0;
				String val1 = (String) comboboxTranspoteur.getSelectionModel().getSelectedItem();
				for (Transporteur e : TransporteurDao.getAllTransporteur()) {
					if (e.getNomtransporteur().equalsIgnoreCase(val1)) {
						id1 = e.getIdtransporteur();
					}
				}
				ordremission.setIdtransporteur(id1);
				;

				ordremissionDao.saveOrdremission(ordremission);
				System.out.println("===Enregistremment Effectuer");

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucess Save");
				alert.setHeaderText(null);
				alert.setContentText("mission  enregistrer avec success");
				alert.showAndWait();
				missionShowList();
				clearmission();

			}
			missionShowList();

			System.out.println(verif);
		}
	}

	public void deletemission() {
		Alert alert;
		if (nummission.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Selectionner ou entrer un numero de mission");
			Optional<ButtonType> option = alert.showAndWait();
		} else {
			// Vérifie l'existance d'un fournisseur
			Boolean verif = false;
			int id = 0;
			for (Ordremission e : ordremissionDao.getAllOrdremission()) {
				if (e.getNumeroordremission().equalsIgnoreCase(nummission.getText())) {
					verif = true;
					id = e.getIdordremission();
				}
			}
			if (verif == true) {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CONFIRMATION MESSAGE");
				alert.setHeaderText(null);
				alert.setContentText("Êtes vous sures de vouloir supprimer la mission: " + nummission.getText()
						+ " ? Cette action est irreversible");
				Optional<ButtonType> option = alert.showAndWait();
				if (option.get().equals(ButtonType.OK)) {
					ordremissionDao.deleteOrdremission(id);
					missionShowList();
					clearmission();

				}
			} else {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Le transporteur: " + nummission.getText() + " n'existe pas ");
				alert.showAndWait();
				return;
			}
		}

	}

	public void clearmission() {

		statut.setText("");
		textidcommande.setText("");
		nummission.setText("");
		rapport_TXT.setText("");

	}

	public void updatemission() {
		Alert alert;
		if (textidcommande.getText().isEmpty() || nummission.getText().isEmpty()
				|| comboboxTranspoteur.getSelectionModel().getSelectedItem() == null || statut.getText().isEmpty()
				|| rapport_TXT.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Remplissez tous les champs s'il vous plait ");
			alert.showAndWait();
		} else {
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CONFIRMATION MESSAGE");
			alert.setHeaderText(null);
			alert.setContentText("Êtes vous sures de vouloir modifier les Informations de la mission: "
					+ nummission.getText() + " ? Cette action est irreversible");
			Optional<ButtonType> option = alert.showAndWait();
			if (option.get().equals(ButtonType.OK)) {
				for (Ordremission e : ordremissionDao.getAllOrdremission()) {
					if (e.getNumeroordremission().equalsIgnoreCase(nummission.getText())) {
						e.setIdcommandeclient(textidcommande.getText());
						e.setNumeroordremission(nummission.getText());
						e.setRapport(rapport_TXT.getText());
						e.setStatue(statut.getText());

						int id1 = 0;
						String val1 = (String) comboboxTranspoteur.getSelectionModel().getSelectedItem();
						for (Transporteur a : TransporteurDao.getAllTransporteur()) {
							if (a.getNomtransporteur().equalsIgnoreCase(val1)) {
								id1 = a.getIdtransporteur();
							}
						}
						e.setIdtransporteur(id1);

						ordremissionDao.updateordremission(e);
					}
				}

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucess Modification");
				alert.setHeaderText(null);
				alert.setContentText("utilisateur: " + username.getText() + " enregistrer avec success");
				alert.showAndWait();
				missionShowList();

			}
		}

	}

	public void missionShowList() {
		addOdremissionList = ordremissionDao.addOrdremissionList();

		idcomande.setCellValueFactory(new PropertyValueFactory<>("idcommandeclient"));
		idmission.setCellValueFactory(new PropertyValueFactory<>("idordremission"));
		RAPPORT.setCellValueFactory(new PropertyValueFactory<>("rapport"));
		debut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
		fin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
		STAT.setCellValueFactory(new PropertyValueFactory<>("statue"));
		mission.setCellValueFactory(new PropertyValueFactory<>("numeroordremission"));
		transporteur.setCellValueFactory(new PropertyValueFactory<>("idtransporteur"));

		tableau_mission.setItems(addOdremissionList);
	}

	public void logout() {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CONFIRMATION MESSAGE");
			alert.setHeaderText(null);
			alert.setContentText("Êtes vous sure de vouloir vous déconnecter?");
			Optional<ButtonType> option = alert.showAndWait();

			if (option.get().equals(ButtonType.OK)) {

//						Permet de cacher la fenetre du dashboard
				logoutBtn.getScene().getWindow().hide();
				Parent root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);

				stage.initStyle(StageStyle.TRANSPARENT);

				stage.show();
			} else
				return;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ADD USER

	public void ajouuser() {
		Alert alert;
		Admin admin = new Admin();
		if (username.getText().isEmpty() || password.getText().isEmpty()
				|| comboboxRole.getSelectionModel().getSelectedItem() == null) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Remplissez tous les champs s'il vous plait ");
			alert.showAndWait();
		} else {
			Boolean verif = false;

			if (verif == true) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("L'utilisateur: " + username.getText() + " exite déja. Entrer un autre username");
				alert.showAndWait();
			} else {

				admin.setLogin(username.getText());
				admin.setPassword(password.getText());
				admin.setRole(String.valueOf(comboboxRole.getSelectionModel().getSelectedItem()));

				adminDao.saveAdmin(admin);
				System.out.println("===Enregistremment Effectuer");

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucess Save");
				alert.setHeaderText(null);
				alert.setContentText("utilisateur: " + username.getText() + " enregistrer avec success");
				alert.showAndWait();
				userShowList();
				clearuser();

			}
			userShowList();
			System.out.println(verif);
		}
	}

	// SUPPRIMER UN UTILISATEUR

	public void deleteUSER() {
		Alert alert;
		if (username.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Selectionner ou entrer un nom de fournisseur");
			Optional<ButtonType> option = alert.showAndWait();
		} else {
			// Vérifie l'existance d'un fournisseur
			Boolean verif = false;
			int id = 0;
			for (Admin e : adminDao.getAllAdmin()) {
				if (e.getLogin().equalsIgnoreCase(username.getText())) {
					verif = true;
					id = e.getIdadmin();
				}
			}
			if (verif == true) {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CONFIRMATION MESSAGE");
				alert.setHeaderText(null);
				alert.setContentText("Êtes vous sures de vouloir supprimer l'utilisateur: " + username.getText()
						+ " ? Cette action est irreversible");
				Optional<ButtonType> option = alert.showAndWait();
				if (option.get().equals(ButtonType.OK)) {
					adminDao.deleteAdmin(id);
					userShowList();
					clearuser();

				}
			} else {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("L'utilisateur: " + username.getText() + " n'existe pas ");
				alert.showAndWait();
				return;
			}
		}

	}

	/**
	 * Méthode de modification d'un utilisateur
	 */
	public void updateuser() {
		Alert alert;
		if (username.getText().isEmpty() || password.getText().isEmpty()
				|| comboboxRole.getSelectionModel().getSelectedItem() == null) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Remplissez tous les champs s'il vous plait ");
			alert.showAndWait();
		} else {
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CONFIRMATION MESSAGE");
			alert.setHeaderText(null);
			alert.setContentText("Êtes vous sures de vouloir modifier les Informations utilisateur: "
					+ username.getText() + " ? Cette action est irreversible");
			Optional<ButtonType> option = alert.showAndWait();
			if (option.get().equals(ButtonType.OK)) {
				for (Admin e : adminDao.getAllAdmin()) {
					if (e.getLogin().equals(username.getText())) {
						e.setLogin(username.getText());
						e.setPassword(password.getText());
						e.setRole(String.valueOf(comboboxRole.getSelectionModel().getSelectedItem()));

						adminDao.updateAdmin(e);
					}
				}

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucess Modification");
				alert.setHeaderText(null);
				alert.setContentText("utilisateur: " + username.getText() + " enregistrer avec success");
				alert.showAndWait();
				userShowList();
				clearuser();

			}
		}

	}

	public void userShowList() {
		addAdminList = adminDao.addAdminList();

		user_name.setCellValueFactory(new PropertyValueFactory<>("login"));
		mot_de_passe.setCellValueFactory(new PropertyValueFactory<>("password"));
		roles.setCellValueFactory(new PropertyValueFactory<>("role"));

		tablrau_user.setItems(addAdminList);
	}

	public void clearuser() {
		username.setText("");
		password.setText("");
		role.setText("");
	}

	public void userSelected() {
		Admin admin = tablrau_user.getSelectionModel().getSelectedItem();

		Integer num = tablrau_user.getSelectionModel().getSelectedIndex();

		if (num - 1 < -1) {
			return;
		}
		username.setText(admin.getLogin());
		password.setText(admin.getPassword());

	}

	public void missionSelected() {
		Ordremission ordremission = tableau_mission.getSelectionModel().getSelectedItem();

		Integer num = tableau_mission.getSelectionModel().getSelectedIndex();

		if (num - 1 < -1) {
			return;
		}
		textidcommande.setText(ordremission.getIdcommandeclient());

		rapport_TXT.setText(ordremission.getRapport());
		nummission.setText(ordremission.getNumeroordremission());
		statut.setText(ordremission.getStatue());

	}

	public void rapportShowList() {
		addOdremissionList = ordremissionDao.addOrdremissionList();

		col_idmission.setCellValueFactory(new PropertyValueFactory<>("Numeroordremission"));
		col_mission.setCellValueFactory(new PropertyValueFactory<>("Rapport"));

		tableau_rapport.setItems(addOdremissionList);
	}

	/**
	 * Méthode servant à ajouter une ligne de commande client
	 */
	public void addLigneCmd() {
		Alert alert;
		Contenir2 ligneCmdClient = new Contenir2();
		Contenir2Id id = new Contenir2Id();
		if (txtIdcmdClient.getText().isEmpty() || text_quantité.getText().isEmpty()
				|| comboBoxProduit.getSelectionModel().getSelectedItem() == null || textdevise.getText().isEmpty()
				|| text_moyenpaiement.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Remplissez tous les champs s'il vous plait ");
			alert.showAndWait();
		} else {
			int idProd = 0;
			id.setIdcommandeclient(txtIdcmdClient.getText());
			String val = (String) comboBoxProduit.getSelectionModel().getSelectedItem();
			for (Produit e : produitDao.getAllProduit()) {
				if (e.getNomproduit().equalsIgnoreCase(val)) {
					idProd = e.getIdproduit();
				}
			}
			id.setIdproduit(idProd);
			
			Produit prod = produitDao.getProduitById(idProd);
			
			Float cout = prod.getPrixunitaire() * Integer.parseInt(text_quantité.getText());

			ligneCmdClient.setId(id);
			ligneCmdClient.setIdlignecommande(txtIdLignecmdClient.getText());
			ligneCmdClient.setQuantitearticle(Integer.parseInt(text_quantité.getText()));
			ligneCmdClient.setCout(cout);
			
			lignecmdDao.saveLigneCommandeClient(ligneCmdClient);
			System.out.println("===Enregistremment Effectuer");
//			showLigneCmdClients();
		}
	
	}

	
//	public void showLigneCmdClients() {
//		addLigneCmdClientsList = lignecmdDao.addLigneCommandeClientList();
//
//		TableLigneCmd_col_IdCmd.setCellValueFactory(new PropertyValueFactory<>("idcommandeclient"));
//		TableLigneCmd_col_IdLigneCmd.setCellValueFactory(new PropertyValueFactory<>("idlignecommande"));
//		TableLigneCmd_col_Produit.setCellValueFactory(new PropertyValueFactory<>("idproduit"));
//		TableLigneCmd_col_Quantite.setCellValueFactory(new PropertyValueFactory<>("quantitearticle"));
//		TableLigneCmd_col_Cout.setCellValueFactory(new PropertyValueFactory<>("cout"));
//
//		TableLigneCmd.setItems(addLigneCmdClientsList);
//		
//	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		userShowList();
		addRoleComboBox();
		missionShowList();
		addComboBoxtransporteur();
		rapportShowList();
		addComboBoxProduit();
//		showLigneCmdClients();

//				addComboBoxProduit();
//				addComboBoxUniteMesure();
//				addComboBoxDevice();
	}
}
