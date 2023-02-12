package com.TransitApp.Controllers;

import java.awt.Image;
import java.awt.Label;
import java.awt.event.MouseEvent;
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
import com.TransitApp.Dao.IOrdremissionDao;
import com.TransitApp.Dao.OrdremissionDao;
import com.TransitApp.Modeles.Ordremission;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ManagerController {

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
	private TextArea entrerDescription;

	@FXML
	private TextField entrer_id;

	@FXML
	private TextField entrer_nom;

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

	IOrdremissionDao ordremissionDao = new OrdremissionDao();

	@FXML
	private AnchorPane suivi;

	@FXML
	private Button supprimer;

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

			addStyle(boutton_gestion_commandes, "#34a39c");

			removeStyleBtn(boutton_gestionmission, boutton_suivi, adduser);
		}

		else if (event.getSource() == adduser) {
			page_ajout_commande.setVisible(false);
			page_ajout_mission.setVisible(false);
			suivi.setVisible(false);
			ajoutuser.setVisible(true);

			addStyle(adduser, "#34a39c");
			removeStyleBtn(boutton_gestion_commandes, boutton_gestionmission, boutton_suivi);
		}

		else if (event.getSource() == boutton_gestionmission) {
			page_ajout_commande.setVisible(false);
			page_ajout_mission.setVisible(true);
			suivi.setVisible(false);
			ajoutuser.setVisible(false);

			addStyle(boutton_gestionmission, "#34a39c");
			removeStyleBtn(boutton_gestion_commandes, boutton_suivi, adduser);
		}

//			

		else if (event.getSource() == boutton_suivi) {
			page_ajout_commande.setVisible(false);
			page_ajout_mission.setVisible(false);
			suivi.setVisible(true);
			ajoutuser.setVisible(false);

			addStyle(boutton_suivi, "#34a39c");
			removeStyleBtn(boutton_gestion_commandes, boutton_gestionmission, adduser);

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
		if (textidcommande.getText().isEmpty() || nummission.getText().isEmpty() || text_idmission.getText().isEmpty()
				|| statut.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Remplissez tous les champs s'il vous plait ");
			alert.showAndWait();
		} else {
			Boolean verif = false;
			for (Ordremission e : ordremissionDao.getAllOrdremission()) {
				if (e.getIdordremission() == Integer.parseInt(text_idmission.getText())) {
					verif = true;
				}
			}
			if (verif == true) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText(
						"La mission: " + text_idmission.getText() + " exite déja. Entrer un autre id de mission");
				alert.showAndWait();
			} else {
				ordremission.setRapport(statut.getText());
				ordremission.setIdcommandeclient(textidcommande.getText());
				ordremission.setNumeroordremission(nummission.getText());

				ordremission.setStatue(statut.getText());

				ordremissionDao.saveOrdremission(ordremission);
				System.out.println("===Enregistremment Effectuer");

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucess Save");
				alert.setHeaderText(null);
				alert.setContentText("mission: " + text_idmission.getText() + " enregistrer avec success");

			}

			System.out.println(verif);
		}

	}

}
