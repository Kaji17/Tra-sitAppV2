package com.TransitApp.Dao;

import java.util.List;

import com.TransitApp.Modeles.Commandefournisseur;

public interface ICommandeFournisseurDao {

	void saveCommandeFournisseur(Commandefournisseur cmdFourn);

	void updateCommandeFournisseur(Commandefournisseur cmdFourn);

	Commandefournisseur getAdminById(int id);

	List<Commandefournisseur> getAllCommandefournisseur();

	void deleteCommandefournisseur(int id);

}
