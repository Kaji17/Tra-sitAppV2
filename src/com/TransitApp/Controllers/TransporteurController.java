package com.TransitApp.Controllers;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;


import com.TransitApp.Dao.ITransporteurDao;
import com.TransitApp.Dao.TransporteurDao;
import com.TransitApp.Modeles.Transporteur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class  TransporteurController {

    @FXML
    private Button ButtonAgrandissement;

    @FXML
    private Button ButtonDeleteRAPPORTt;

    @FXML
    private Button ButtonDeletetransporteur;

    @FXML
    private Button ButtonGestionconducteurs;

    @FXML
    private Button ButtonMinimisize;

    @FXML
    private Button ButtonModifyRAPPORT;

    @FXML
    private Button ButtonModifytransporteur;

    @FXML
    private Button Buttonredactionrapport;

    @FXML
    private AnchorPane PAGE_RAPPORT;

    @FXML
    private Button addtransporteur;

    @FXML
    private Button addRAPPORT;

    @FXML
    private TextField chercher_conducteur;

    @FXML
    private TextField cherchermission;

    @FXML
    private ChoiceBox<?> choix_idmission;

    @FXML
    private TableColumn<?, ?> civilite;

    @FXML
    private TableColumn<?, ?> date_embauche;

    @FXML
    private TableColumn<?, ?> date_fin;

    @FXML
    private TableColumn<?, ?> daterapport;

    @FXML
    private TableColumn<?, ?> fonction;

    @FXML
    private TableColumn<?, ?> id_admin;

    @FXML
    private TableColumn<?, ?> idmission;

    @FXML
    private TableColumn<?, ?> idtransporteur;

    @FXML
    private TableColumn<?, ?> lieurapport;

    @FXML
    private Button logoutBtn;

    @FXML
    private TableView<?> missions_rapport;

    @FXML
    private TableColumn<?, ?> nom_transporteur;

    @FXML
    private AnchorPane pageGestionconducteurs;

    @FXML
    private TableColumn<?, ?> prenom_transporteur;

    @FXML
    private TableColumn<?, ?> rapport;

    @FXML
    private TableColumn<?, ?> salaire;

    @FXML
    private TableView<?> tableau_conducteur;

    @FXML
    private TextField text_civilité;

    @FXML
    private DatePicker text_dateembauche;

    @FXML
    private DatePicker text_datefin;

    @FXML
    private DatePicker text_daterapport;

    @FXML
    private TextField text_fonction;

    @FXML
    private TextField text_lieurapport;

    @FXML
    private TextField text_nom;

    @FXML
    private TextField text_prenom;

    @FXML
    private TextArea text_rapport;

    @FXML
    private TextField text_salaire;
    
    @FXML
    private TextField text_idadmin;
    
    ITransporteurDao TransporteurDao = new TransporteurDao();


    @FXML
    void switchForm(ActionEvent event) {
    	if (event.getSource() == ButtonGestionconducteurs) {
			pageGestionconducteurs.setVisible(true);
			PAGE_RAPPORT.setVisible(false);
			
		
		} else if (event.getSource() == Buttonredactionrapport) {
			PAGE_RAPPORT.setVisible(true);
			pageGestionconducteurs.setVisible(false);
			
			
		}

    }
    

    @FXML
    void logout(ActionEvent event) {
    	try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CONFIRMATION MESSAGE");
			alert.setHeaderText(null);
			alert.setContentText("Êtes vous sure de vouloir vous déconnecter?");
			Optional<ButtonType> option = alert.showAndWait();

			if (option.get().equals(ButtonType.OK)) {

//				Permet de cacher la fenetre du dashboard
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

    
    
    public void ajouttransporteur() {
		Alert alert;
		Transporteur transporteur  = new Transporteur();
		if (text_nom.getText().isEmpty() || text_prenom.getText().isEmpty()
				 || text_idadmin.getText().isEmpty() || text_fonction.getText().isEmpty()
				|| text_civilité.getText().isEmpty() || text_salaire.getText().isEmpty() ) {
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
				alert.setContentText("Le transporteur: " + text_nom.getText() + " exite déja. Entrer un autre transporteur");
				alert.showAndWait();
			} else {

				
				transporteur.setNomtransporteur(text_nom.getText());
				transporteur.setPrenomtrnsporteur(text_prenom.getText());
				transporteur.setFonction(text_fonction.getText());
				transporteur.setIdadmin(Integer.parseInt(text_idadmin.getText()));
				transporteur.setCivilite(text_civilité.getText());
				transporteur.setSalaire(Float.parseFloat(text_salaire.getText()));
				
				Date date = new Date();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				transporteur.setDatedebutembauche(sqlDate);
				transporteur.setDatefinembauche(null);
				

				TransporteurDao.saveTransporteur(transporteur);
				System.out.println("===Enregistremment Effectuer");

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucess Save");
				alert.setHeaderText(null);
				alert.setContentText("transporteur: " + text_nom.getText() + " enregistrer avec success");
				alert.showAndWait();
				

			}
			
			System.out.println(verif);
		}
	}



}
