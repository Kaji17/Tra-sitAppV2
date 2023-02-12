package com.TransitApp;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	private double x;
	private double y;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Views/manager.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("Ressources/Styles/NavigationButton.css").toExternalForm());
			primaryStage.setScene(scene);
			
			Image image = new Image("com/TransitApp/icons8-package-48.png");
			primaryStage.getIcons().add(image);
			
			//Permet de faire bouger la fenetre et d'éviter de la redimensionn cer 
			root.setOnMousePressed((MouseEvent event)->{
				x = event.getSceneX();
				y= event.getSceneY();
			});
			
			root.setOnMouseDragged((MouseEvent event)->{
				primaryStage.setX(event.getScreenX() - x);
				primaryStage.setY(event.getScreenY() - y);
			});
			
			//Permet de faire disparatraire la barre du haut
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
