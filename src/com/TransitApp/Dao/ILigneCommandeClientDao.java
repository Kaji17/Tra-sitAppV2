package com.TransitApp.Dao;

import java.util.List;

import com.TransitApp.Modeles.Contenir2;

import javafx.collections.ObservableList;

public interface ILigneCommandeClientDao {

	void saveLigneCommandeClient(Contenir2 lignecmdClient);

	Contenir2 getLigneCommandeClientById(int id);

	List<Contenir2> getAllLigneCommandeclient();

	void deleteLigneCommandeclient(int id);
	
	ObservableList<Contenir2> addLigneCommandeClientList();

}
