package com.TransitApp.Controllers;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ManagerController {
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

}
