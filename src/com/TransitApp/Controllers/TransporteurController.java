package com.TransitApp.Controllers;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import com.TransitApp.Controllers.ManagerController;
import com.TransitApp.Dao.IOrdremissionDao;
import com.TransitApp.Dao.ITransporteurDao;
import com.TransitApp.Dao.OrdremissionDao;
import com.TransitApp.Dao.TransporteurDao;
import com.TransitApp.Modeles.Admin;
import com.TransitApp.Modeles.Fournisseur;
import com.TransitApp.Modeles.Ordremission;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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

public class  TransporteurController implements Initializable {

    @FXML
    private Button ButtonAgrandissement;

    @FXML
    private Button ButtonDeleteRAPPORTt;

    @FXML
    private Button ButtonDeletetransporteur;
    
    IOrdremissionDao ordremissionDao = new OrdremissionDao();

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
    private TableView<Transporteur> tableau_conducteur;

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
    
	private ObservableList<Ordremission> addOdremissionList;


    @FXML
    private TextField text_prenom;

    @FXML
    private TextArea text_rapport;

    @FXML
    private TextField text_salaire;
    
    @FXML
    private TextField text_id_mission;
    
    @FXML
    private ComboBox<?> comboboxmission;
    
    @FXML
    private TextField text_idadmin;
    
    ITransporteurDao TransporteurDao = new TransporteurDao();
    
    private  ObservableList<Transporteur> addTransporteurList;
    
    ManagerController managerController= new ManagerController();
    
    


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
				transportshowList();
				

			}
			transportshowList();
			System.out.println(verif);
			
		}
    }
    
    public void deletetransport() {
		Alert alert;
		if (text_nom.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Selectionner ou entrer un nom de transporteur");
			Optional<ButtonType> option = alert.showAndWait();
		} else {
			// Vérifie l'existance d'un fournisseur
			Boolean verif = false;
			int id = 0;
			for (Transporteur e : TransporteurDao.getAllTransporteur()) {
				if (e.getNomtransporteur().equalsIgnoreCase(text_nom.getText())) {
					verif = true;
					id = e.getIdadmin();
				}
			}
			if (verif == true) {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CONFIRMATION MESSAGE");
				alert.setHeaderText(null);
				alert.setContentText("Êtes vous sures de vouloir supprimer le transporteur: " + text_nom.getText()
						+ " ? Cette action est irreversible");
				Optional<ButtonType> option = alert.showAndWait();
				if (option.get().equals(ButtonType.OK)) {
					TransporteurDao.deleteTransporteur(id);
					transportshowList();
					

				}
			} else {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Le transporteur: " + text_nom.getText() + " n'existe pas ");
				alert.showAndWait();
				return;
			}
		}

		
	}
    
   
    

		
		
		public  void  transportshowList() { 
			
			addTransporteurList = TransporteurDao.addTransporteurList();

			nom_transporteur.setCellValueFactory(new PropertyValueFactory<>("nomtransporteur"));
			idtransporteur.setCellValueFactory(new PropertyValueFactory<>("idtransporteur"));
			id_admin.setCellValueFactory(new PropertyValueFactory<>("idadmin"));
			prenom_transporteur.setCellValueFactory(new PropertyValueFactory<>("prenomtrnsporteur"));
			civilite.setCellValueFactory(new PropertyValueFactory<>("civilite"));
			date_fin.setCellValueFactory(new PropertyValueFactory<>("datefinembauche"));
			date_embauche.setCellValueFactory(new PropertyValueFactory<>("datedebutembauche"));
			salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
			fonction.setCellValueFactory(new PropertyValueFactory<>("fonction"));

			tableau_conducteur.setItems(addTransporteurList);
		}
		
		public void transportSelected() {
			Transporteur transporteur = tableau_conducteur.getSelectionModel().getSelectedItem();

			Integer num = tableau_conducteur.getSelectionModel().getSelectedIndex();

			if (num - 1 < -1) {
				return;
			}
			
			text_nom.setText(transporteur.getNomtransporteur());
			text_prenom.setText(transporteur.getPrenomtrnsporteur());
			text_fonction.setText(transporteur.getFonction());
			text_civilité.setText(transporteur.getCivilite());
			

		}
		
		/*public void addComboBoxmission() {
			List<String> missionList = new ArrayList<>();

			for (Ordremission data : ordremissionDao.getAllOrdremission()) {
				missionList.add(data.getNumeroordremission());
			}

			ObservableList oblist = FXCollections.observableArrayList(missionList);
			comboboxmission.setItems(oblist);
		}*/
	
		public void addrapport() {
			Alert alert;
			Ordremission ordremission = new Ordremission();
			if (text_id_mission.getText().isEmpty() || text_rapport.getText().isEmpty() ) {
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
						+ text_id_mission.getText() + " ? Cette action est irreversible");
				Optional<ButtonType> option = alert.showAndWait();
				if (option.get().equals(ButtonType.OK)) {
					for (Ordremission e : ordremissionDao.getAllOrdremission()) {
						if (e.getNumeroordremission().equals(text_id_mission)) {
							e.setRapport(text_rapport.getText());
							
							

							ordremissionDao.updateordremission(e);
						}
					}

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Sucess Modification");
					alert.setHeaderText(null);
					alert.setContentText("utilisateur: " + text_id_mission.getText() + " enregistrer avec success");
					alert.showAndWait();
					

				}
			}

		}
		
		
		
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			 transportshowList();
			 transportSelected();
			
			
		}
		
		
		}	



