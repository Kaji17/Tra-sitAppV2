package com.TransitApp.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SplashUiController implements Initializable {

	@FXML
	private AnchorPane anchRoot;
	
	@FXML
    private Label count;

    @FXML
    private ProgressBar progressBar;

	private double x;

	private double y;

    void makeProgress(ActionEvent event) {
    	progress(progressBar);
	}
    
    private void progress(ProgressIndicator p) {
    	double value = p.getProgress();
    	if (value < 0) {
			value = 0.1;
		}else {
			value = value + 0.1;
			if (value >= 1.0) {
				value = 1.0;
			}
		}
    	p.setProgress(value);
    	count.setText(Integer.toString((int)Math.round(value * 100))+"%");
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FadeTransition.applyFadeTransition(anchRoot, Duration.seconds(2), (e) ->{
			anchRoot.getScene().getWindow().hide();
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				//Permet de faire bouger la fenetre et d'éviter de la redimensionn cer 
				root.setOnMousePressed((MouseEvent event)->{
					x = event.getSceneX();
					y= event.getSceneY();
				});
				
				root.setOnMouseDragged((MouseEvent event)->{
					stage.setX(event.getScreenX() - x);
					stage.setY(event.getScreenY() - y);
				});
				
				//Permet de faire disparatraire la barre du haut
				stage.initStyle(StageStyle.TRANSPARENT);
				stage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
});
}

}
