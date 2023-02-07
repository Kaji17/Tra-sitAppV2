package com.TransitApp.Controllers;

import java.awt.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.TransitApp.Dao.FournisseurDao;
import com.TransitApp.Dao.IFournisseurDao;
import com.TransitApp.Modeles.Fournisseur;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RespoReaproController implements Initializable {

	@FXML
	private Button ButtonAddCmdFourn;

	@FXML
	private Button ButtonAddFourn;

	@FXML
	private Button ButtonAgrandissement;

	@FXML
	private Button ButtonAjouterCmdFourn;

	@FXML
	private Button ButtonAjouterComdFourn;

	@FXML
	private Button ButtonDeleteCmdFourn;

	@FXML
	private Button ButtonDeleteFourn;

	@FXML
	private Button ButtonEnregistrerCmd;

	@FXML
	private Button ButtonGestionArticle;

	@FXML
	private Button ButtonGestionEntrepot;

	@FXML
	private Button ButtonGestionFourn;

	@FXML
	private Button ButtonMinimisize;

	@FXML
	private Button ButtonModifyCmdFourn;

	@FXML
	private Button ButtonModifyFourn;

	@FXML
	private Button ButtonSupressionCmdFOurn;

	@FXML
	private ComboBox<?> ComboBoxDevise;

	@FXML
	private ComboBox<?> ComboBoxFournisseur;

	@FXML
	private ComboBox<?> ComboBoxUniteMesure;

	@FXML
	private Label LabelDevise;

	@FXML
	private Label LabelIdResposReapro;

	@FXML
	private TableColumn<?, ?> LigneCmd_col_Designation;

	@FXML
	private TableColumn<?, ?> LigneCmd_col_NumeroProduit;

	@FXML
	private TableColumn<?, ?> LigneCmd_col_PU;

	@FXML
	private TableColumn<?, ?> LigneCmd_col_Poids;

	@FXML
	private TableColumn<?, ?> LigneCmd_col_Quantite;

	@FXML
	private TableColumn<?, ?> LigneCmd_col_Total;

	@FXML
	private TableColumn<?, ?> ListFourn_col_AdresFourn;

	@FXML
	private TableColumn<?, ?> ListFourn_col_EmailFourn;

	@FXML
	private TableColumn<?, ?> ListFourn_col_IdFourn;

	@FXML
	private TableColumn<?, ?> ListFourn_col_NomFourn;

	@FXML
	private TableColumn<?, ?> ListFourn_col_PaysFourn;

	@FXML
	private TableColumn<?, ?> ListFourn_col_Telephone;

	@FXML
	private AnchorPane PageAjoutCommandeFourn;

	@FXML
	private AnchorPane PageCommandeFourn;

	@FXML
	private AnchorPane PageGestionArticle;

	@FXML
	private AnchorPane PageGestionEntrepot;

	@FXML
	private AnchorPane PageGestionFournisseur;

	@FXML
	private TextField PrixAchat;

	@FXML
	private TextField TXtEmailFourn;

	@FXML
	private TableView<Fournisseur> TableListFourn;

	@FXML
	private TableView<?> TableViewComdFourn;

	@FXML
	private TableView<?> TableViewLigneCmd;

	@FXML
	private TextField TxtAdresseFourn;

	@FXML
	private Label TxtCOutTotal;

	@FXML
	private TextField TxtCPFourn;

	@FXML
	private TextField TxtNomFourn;

	@FXML
	private Label TxtNomRespoReapro;

	@FXML
	private TextField TxtNomproduit;

	@FXML
	private TextField TxtPaysFourn;

	@FXML
	private TextField TxtPoidsProduit;

	@FXML
	private TextField TxtQuantiteProduit;

	@FXML
	private TextField TxtSearch;

	@FXML
	private TextField TxtTelephone;

	@FXML
	private TextField TxtVilleFourn;

	@FXML
	private TableColumn<?, ?> ViewCmdFourn_col_DateCmd;

	@FXML
	private TableColumn<?, ?> ViewCmdFourn_col_DateLivraison;

	@FXML
	private TableColumn<?, ?> ViewCmdFourn_col_NomFourn;

	@FXML
	private TableColumn<?, ?> ViewCmdFourn_col_NumCmd;

	@FXML
	private TableColumn<?, ?> ViewCmdFourn_col_NumRespoReapro;

	@FXML
	private TableColumn<?, ?> ViewCmdFourn_col_Total;

	@FXML
	private TableColumn<?, ?> ViewCmdFourn_col_idLigneCmd;

	@FXML
	private Button buttonCommandeFourn;

	@FXML
	private Button logoutBtn;

	private ObservableList<Fournisseur> addFournisseurList;

	IFournisseurDao fournisseurDao = new FournisseurDao();

	private Connection connect;

	private PreparedStatement prepare;

	private ResultSet result;

	private double x;

	private double y;

	/**
	 * methode pour fermer la fenetre
	 * 
	 * @return void
	 */
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

	/**
	 * méthode pour se déconnecter de l'application
	 */
	public void logout() {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CONFIRMATION MESSAGE");
			alert.setHeaderText(null);
			alert.setContentText("Êtes vous sure de vouloir vous déconnecter?");
			Optional<ButtonType> option = alert.showAndWait();

			if (option.get().equals(ButtonType.OK)) {

//				Permet de cacher la fenetre du dashboard
				logoutBtn.getScene().getWindow().hide();
				Parent root = FXMLLoader.load(getClass().getResource("../Views/LoginV2.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.initStyle(StageStyle.TRANSPARENT);

				// Permet de faire bouger la fenetre et d'éviter de la redimensionner
				root.setOnMousePressed((MouseEvent event) -> {
					x = event.getSceneX();
					y = event.getSceneY();
				});

				root.setOnMouseDragged((MouseEvent event) -> {
					stage.setX(event.getScreenX() - x);
					stage.setY(event.getScreenY() - y);
				});

				stage.show();
			} else
				return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Méthode permettant d'afficher les différent menu du Dashboard en cachant les
	 * autres non concerné
	 * 
	 * @param event ActionEvent
	 * @return void
	 * 
	 */
	public void switchForm(ActionEvent event) {
		if (event.getSource() == buttonCommandeFourn) {
			PageCommandeFourn.setVisible(true);
			PageAjoutCommandeFourn.setVisible(false);
			PageGestionArticle.setVisible(false);
			PageGestionEntrepot.setVisible(false);
			PageGestionFournisseur.setVisible(false);

//			Ajouter la coleur #34a39c au background du button passer en paramètre
			fournisseurDao.addStyle(buttonCommandeFourn, "#cd2e2e");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			fournisseurDao.removeStyleBtn(ButtonAjouterComdFourn, ButtonGestionArticle, ButtonGestionEntrepot,
					ButtonGestionFourn);

		} else if (event.getSource() == ButtonAjouterComdFourn || event.getSource() == ButtonAddCmdFourn) {
			PageAjoutCommandeFourn.setVisible(true);
			PageCommandeFourn.setVisible(false);
			PageGestionArticle.setVisible(false);
			PageGestionEntrepot.setVisible(false);
			PageGestionFournisseur.setVisible(false);

			addComboBoxFournisseur();

//			Ajouter la coleur #34a39c au background du button passer en paramètre
			fournisseurDao.addStyle(ButtonAjouterComdFourn, "#cd2e2e");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			fournisseurDao.removeStyleBtn(buttonCommandeFourn, ButtonGestionArticle, ButtonGestionEntrepot,
					ButtonGestionFourn);

		} else if (event.getSource() == ButtonGestionArticle) {
			PageGestionArticle.setVisible(true);
			PageCommandeFourn.setVisible(false);
			PageAjoutCommandeFourn.setVisible(false);
			PageGestionEntrepot.setVisible(false);
			PageGestionFournisseur.setVisible(false);

//			Ajouter la coleur #34a39c au background du button passer en paramètre
			fournisseurDao.addStyle(ButtonGestionArticle, "#cd2e2e");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			fournisseurDao.removeStyleBtn(buttonCommandeFourn, ButtonAjouterComdFourn, ButtonGestionEntrepot,
					ButtonGestionFourn);

		} else if (event.getSource() == ButtonGestionEntrepot) {
			PageGestionEntrepot.setVisible(true);
			PageCommandeFourn.setVisible(false);
			PageAjoutCommandeFourn.setVisible(false);
			PageGestionArticle.setVisible(false);
			PageGestionFournisseur.setVisible(false);

//			Ajouter la coleur #34a39c au background du button passer en paramètre
			fournisseurDao.addStyle(ButtonGestionEntrepot, "#cd2e2e");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			fournisseurDao.removeStyleBtn(buttonCommandeFourn, ButtonAjouterComdFourn, ButtonGestionArticle,
					ButtonGestionFourn);

		} else if (event.getSource() == ButtonGestionFourn) {
			PageGestionFournisseur.setVisible(true);
			PageCommandeFourn.setVisible(false);
			PageAjoutCommandeFourn.setVisible(false);
			PageGestionArticle.setVisible(false);
			PageGestionEntrepot.setVisible(false);

//			Ajouter la coleur #34a39c au background du button passer en paramètre
			fournisseurDao.addStyle(ButtonGestionFourn, "#cd2e2e");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			fournisseurDao.removeStyleBtn(buttonCommandeFourn, ButtonAjouterComdFourn, ButtonGestionArticle,
					ButtonGestionEntrepot);
			FournisseurShowList();

		}

	}

	/**
	 * 
	 * Méthode permettant d'ajouter les fournisseur en BD
	 * 
	 * @param event ActionEvent
	 * @return void
	 * @author kaji17
	 */
	public void addFournisseur() {
		Alert alert;
		Fournisseur fournisseur = new Fournisseur();
		if (TxtNomFourn.getText().isEmpty() || TxtAdresseFourn.getText().isEmpty() || TxtPaysFourn.getText().isEmpty()
				|| TxtVilleFourn.getText().isEmpty() || TxtCPFourn.getText().isEmpty()
				|| TXtEmailFourn.getText().isEmpty() || TxtTelephone.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Remplissez tous les champs s'il vous plait ");
			alert.showAndWait();
		} else {
			Boolean verif = false;
			for (Fournisseur e : fournisseurDao.getAllFournisseur()) {
				if (e.getNomfournisseur().equalsIgnoreCase(TxtNomFourn.getText())) {
					verif = true;
				}
			}
			if (verif == true) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText(
						"Le Fournisseur: " + TxtNomFourn.getText() + " exite déja. Entrer un autre nom Fournisseur");
				alert.showAndWait();
			} else {
				fournisseur.setNomfournisseur(TxtNomFourn.getText());
				fournisseur.setAdressefournisseur(TxtAdresseFourn.getText());
				fournisseur.setPaysfournisseur(TxtPaysFourn.getText());
				fournisseur.setVillefournisseur(TxtVilleFourn.getText());
				fournisseur.setCpfournisseur(TxtCPFourn.getText());
				fournisseur.setEmailfournisseur(TXtEmailFourn.getText());
				fournisseur.setTelephonefournisseur(TxtTelephone.getText());

				fournisseurDao.saveFournisseur(fournisseur);
				System.out.println("===Enregistremment Effectuer");

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucess Save");
				alert.setHeaderText(null);
				alert.setContentText("Fournisseur: " + TxtNomFourn.getText() + " enregistrer avec success");
				alert.showAndWait();
				clearFournisseur();
				addComboBoxFournisseur();
			}
			FournisseurShowList();
			System.out.println(verif);
		}

	}

	/**
	 * 
	 * Méthode permettant de modifier les fournisseur en BD
	 * 
	 * @param event ActionEvent
	 * @return void
	 * @author kaji17
	 */
	public void updateFournisseur() {
		Alert alert;
		Fournisseur fournisseur = new Fournisseur();
		if (TxtNomFourn.getText().isEmpty() || TxtAdresseFourn.getText().isEmpty() || TxtPaysFourn.getText().isEmpty()
				|| TxtVilleFourn.getText().isEmpty() || TxtCPFourn.getText().isEmpty()
				|| TXtEmailFourn.getText().isEmpty() || TxtTelephone.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Remplissez tous les champs s'il vous plait ");
			alert.showAndWait();
		} else {
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CONFIRMATION MESSAGE");
			alert.setHeaderText(null);
			alert.setContentText("Êtes vous sures de vouloir modifier les Informations fournisseur: "
					+ TxtNomFourn.getText() + " ? Cette action est irreversible");
			Optional<ButtonType> option = alert.showAndWait();
			if (option.get().equals(ButtonType.OK)) {
				fournisseur.setNomfournisseur(TxtNomFourn.getText());
				fournisseur.setAdressefournisseur(TxtAdresseFourn.getText());
				fournisseur.setPaysfournisseur(TxtPaysFourn.getText());
				fournisseur.setVillefournisseur(TxtVilleFourn.getText());
				fournisseur.setCpfournisseur(TxtCPFourn.getText());
				fournisseur.setEmailfournisseur(TXtEmailFourn.getText());
				fournisseur.setTelephonefournisseur(TxtTelephone.getText());
				System.out.println("===Modification Effectuer");

				fournisseurDao.updateFournisseur(fournisseur);

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucess Modification");
				alert.setHeaderText(null);
				alert.setContentText("Fournisseur: " + TxtNomFourn.getText() + " enregistrer avec success");
				alert.showAndWait();
				clearFournisseur();
				addComboBoxFournisseur();
				FournisseurShowList();
			}
		}

	}

	/**
	 * 
	 * Méthode permettant de supprimer un fournisseur par son id
	 * 
	 * @param event ActionEvent
	 * @return void
	 * @author kaji17
	 */
	public void deleteFournisseur() {
		Alert alert;
		if (TxtNomFourn.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Selectionner ou entrer un nom de fournisseur");
			Optional<ButtonType> option = alert.showAndWait();
		} else {
			// Vérifie l'existance d'un fournisseur
			Boolean verif = false;
			int id = 0;
			for (Fournisseur e : fournisseurDao.getAllFournisseur()) {
				if (e.getNomfournisseur().equalsIgnoreCase(TxtNomFourn.getText())) {
					verif = true;
					id = e.getIdfournisseur();
				}
			}
			if (verif == true) {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CONFIRMATION MESSAGE");
				alert.setHeaderText(null);
				alert.setContentText("Êtes vous sures de vouloir supprimer le fournisseur: " + TxtNomFourn.getText()
						+ " ? Cette action est irreversible");
				Optional<ButtonType> option = alert.showAndWait();
				if (option.get().equals(ButtonType.OK)) {
					fournisseurDao.deleteFournisseur(id);
					clearFournisseur();
					FournisseurShowList();
				}
			} else {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Le fournisseur: " + TxtNomFourn.getText() + " n'existe pas ");
				alert.showAndWait();
				return;
			}
		}

	}

	/**
	 * Méthode permettant ajouter les fournisseurs dans le tableau
	 * 
	 */
	public void FournisseurShowList() {
		addFournisseurList = fournisseurDao.addFournisseurList();

		ListFourn_col_IdFourn.setCellValueFactory(new PropertyValueFactory<>("idfournisseur"));
		ListFourn_col_NomFourn.setCellValueFactory(new PropertyValueFactory<>("nomfournisseur"));
		ListFourn_col_PaysFourn.setCellValueFactory(new PropertyValueFactory<>("paysfournisseur"));
		ListFourn_col_EmailFourn.setCellValueFactory(new PropertyValueFactory<>("emailfournisseur"));
		ListFourn_col_Telephone.setCellValueFactory(new PropertyValueFactory<>("telephonefournisseur"));
		ListFourn_col_AdresFourn.setCellValueFactory(new PropertyValueFactory<>("adressefournisseur"));

		TableListFourn.setItems(addFournisseurList);
	}

	/**
	 * Méthode permettant de remettre à zero tout les champs d'enregistrement
	 * fournisseur
	 * 
	 * @author pevir
	 */
	public void clearFournisseur() {
		TxtNomFourn.setText("");
		TxtNomFourn.setText("");
		TxtAdresseFourn.setText("");
		TxtPaysFourn.setText("");
		TxtPaysFourn.setText("");
		TxtVilleFourn.setText("");
		TxtCPFourn.setText("");
		TXtEmailFourn.setText("");
		TxtTelephone.setText("");
	}

	/**
	 * methode pour remplir la combobox des différents fournisseur
	 * 
	 * @return void
	 * @author Kaji17
	 */
	public void addComboBoxFournisseur() {
		List<String> FournisseurList = new ArrayList<>();

		for (Fournisseur data : fournisseurDao.getAllFournisseur()) {
			FournisseurList.add(data.getNomfournisseur());
		}

		ObservableList oblist = FXCollections.observableArrayList(FournisseurList);
		ComboBoxFournisseur.setItems(oblist);
	}

	/**
	 * méthode permetant de remplir les différent champs d'enregistrment fournisseur
	 * lorsque un champs du table views est selectionner
	 * 
	 * @return void
	 * @author Kaji17
	 */
	public void fournisseurSelected() {
		Fournisseur fournisseur = TableListFourn.getSelectionModel().getSelectedItem();

		Integer num = TableListFourn.getSelectionModel().getSelectedIndex();

		if (num - 1 < -1) {
			return;
		}
		TxtNomFourn.setText(fournisseur.getNomfournisseur());
		TxtAdresseFourn.setText(fournisseur.getAdressefournisseur());
		TxtPaysFourn.setText(fournisseur.getPaysfournisseur());
		TxtVilleFourn.setText(fournisseur.getVillefournisseur());
		TxtCPFourn.setText(fournisseur.getCpfournisseur());
		TXtEmailFourn.setText(fournisseur.getEmailfournisseur());
		TxtTelephone.setText(fournisseur.getTelephonefournisseur());

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FournisseurShowList();
		addComboBoxFournisseur();
	}

}
