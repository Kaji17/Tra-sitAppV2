package com.TransitApp.Dao;

import java.util.List;

import com.TransitApp.Modeles.Transporteur;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public interface ITransporteurDao {

	void saveTransporteur(Transporteur transporteur);

	void updateTransporteur(Transporteur transporteur);

	Transporteur getTransporteurById(int id);

	void deleteTransporteur(int id);

	List<Transporteur> getAllTransporteur();

	List<Transporteur> rechercher(String nom);
	
	ObservableList<Transporteur> addTransporteurList();
	void Login(String fxml, Button button);
}
