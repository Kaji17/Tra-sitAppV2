package com.TransitApp.Dao;

import java.util.List;

import com.TransitApp.Modeles.Contenir;

public interface IMouvementProduitDao {


	void saveMouvementProduit(Contenir mvnProd);

	Contenir getMouvementProduitById(int id);

	List<Contenir> getMouvementProduit();

	List<Contenir> getAllMouvementProduit();

}
