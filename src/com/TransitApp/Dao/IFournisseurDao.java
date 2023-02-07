package com.TransitApp.Dao;

import java.util.List;

import com.TransitApp.Modeles.Fournisseur;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public interface IFournisseurDao {

	// Ajouter un fournisseur dans la base de donnée
	void saveFournisseur(Fournisseur fournisseur);

	// Modifier les informations du fournisseur
	void updateFournisseur(Fournisseur fournisseur);

	// Recuperer un fournisseur par id
	Fournisseur getFournisseurById(int id);

	// Retourne la liste des fournisseur
	List<Fournisseur> getAllFournisseur();

	void deleteFournisseur(int id);

	List<Fournisseur> rechercher(String nom);

	void removeStyleBtn(Button btn1, Button btn2, Button btn3, Button btn4);
	
	void addStyle(Button btn, String color);
	
	//  méthode permettant de recuperer la liste des fournisseur dans la base de donnée 
	ObservableList<Fournisseur> addFournisseurList();

}
