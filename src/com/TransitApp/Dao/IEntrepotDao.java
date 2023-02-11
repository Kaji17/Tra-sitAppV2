package com.TransitApp.Dao;

import java.util.List;

import com.TransitApp.Modeles.Entrepot;

public interface IEntrepotDao {

	void saveEntrepot(Entrepot entrepot);

	void updateEntrepot(Entrepot entrepot);

	void deleteEntrepot(int id);

	List<Entrepot> getAllEntrepot();

	Entrepot getEntrepotById(int id);

}
