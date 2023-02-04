package com.TransitApp.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.TransitApp.Dao.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
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
	
    private String[] Role = {"Manager", "Transporteur", "Respo_Reapro"};

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
		String sql = "SELECT * FROM admin WHERE LOGIN=?, PASSWORD=?, ROLE=?";
		
		connect = Database.connectDb();
		
		try {
			Alert alert;
			
			prepare = connect.prepareStatement(sql);
			
			prepare.setString(1, LoginTextfield.getText());
			
			prepare.setString(2, MotDePasseText.getText());
			
			prepare.setString(2, (String) RoleComboBox.getSelectionModel().getSelectedItem());

			result = prepare.executeQuery();
			
			if(LoginTextfield.getText().isEmpty() || MotDePasseText.getText().isEmpty() || RoleComboBox.getSelectionModel().getSelectedItem() == null) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields ");
				alert.showAndWait();
				
			}else if(result.next()){
				System.out.println("Succed connection");
				
				alert = new Alert(AlertType.INFORMATION);

				alert.setTitle("INFORMATION MESSAGE");

				alert.setHeaderText(null);

				alert.setContentText("Successfull login!");

				alert.showAndWait();
				
				ConnectBtn.getScene().getWindow().hide();
				
				if(RoleComboBox.getSelectionModel().getSelectedItem() == "Manager") {
					Parent root = FXMLLoader.load(getClass().getResource("../Views/manager.fxml"));

					Stage stage = new Stage();

					Scene scene = new Scene(root);
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
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
