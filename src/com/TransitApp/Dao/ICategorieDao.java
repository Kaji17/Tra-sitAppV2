package com.TransitApp.Dao;

import java.util.List;

import com.TransitApp.Modeles.Categorie;

public interface ICategorieDao {

	void saveCategorie(Categorie categorie);

	void updateCategorie(Categorie categorie);

	Categorie getCategorieById(int id);

	void deleteCategorie(int id);

	List<Categorie> getAllCategorie();

}
