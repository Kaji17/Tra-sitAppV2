package com.TransitApp.Dao;

import java.util.List;

import com.TransitApp.Modeles.Contenir1;

public interface ILigneCommandeFornisseurDao {

	void saveLigneCommandeFornisseur(Contenir1 ligneCmdFourn);

	void updateLigneCommandeFornisseur(Contenir1 ligneCmdFourn);

	Contenir1 getLigneCommandeFornisseurById(int id);

	List<Contenir1> getLigneCommandeFornisseur();

	void deleteLigneCommandeFornisseur(int id);

}
