package com.TransitApp.Dao;

import java.util.List;

import com.TransitApp.Modeles.Fournisseur;

public interface IFournisseurDao {

	void saveFournisseur(Fournisseur fournisseur);

	void updateFournisseur(Fournisseur fournisseur);

	Fournisseur getFournisseurById(int id);

	List<Fournisseur> getAllFournisseur();

	void deleteFournisseur(int id);

	List<Fournisseur> rechercher(String nom);

}
