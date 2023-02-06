package com.TransitApp.Controllers;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class ManagerController {

    @FXML
    private Button ajouter;

    @FXML
    private Button boutton_gestion_commandes;

    @FXML
    private Button boutton_gestionmission;

    @FXML
    private Button boutton_stat;

    @FXML
    private Button boutton_suivi;

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
    private AnchorPane statistiques;
    
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
			return;}

	public void switchForm(ActionEvent event) {
		if (event.getSource() == boutton_gestion_commandes) {
			page_ajout_commande.setVisible(true);
			statistiques.setVisible(false);
			page_ajout_mission.setVisible(false);
			suivi.setVisible(false);
			
		}
//			
	 
		else if (event.getSource() == boutton_gestionmission) {
			page_ajout_commande.setVisible(false);
			statistiques.setVisible(false);
			page_ajout_mission.setVisible(true);
			suivi.setVisible(false);
			
		}
		
		else if (event.getSource() == boutton_stat ) {
			page_ajout_commande.setVisible(false);
			statistiques.setVisible(true);
			page_ajout_mission.setVisible(false);
		    suivi.setVisible(false);
//			
	}
		else if (event.getSource() == boutton_suivi ) {
			page_ajout_commande.setVisible(false);
			statistiques.setVisible(false);
			page_ajout_mission.setVisible(false);
			suivi.setVisible(true);
//			
	}
	}
	}
