package com.TransitApp.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.TransitApp.Dao.AdminDao;
import com.TransitApp.Dao.IAdminDao;
import com.TransitApp.Modeles.Admin;
import com.TransitApp.Modeles.Fournisseur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {

	@FXML
	private Button ConnectBtn;

	@FXML
	private TextField LoginTextfield;

	@FXML
	private PasswordField MotDePasseText;

	@FXML
	private ComboBox<?> RoleComboBox;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnMinimise;

	private Connection connect;

	private PreparedStatement prepare;

	private ResultSet result;

	private String[] Role = { "Manager", "Transporteur", "Respo_Reapro" };

	private double x;

	private double y;

	IAdminDao adminDao = new AdminDao();

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
	 * methode pour remplir la combobox des différents rôle
	 * 
	 * @return void
	 */
	public void addRoleComboBox() {
		List<String> RoleList = new ArrayList<>();

		for (String data : Role) {
			RoleList.add(data);
		}

		ObservableList oblist = FXCollections.observableArrayList(RoleList);
		RoleComboBox.setItems(oblist);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addRoleComboBox();

	}

	public void loginAdmin() {
		Alert alert;
		Fournisseur fournisseur = new Fournisseur();
		if (LoginTextfield.getText().isEmpty() || MotDePasseText.getText().isEmpty()
				|| RoleComboBox.getSelectionModel().getSelectedItem() == null) {
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Remplissez tous les champs s'il vous plait ");
			alert.showAndWait();
		} else {
			Boolean verif = false;
			for (Admin e : adminDao.getAllAdmin()) {
				if (e.getLogin().equals(LoginTextfield.getText()) && e.getPassword().equals(MotDePasseText.getText())) {
					verif = true;
					String val = (String) RoleComboBox.getSelectionModel().getSelectedItem();
					if (val.equals("Manager")) {
						adminDao.Login("../Views/manager.fxml", ConnectBtn);

//						try {
//							Parent root;
//							root = FXMLLoader.load(getClass().getResource("../Views/manager.fxml"));
//
//							Stage stage = new Stage();
//
//							Scene scene = new Scene(root);
//
//							ConnectBtn.getScene().getWindow().hide();
//							// Permet de faire bouger la fenetre et d'éviter de la redimensionner
//							root.setOnMousePressed((MouseEvent event) -> {
//								x = event.getSceneX();
//								y = event.getSceneY();
//							});
//
//							root.setOnMouseDragged((MouseEvent event) -> {
//								stage.setX(event.getScreenX() - x);
//								stage.setY(event.getScreenY() - y);
//							});
//
//							stage.setScene(scene);
//							stage.initStyle(StageStyle.TRANSPARENT);
//
//							stage.show();
//						} catch (IOException error) {
//							// TODO Auto-generated catch block
//							error.printStackTrace();
//						}
					} else if (val.equals("Respo_Reapro")) {
						adminDao.Login("../Views/RespoReaproHome.fxml", ConnectBtn);
					} else if (val.equals("Transporteur")) {
						adminDao.Login("../Views/Transportsuper.fxml", ConnectBtn);
					}
				}
			}
			if(verif = false) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Verifier les informations de connection");
				alert.showAndWait();
			}
		}

//		System.out.println("btnclick");
//		System.out.println((String) RoleComboBox.getSelectionModel().getSelectedItem());
//		try {
//			Parent root;
//			root = FXMLLoader.load(getClass().getResource("../Views/RespoReaproHome.fxml"));
//
//			Stage stage = new Stage();
//
//			Scene scene = new Scene(root);
//
//			ConnectBtn.getScene().getWindow().hide();
//			// Permet de faire bouger la fenetre et d'éviter de la redimensionner
//			root.setOnMousePressed((MouseEvent event) -> {
//				x = event.getSceneX();
//				y = event.getSceneY();
//			});
//
//			root.setOnMouseDragged((MouseEvent event) -> {
//				stage.setX(event.getScreenX() - x);
//				stage.setY(event.getScreenY() - y);
//			});
//
//			stage.setScene(scene);
//			stage.initStyle(StageStyle.TRANSPARENT);
//
//			stage.show();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

//	/**
//	 * méthode pour réduire une fénetre
//	 * 
//	 * @return void
//	 */
//	public void minimize() {
//		Stage stage = (Stage) main_form.getScene().getWindow();
//		stage.setIconified(true);
//	}

}
