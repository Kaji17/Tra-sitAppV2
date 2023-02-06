package com.TransitApp.Controllers;

import java.awt.Label;
import java.util.Optional;

import com.TransitApp.Dao.FournisseurDao;
import com.TransitApp.Dao.IFournisseurDao;
import com.TransitApp.Modeles.Fournisseur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RespoReaproController {

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
	private TableView<?> TableListFourn;

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

	IFournisseurDao fournisseurDao = new FournisseurDao();

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
			addStyle(buttonCommandeFourn, "#cd2e2e");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			removeStyleBtn(ButtonAjouterComdFourn, ButtonGestionArticle, ButtonGestionEntrepot, ButtonGestionFourn);

		} else if (event.getSource() == ButtonAjouterComdFourn) {
			PageAjoutCommandeFourn.setVisible(true);
			PageCommandeFourn.setVisible(false);
			PageGestionArticle.setVisible(false);
			PageGestionEntrepot.setVisible(false);
			PageGestionFournisseur.setVisible(false);

//			Ajouter la coleur #34a39c au background du button passer en paramètre
			addStyle(ButtonAjouterComdFourn, "#cd2e2e");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			removeStyleBtn(buttonCommandeFourn, ButtonGestionArticle, ButtonGestionEntrepot, ButtonGestionFourn);

		} else if (event.getSource() == ButtonGestionArticle) {
			PageGestionArticle.setVisible(true);
			PageCommandeFourn.setVisible(false);
			PageAjoutCommandeFourn.setVisible(false);
			PageGestionEntrepot.setVisible(false);
			PageGestionFournisseur.setVisible(false);

//			Ajouter la coleur #34a39c au background du button passer en paramètre
			addStyle(ButtonGestionArticle, "#cd2e2e");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			removeStyleBtn(buttonCommandeFourn, ButtonAjouterComdFourn, ButtonGestionEntrepot, ButtonGestionFourn);

		} else if (event.getSource() == ButtonGestionEntrepot) {
			PageGestionEntrepot.setVisible(true);
			PageCommandeFourn.setVisible(false);
			PageAjoutCommandeFourn.setVisible(false);
			PageGestionArticle.setVisible(false);
			PageGestionFournisseur.setVisible(false);

//			Ajouter la coleur #34a39c au background du button passer en paramètre
			addStyle(ButtonGestionEntrepot, "#cd2e2e");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			removeStyleBtn(buttonCommandeFourn, ButtonAjouterComdFourn, ButtonGestionArticle, ButtonGestionFourn);

		} else if (event.getSource() == ButtonGestionFourn) {
			PageGestionFournisseur.setVisible(true);
			PageCommandeFourn.setVisible(false);
			PageAjoutCommandeFourn.setVisible(false);
			PageGestionArticle.setVisible(false);
			PageGestionEntrepot.setVisible(false);

//			Ajouter la coleur #34a39c au background du button passer en paramètre
			addStyle(ButtonGestionFourn, "#cd2e2e");

//			Ajouter un couleur transparent au backgroud des trois button passer en parametre
			removeStyleBtn(buttonCommandeFourn, ButtonAjouterComdFourn, ButtonGestionArticle, ButtonGestionEntrepot);

		}

	}

	/**
	 * méthode permettant d'ajouter une couleur au choix au background d'un boutton
	 * 
	 * @param btn   Button au quel on veut ajouter la couleur
	 * @param color String couleur choisit en exadecimale ex: #34a39c
	 */
	private void addStyle(Button btn, String color) {
		btn.setStyle("-fx-background-color:" + color);
	}

	/**
	 * méthode permettant de donner une coleur transparente au background des
	 * buttons passés en paramètre
	 * 
	 * @param btn1 Button
	 * @param btn2 Button
	 * @param btn3 button
	 * @return void
	 * @author pevir
	 */
	private void removeStyleBtn(Button btn1, Button btn2, Button btn3, Button btn4) {
		btn1.setStyle("-fx-background-color: transparent");
		btn2.setStyle("-fx-background-color: transparent");
		btn3.setStyle("-fx-background-color: transparent");
		btn4.setStyle("-fx-background-color: transparent");
	}

	
	public void addCommandeFournisseur() {
		Alert alert;
		Fournisseur fournisseur = new Fournisseur();
		if (TxtNomFourn.getText().isEmpty() || TxtAdresseFourn.getText().isEmpty() || TxtPaysFourn.getText().isEmpty()
				|| TxtVilleFourn.getText().isEmpty() || TxtCPFourn.getText().isEmpty()
				|| TXtEmailFourn.getText().isEmpty() || TxtTelephone.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Please fill all blank fields ");
			alert.showAndWait();
		}else {
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
			alert.setContentText("Fournisseur: "+TxtNomFourn.getText()+" enregistrer avec success");
			alert.showAndWait();	
			clearFournisseur();
		}
	}
	
	public void clearFournisseur() {
		TxtNomFourn.setText("");
		TxtAdresseFourn.setText("");
		TxtPaysFourn.setText("");
		TxtVilleFourn.setText("");
		TxtCPFourn.setText("");
		TXtEmailFourn.setText("");
		TxtTelephone.setText("");
	}

}
