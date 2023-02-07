package com.TransitApp.Dao;

import java.util.List;

import com.TransitApp.Modeles.Produit;

import javafx.collections.ObservableList;

public interface IProduitDao {

	void saveProduit(Produit produit);

	void updateProduit(Produit produit);

	Produit getProduitById(int id);

	List<Produit> getAllProduit();

	void deleteProduit(int id);
	
	ObservableList<Produit> addProduitList();

}
