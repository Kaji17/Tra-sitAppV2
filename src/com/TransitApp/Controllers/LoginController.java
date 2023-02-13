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

import javafx.animation.FadeTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

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

	@FXML
	private AnchorPane CardView;

	private double x;

	private double y;
	
    @FXML
    private Pane slideView1;

    @FXML
    private Pane slideView2;

    @FXML
    private Pane slideView3;

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
						adminDao.Login("../Views/Supertransport.fxml", ConnectBtn);
					}
				}
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addRoleComboBox();
		
//		sliderAnimation();

	}
	
	public void sliderAnimation() {
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5),slideView3);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.play();
		
		fadeTransition.setOnFinished(event ->{
			FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(5),slideView2);
			fadeTransition1.setFromValue(1);
			fadeTransition1.setToValue(0);
			fadeTransition1.play();
			
			fadeTransition1.setOnFinished(event1 ->{
				FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(5),slideView1);
				fadeTransition2.setFromValue(1);
				fadeTransition2.setToValue(0);
				fadeTransition2.play();
				
				fadeTransition2.setOnFinished(event2 ->{
					FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(5),slideView1);
					fadeTransition3.setFromValue(1);
					fadeTransition3.setToValue(0);
					fadeTransition3.play();
					
					fadeTransition3.setOnFinished(event3 ->{
						FadeTransition fadeTransition4 = new FadeTransition(Duration.seconds(5),slideView2);
						fadeTransition4.setFromValue(1);
						fadeTransition4.setToValue(0);
						fadeTransition4.play();
						
						fadeTransition4.setOnFinished(event4 ->{
							FadeTransition fadeTransition5 = new FadeTransition(Duration.seconds(5),slideView3);
							fadeTransition5.setFromValue(1);
							fadeTransition5.setToValue(0);
							fadeTransition5.play();
							
							fadeTransition5.setOnFinished(event5 ->{
								sliderAnimation();
						});

					});
				});
			});
		});
		
	});


	}}
