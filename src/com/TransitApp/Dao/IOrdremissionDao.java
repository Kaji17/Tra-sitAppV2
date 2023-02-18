package com.TransitApp.Dao;

import java.util.List;

import com.TransitApp.Modeles.Ordremission;

import javafx.collections.ObservableList;

public interface IOrdremissionDao {
	
	void saveOrdremission(Ordremission ordremission);

	

	Ordremission getOrdremissionById(int id);

	

	void deleteOrdremission(int id);

	List < Ordremission > getAllOrdremission();

	List<Ordremission> rechercher(String nom);
	
	ObservableList<Ordremission> addOrdremissionList();

	void updateordremission(Ordremission ordremission);
}
