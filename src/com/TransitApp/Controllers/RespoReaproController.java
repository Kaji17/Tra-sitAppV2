package com.TransitApp.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.TransitApp.Dao.CategorieDao;
import com.TransitApp.Dao.CommandeFournisseurDao;
import com.TransitApp.Dao.EntrepotDao;
import com.TransitApp.Dao.FournisseurDao;
import com.TransitApp.Dao.ICategorieDao;
import com.TransitApp.Dao.ICommandeFournisseurDao;
import com.TransitApp.Dao.IEntrepotDao;
import com.TransitApp.Dao.IFournisseurDao;
import com.TransitApp.Dao.IMouvementProduitDao;
import com.TransitApp.Dao.IProduitDao;
import com.TransitApp.Dao.MouvementProduitDao;
import com.TransitApp.Dao.ProduitDao;
import com.TransitApp.Modeles.Categorie;
import com.TransitApp.Modeles.Contenir;
import com.TransitApp.Modeles.ContenirId;
import com.TransitApp.Modeles.Entrepot;
import com.TransitApp.Modeles.Fournisseur;
import com.TransitApp.Modeles.Produit;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
	private Button ButtonAddProduit;

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
	private Button ButtonDeleteProduit;

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
	private Button ButtonModifyProduit;

	@FXML
	private Button ButtonSupressionCmdFOurn;

	@FXML
	private ComboBox<String> ComboBoxCategorieProduit;

	@FXML
	private ComboBox<?> ComboBoxDevise;

	@FXML
	private ComboBox<?> ComboBoxFournisseur;

	@FXML
	private ComboBox<?> ComboBoxUniteMesure;

	@FXML
	private TableColumn<?, ?> LigneCmd_col_NomFournisseur;

	@FXML
	private TableColumn<?, ?> LigneCmd_col_NumeroCommande;

	@FXML
	private TableColumn<?, ?> LigneCmd_col_NumeroProduit;

	@FXML
	private TableColumn<?, ?> LigneCmd_col_PU;

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
	private TableColumn<?, ?> ListProduit_col_Categorie;

	@FXML
	private TableColumn<?, ?> ListProduit_col_DescriptionProduit;

	@FXML
	private TableColumn<?, ?> ListProduit_col_IdProduit;

	@FXML
	private TableColumn<?, ?> ListProduit_col_NomProduit;

	@FXML
	private TableColumn<?, ?> ListProduit_col_NumeroProduit;

	@FXML
	private TableColumn<?, ?> ListProduit_col_PoidsProduit;

	@FXML
	private TableColumn<?, ?> ListProduit_col_PrixUnitaireProduit;

	@FXML
	private TableColumn<?, ?> ListProduit_col_QuantiteStock;

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
	private TableView<Produit> TableViewListProduit;

	@FXML
	private TextField TxtAdresseFourn;

	@FXML
	private TextField TxtNumeroCmdFournisseur;

	@FXML
	private TextField TxtUniteMesure;

	@FXML
	private TextField TxtCPFourn;

	@FXML
	private TextArea TxtDescriptionProduit;

	@FXML
	private TextField TxtNomFourn;

	@FXML
	private TextField TxtNomProduit;

	@FXML
	private TextField TxtNomproduit;

	@FXML
	private TextField TxtNumeroProduit;

	@FXML
	private TextField TxtPaysFourn;

	@FXML
	private TextField TxtPoidProduit;

	@FXML
	private TextField TxtPoidsProduit;

	@FXML
	private TextField TxtPrixUnitaire;

	@FXML
	private TextField TxtQuantiteProduit;

	@FXML
	private TextField TxtSearch;

	@FXML
	private TextField TxtTelephone;

	@FXML
	private TextField TxtVilleFourn;

	@FXML
	private Button buttonCommandeFourn;

	@FXML
	private Button logoutBtn;

	@FXML
	private ComboBox<?> ComboBoxNomproduit;

	@FXML
	private TextField searchProduit;

	private ObservableList<Fournisseur> addFournisseurList;

	IFournisseurDao fournisseurDao = new FournisseurDao();

	ICategorieDao categorieDao = new CategorieDao();

	IProduitDao produitDao = new ProduitDao();

	IMouvementProduitDao ligneCmdFournDao = new MouvementProduitDao();

	ICommandeFournisseurDao cmdFournDao = new CommandeFournisseurDao();

	IMouvementProduitDao mouvProdDao = new MouvementProduitDao();

	IEntrepotDao entrepotDao = new EntrepotDao();

	private Connection connect;

	private PreparedStatement prepare;

	private ResultSet result;

	@FXML
	private TableView<Fournisseur> TableListFourn;

	private double x;

	private double y;

	private ObservableList<Produit> addproduitrList;

	private String[] UniteMesure = { "T", "Kg", "G" };

	private String[] Device = { "EUR", "FCFA", "USD" };

	@FXML
	private Button ButtonAddEntrepot;

	@FXML
	private Button ButtonDeleteEntrepot;

	@FXML
	private TableColumn<?, ?> ListEntrepot_col_CapaciteStock;

	@FXML
	private TableColumn<?, ?> ListEntrepot_col_DateEntree;

	@FXML
	private TableColumn<?, ?> ListEntrepot_col_DateSortie;

	@FXML
	private TableColumn<?, ?> ListEntrepot_col_IdEntrepot;

	@FXML
	private TableColumn<?, ?> ListEntrepot_col_IdEntrepot1ProdS;

	@FXML
	private TableColumn<?, ?> ListEntrepot_col_NomEntrepot;

	@FXML
	private TableColumn<?, ?> ListEntrepot_col_NomProd;

	@FXML
	private TableColumn<?, ?> ListEntrepot_col_Quantite;

	@FXML
	private TableColumn<?, ?> ListEntrepot_col_UniteMesureStcok;
	@FXML
	private Label NomEntrepotSelect;
	

    @FXML
    private ComboBox<?> ComboBoxEntrepot;

	@FXML
	private TableView<Entrepot> TableListEntrepot;

	@FXML
	private TableView<?> TableListProdInEntrepot;

	@FXML
	private TextField TxtCapaciteStockage;

	@FXML
	private TextField TxtNomEntrepot;

	@FXML
	private TextField TxtNumeroEntrepot;

	@FXML
	private Button addComboBoxFournisseur;

	@FXML
	private TextField TxtUniteMesurePoid;

	private  ObservableList<Entrepot> addEntrepotList;

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
				Parent root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				Image image = new Image("com/TransitApp/icons8-package-48.png");
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
		if (event.getSource() == ButtonGestionArticle) {
			PageGestionArticle.setVisible(true);
			PageGestionEntrepot.setVisible(false);
			PageGestionFournisseur.setVisible(false);
			addComboBoxCategorie();
			ProduitShowList();
			addComboBoxProduit();

		} else if (event.getSource() == ButtonGestionEntrepot) {
			PageGestionEntrepot.setVisible(true);
			PageGestionArticle.setVisible(false);
			PageGestionFournisseur.setVisible(false);

		} else if (event.getSource() == ButtonGestionFourn) {
			PageGestionFournisseur.setVisible(true);
			PageGestionArticle.setVisible(false);
			PageGestionEntrepot.setVisible(false);
		}

	}

	// ______________________________
	//
	// FOURNISSEUR
	// ______________________________

	//
	// ***********FOURNISSEUR***********
	//
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
				for (Fournisseur e : fournisseurDao.getAllFournisseur()) {
					if (e.getNomfournisseur().equals(TxtNomFourn.getText())) {
						e.setNomfournisseur(TxtNomFourn.getText());
						e.setAdressefournisseur(TxtAdresseFourn.getText());
						e.setPaysfournisseur(TxtPaysFourn.getText());
						e.setVillefournisseur(TxtVilleFourn.getText());
						e.setCpfournisseur(TxtCPFourn.getText());
						e.setEmailfournisseur(TXtEmailFourn.getText());
						e.setTelephonefournisseur(TxtTelephone.getText());
						fournisseurDao.updateFournisseur(e);
					}
				}

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

	// ______________________________
	//
	// PRODUIT
	// ______________________________

	//
	// ***********PRODUIT***********
	//
	/**
	 * Méthode permettant d'ajouter les Produit en BD
	 * 
	 * @param event ActionEvent
	 * @return void
	 * @author kaji17
	 */
	public void addProduit() {
		Alert alert;
		Produit produit = new Produit();
		if (TxtNumeroProduit.getText().isEmpty() || TxtNomProduit.getText().isEmpty()
				|| TxtPoidProduit.getText().isEmpty() || TxtPrixUnitaire.getText().isEmpty()
				|| TxtQuantiteProduit.getText().isEmpty()
				|| ComboBoxCategorieProduit.getSelectionModel().getSelectedItem() == null
				|| ComboBoxFournisseur.getSelectionModel().getSelectedItem() == null) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Remplissez tous les champs s'il vous plait ");
			alert.showAndWait();
		} else {
			Boolean verif = false;
			for (Produit e : produitDao.getAllProduit()) {
				if (e.getNumeroproduit().equalsIgnoreCase(TxtNumeroProduit.getText())) {
					verif = true;
				}
			}
			if (verif == true) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText(
						"Le Produit: " + TxtNumeroProduit.getText() + " exite déja. Entrer un autre nom Produit");
				alert.showAndWait();
			} else {
				produit.setNumeroproduit(TxtNumeroProduit.getText());
				produit.setNomproduit(TxtNomProduit.getText());
				produit.setPoids(Float.parseFloat(TxtPoidProduit.getText()));
				produit.setPrixunitaire(Float.parseFloat(TxtPrixUnitaire.getText()));
				produit.setDescription(TxtDescriptionProduit.getText());
				produit.setPoidunitemesurecode(TxtUniteMesurePoid.getText());
				produit.setQuantite(Integer.parseInt(TxtQuantiteProduit.getText()));

				Date date = new Date();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());

				produit.setDateajout(sqlDate);

				// Récuperer l'id de la categorie selectionner
				int id = 0;
				String val = (String) ComboBoxCategorieProduit.getSelectionModel().getSelectedItem();
				for (Categorie e : categorieDao.getAllCategorie()) {
					if (e.getLibelecategorie().equalsIgnoreCase(val)) {
						id = e.getIdcategorie();
					}
				}
				produit.setIdcategorie(id);

				// Récuperer l'id du fournisseur selectionner
				int id1 = 0;
				String val1 = (String) ComboBoxEntrepot.getSelectionModel().getSelectedItem();
				for (Entrepot e : entrepotDao.getAllEntrepot()) {
					if (e.getNomentrepot().equalsIgnoreCase(val1)) {
						id1 = e.getIdentrepot();
					}
				}
				produit.setIdfournisseur(id1);
				produitDao.saveProduit(produit);

				int idProd = produit.getIdproduit();

				Contenir mouvProd = new Contenir();

				mouvProd = addMouvProdAdd(idProd, id1, produit.getDateajout());

				mouvProdDao.saveMouvementProduit(mouvProd);

				System.out.println("===Enregistremment Effectuer");
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucess Save");
				alert.setHeaderText(null);
				alert.setContentText("Produit: " + TxtNomProduit.getText() + " enregistrer avec success");
				alert.showAndWait();
				clearProduit();
				ProduitShowList();
				addComboBoxProduit();
				addComboBoxEntrepot();
			}
			FournisseurShowList();
			System.out.println(verif);
		}

	}

	/**
	 * Méthode permettant de modifier les Produit en BD
	 * 
	 * @param event ActionEvent
	 * @return void
	 * @author kaji17
	 */
	public void updateProduit() {
		Alert alert;
		if (TxtNumeroProduit.getText().isEmpty() || TxtNomProduit.getText().isEmpty()
				|| TxtPoidProduit.getText().isEmpty() || TxtPrixUnitaire.getText().isEmpty()
				|| TxtUniteMesurePoid.getText().isEmpty()
				|| ComboBoxCategorieProduit.getSelectionModel().getSelectedItem() == null
				|| ComboBoxEntrepot.getSelectionModel().getSelectedItem() == null) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Remplissez tous les champs s'il vous plait ");
			alert.showAndWait();
		} else {
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CONFIRMATION MESSAGE");
			alert.setHeaderText(null);
			alert.setContentText("Êtes vous sures de vouloir modifier les Informations du produit: "
					+ TxtNumeroProduit.getText() + " ? Cette action est irreversible");
			Optional<ButtonType> option = alert.showAndWait();
			if (option.get().equals(ButtonType.OK)) {
				for (Produit e : produitDao.getAllProduit()) {
					if (e.getNumeroproduit().equals(TxtNumeroProduit.getText())) {
						e.setNomproduit(TxtNomProduit.getText());
						e.setPoids(Float.parseFloat(TxtPoidProduit.getText()));
						e.setDescription(TxtDescriptionProduit.getText());
						e.setPrixunitaire(Float.parseFloat(TxtPrixUnitaire.getText()));
						int id = 0;
						String val = (String) ComboBoxCategorieProduit.getSelectionModel().getSelectedItem();
						for (Categorie ex : categorieDao.getAllCategorie()) {
							if (ex.getLibelecategorie().equalsIgnoreCase(val)) {
								id = ex.getIdcategorie();
							}
						}
						e.setIdcategorie(id);
						e.setPoidunitemesurecode(TxtUniteMesurePoid.getText());
						produitDao.updateProduit(e);
					}
				}

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucess Modification");
				alert.setHeaderText(null);
				alert.setContentText("Produit: " + TxtNomProduit.getText() + " enregistrer avec success");
				alert.showAndWait();
				clearProduit();

				ProduitShowList();
			}
		}

	}

	/**
	 * 
	 * Méthode permettant de supprimer un Produit par son id
	 * 
	 * @param event ActionEvent
	 * @return void
	 * @author kaji17
	 */
	public void deleteProduit() {
		Alert alert;
		if (TxtNumeroProduit.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Selectionner ou entrer un nom de Numero Produit");
			Optional<ButtonType> option = alert.showAndWait();
		} else {
			// Vérifie l'existance d'un fournisseur
			Boolean verif = false;
			int id = 0;
			for (Produit e : produitDao.getAllProduit()) {
				if (e.getNumeroproduit().equals(TxtNumeroProduit.getText())) {
					verif = true;
					id = e.getIdproduit();
				}
			}
			if (verif == true) {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CONFIRMATION MESSAGE");
				alert.setHeaderText(null);
				alert.setContentText("Êtes vous sures de vouloir supprimer le produit: " + TxtNumeroProduit.getText()
						+ " ? Cette action est irreversible");
				Optional<ButtonType> option = alert.showAndWait();
				if (option.get().equals(ButtonType.OK)) {
					produitDao.deleteProduit(id);
					ProduitShowList();
					clearProduit();
				}
			} else {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Le produit: " + TxtNumeroProduit.getText() + " n'existe pas ");
				alert.showAndWait();
				return;
			}
		}
		ProduitShowList();
		clearProduit();

	}

	/**
	 * Méthode permettant ajouter les Produit dans le tableau
	 * 
	 */
	public void ProduitShowList() {
		addproduitrList = produitDao.addProduitList();

		ListProduit_col_IdProduit.setCellValueFactory(new PropertyValueFactory<>("idproduit"));
		ListProduit_col_NumeroProduit.setCellValueFactory(new PropertyValueFactory<>("numeroproduit"));
		ListProduit_col_NomProduit.setCellValueFactory(new PropertyValueFactory<>("nomproduit"));
		ListProduit_col_PoidsProduit.setCellValueFactory(new PropertyValueFactory<>("poids"));
		ListProduit_col_DescriptionProduit.setCellValueFactory(new PropertyValueFactory<>("idfournisseur"));
		ListProduit_col_PrixUnitaireProduit.setCellValueFactory(new PropertyValueFactory<>("prixunitaire"));
		ListProduit_col_QuantiteStock.setCellValueFactory(new PropertyValueFactory<>("quantite"));
		ListProduit_col_Categorie.setCellValueFactory(new PropertyValueFactory<>("idcategorie"));

		TableViewListProduit.setItems(addproduitrList);
	}

	/**
	 * Méthode permettant de remettre à zero tout les champs d'enregistrement
	 * Produit
	 * 
	 * @author pevir
	 */
	public void clearProduit() {
		TxtNumeroProduit.setText("");
		TxtNomProduit.setText("");
		TxtDescriptionProduit.setText("");
		TxtPoidProduit.setText("");
		TxtPrixUnitaire.setText("");
		TxtQuantiteProduit.setText("");
		TxtUniteMesure.setText("");
		ComboBoxCategorieProduit.setSelectionModel(null);
		ComboBoxEntrepot.setSelectionModel(null);
		ComboBoxFournisseur.setSelectionModel(null);
	}

	/**
	 * methode pour remplir la combobox des différents categorie
	 * 
	 * @return void
	 * @author Kaji17
	 */
	public void addComboBoxCategorie() {
		List<String> CategorieList = new ArrayList<>();

		for (Categorie data : categorieDao.getAllCategorie()) {
			CategorieList.add(data.getLibelecategorie());
		}

		ObservableList oblist = FXCollections.observableArrayList(CategorieList);
		ComboBoxCategorieProduit.setItems(oblist);
	}

	/**
	 * methode pour remplir la combobox des différents produit
	 * 
	 * @return void
	 * @author Kaji17
	 */
	public void addComboBoxProduit() {
		List<String> ProduitList = new ArrayList<>();

		for (Produit data : produitDao.getAllProduit()) {
			ProduitList.add(data.getNomproduit());
		}

		ObservableList oblist = FXCollections.observableArrayList(ProduitList);
		ComboBoxNomproduit.setItems(oblist);
	}

	/**
	 * methode pour remplir la combobox des différents Unite de Mesure
	 * 
	 * @return void
	 * @author Kaji17
	 */
	public void addComboBoxUniteMesure() {
		List<String> UniteMesureList = new ArrayList<>();

		for (String data : UniteMesure) {
			UniteMesureList.add(data);
		}

		ObservableList oblist = FXCollections.observableArrayList(UniteMesureList);
		ComboBoxUniteMesure.setItems(oblist);
	}

	/**
	 * methode pour remplir la combobox des différents device
	 * 
	 * @return void
	 * @author Kaji17
	 */
	public void addComboBoxDevice() {
		List<String> DeviceList = new ArrayList<>();

		for (String data : Device) {
			DeviceList.add(data);
		}

		ObservableList oblist = FXCollections.observableArrayList(DeviceList);
		ComboBoxDevise.setItems(oblist);
	}

	/**
	 * méthode permetant de remplir les différent champs d'enregistrment produit
	 * lorsque un champs du table views est selectionner
	 * 
	 * @return void
	 * @author Kaji17
	 */
	public void produitSelected() {
		Produit produit = TableViewListProduit.getSelectionModel().getSelectedItem();

		Integer num = TableViewListProduit.getSelectionModel().getSelectedIndex();

		if (num - 1 < -1) {
			return;
		}
		TxtNomProduit.setText(produit.getNomproduit());
		TxtNumeroProduit.setText(produit.getNumeroproduit());
		TxtDescriptionProduit.setText(produit.getDescription());
		TxtPoidProduit.setText(String.valueOf(produit.getPoids()));
		TxtPrixUnitaire.setText(String.valueOf(produit.getPrixunitaire()));
		TxtUniteMesurePoid.setText(produit.getPoidunitemesurecode());
		TxtQuantiteProduit.setText(String.valueOf(produit.getQuantite()));
		

	}

	/**
	 * Méthode permettant de creer un historique d'enregistrement des produits dans
	 * un entrepot
	 * 
	 * @param idProd     Id du produit ajouter
	 * @param idEntrepot Id de l'entrepot selectionner
	 * @param dateEntrer La date d'ajout du produit
	 * @return
	 * 
	 * @author Kaji17
	 */
	public Contenir addMouvProdAdd(int idProd, int idEntrepot, Date dateEntrer) {
		ContenirId id = new ContenirId(idProd, idEntrepot);

		Contenir mouvProd = new Contenir(id, dateEntrer, null);

		return mouvProd;

	}

	//
	// ***********ENTREPOT***********
	//

	/**
	 * Méthode permettant d'ajouter les entrepot en BD
	 * @return void
	 * @author kaji17
	 */
	public void addEntrepot() {
		Alert alert;
		Entrepot entrepot = new Entrepot();
		if (TxtNomEntrepot.getText().isEmpty() || TxtNumeroEntrepot.getText().isEmpty() || TxtCapaciteStockage.getText().isEmpty()
				|| TxtUniteMesure.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Remplissez tous les champs s'il vous plait ");
			alert.showAndWait();
		} else {
			Boolean verif = false;
			for (Entrepot e : entrepotDao.getAllEntrepot()) {
				if (e.getNomentrepot().equalsIgnoreCase(TxtNomEntrepot.getText())) {
					verif = true;
				}
			}
			if (verif == true) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText(
						"L'entrepot : " + TxtNomEntrepot.getText() + " exite déja. Entrer un autre nom d'Entrepot");
				alert.showAndWait();
			} else {
				entrepot.setNomentrepot(TxtNomEntrepot.getText());
				entrepot.setNumeroentrepot(TxtNumeroEntrepot.getText());
				entrepot.setUnitemesurcapacite(TxtUniteMesure.getText());
				entrepot.setCapacitstockage(Float.parseFloat(TxtCapaciteStockage.getText()));

				entrepotDao.saveEntrepot(entrepot);
				System.out.println("===Enregistremment Effectuer");

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucess Save");
				alert.setHeaderText(null);
				alert.setContentText("Entrepot: " + TxtNomEntrepot.getText() + " enregistrer avec success");
				alert.showAndWait();
				clearEntrepot();
				addComboBoxEntrepot();
				EntrepotShowList();
			}
			FournisseurShowList();
			System.out.println(verif);
		}

	}

	/**
	 * 
	 * Méthode permettant de modifier les Entrepots en BD
	 * @return void
	 * @author kaji17
	 */
	public void updateEntrepot() {
		Alert alert;
		if (TxtNomEntrepot.getText().isEmpty() || TxtNumeroEntrepot.getText().isEmpty() || TxtCapaciteStockage.getText().isEmpty()
				|| TxtUniteMesure.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Remplissez tous les champs s'il vous plait ");
			alert.showAndWait();
		} else {
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CONFIRMATION MESSAGE");
			alert.setHeaderText(null);
			alert.setContentText("Êtes vous sures de vouloir modifier les Informations entrepot : "
					+ TxtNomEntrepot.getText() + " ? Cette action est irreversible");
			Optional<ButtonType> option = alert.showAndWait();
			if (option.get().equals(ButtonType.OK)) {
				for (Entrepot e : entrepotDao.getAllEntrepot()) {
					if (e.getNomentrepot().equals(TxtNomEntrepot.getText())) {
						e.setNumeroentrepot(TxtNumeroEntrepot.getText());
						e.setCapacitstockage(Float.parseFloat(TxtCapaciteStockage.getText()));
						e.setUnitemesurcapacite(TxtUniteMesure.getText());
						entrepotDao.updateEntrepot(e);
					}
				}

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucess Modification");
				alert.setHeaderText(null);
				alert.setContentText("Entrepot: " + TxtNomEntrepot.getText() + " enregistrer avec success");
				alert.showAndWait();
				clearEntrepot();
				addComboBoxEntrepot();
				EntrepotShowList();
			}
		}

	}

	/**
	 * 
	 * Méthode permettant de supprimer un Entrepot par son id
	 * 
	 * @param event ActionEvent
	 * @return void
	 * @author kaji17
	 */
	public void deleteEntrepot() {
		Alert alert;
		if (TxtNomEntrepot.getText().isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Selectionner ou entrer un nom d' entrepot");
			Optional<ButtonType> option = alert.showAndWait();
		} else {
			// Vérifie l'existance d'un fournisseur
			Boolean verif = false;
			int id = 0;
			for (Entrepot e : entrepotDao.getAllEntrepot()) {
				if (e.getNomentrepot().equalsIgnoreCase(TxtNomEntrepot.getText())) {
					verif = true;
					id = e.getIdentrepot();
				}
			}
			if (verif == true) {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("CONFIRMATION MESSAGE");
				alert.setHeaderText(null);
				alert.setContentText("Êtes vous sures de vouloir supprimer l'entrepot: " + TxtNomEntrepot.getText()
						+ " ? Cette action est irreversible");
				Optional<ButtonType> option = alert.showAndWait();
				if (option.get().equals(ButtonType.OK)) {
					entrepotDao.deleteEntrepot(id);
					clearEntrepot();
					EntrepotShowList();
				}
			} else {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("L'entrepot : " + TxtNomEntrepot.getText() + " n'existe pas ");
				alert.showAndWait();
				return;
			}
		}

	}

	/**
	 * Méthode permettant ajouter les Entrepot dans le tableau
	 * 
	 */
	public void EntrepotShowList() {
		addEntrepotList = entrepotDao.addEntrepotList();

		ListEntrepot_col_IdEntrepot.setCellValueFactory(new PropertyValueFactory<>("identrepot"));
		ListEntrepot_col_NomEntrepot.setCellValueFactory(new PropertyValueFactory<>("nomentrepot"));
		ListEntrepot_col_CapaciteStock.setCellValueFactory(new PropertyValueFactory<>("capacitstockage"));
		ListEntrepot_col_UniteMesureStcok.setCellValueFactory(new PropertyValueFactory<>("unitemesurcapacite"));
		
		TableListEntrepot.setItems(addEntrepotList);
	}

	/**
	 * Méthode permettant de remettre à zero tout les champs d'enregistrement
	 * Entrepot
	 * 
	 * @author pevir
	 */
	public void clearEntrepot() {
		TxtNomEntrepot.setText("");
		TxtNumeroEntrepot.setText("");
		TxtCapaciteStockage.setText("");
		TxtUniteMesure.setText("");
	}

	/**
	 * methode pour remplir la combobox des différents Entrepot
	 * 
	 * @return void
	 * @author Kaji17
	 */
	public void addComboBoxEntrepot() {
		List<String> EntrepotList = new ArrayList<>();

		for (Entrepot data : entrepotDao.getAllEntrepot()) {
			EntrepotList.add(data.getNomentrepot());
		}

		ObservableList oblist = FXCollections.observableArrayList(EntrepotList);
		ComboBoxEntrepot.setItems(oblist);
	}

	/**
	 * méthode permetant de remplir les différent champs d'enregistrment Entrepot
	 * lorsque un champs du table views est selectionner
	 * 
	 * @return void
	 * @author Kaji17
	 */
	public void entrepotSelected() {
		Entrepot entrepot= TableListEntrepot.getSelectionModel().getSelectedItem();

		Integer num = TableListEntrepot.getSelectionModel().getSelectedIndex();

		if (num - 1 < -1) {
			return;
		}
		TxtNomEntrepot.setText(entrepot.getNomentrepot());
		TxtNumeroEntrepot.setText(entrepot.getNumeroentrepot());
		TxtCapaciteStockage.setText(String.valueOf(entrepot.getCapacitstockage()));
		TxtUniteMesure.setText(entrepot.getUnitemesurcapacite());
	}

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FournisseurShowList();
		addComboBoxFournisseur();
		addComboBoxCategorie();
		addComboBoxEntrepot();
		ProduitShowList();
		EntrepotShowList();
//		addComboBoxProduit();
//		addComboBoxUniteMesure();
//		addComboBoxDevice();
	}

}
