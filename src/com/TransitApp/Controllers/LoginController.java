package com.TransitApp.Controllers;

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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
	
	/**
	 * Méthode appélé lors de la connexion
	 */
	public void loginAdmin() {
		Alert alert;
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
					} else if (val.equals("Respo_Reapro")) {
						adminDao.Login("../Views/RespoReaproHome.fxml", ConnectBtn);
					} else if (val.equals("Transporteur")) {
						adminDao.Login("../Views/Transportsuper.fxml", ConnectBtn);
					}
				}
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addRoleComboBox();

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
