package com.TransitApp.Dao;

import java.util.List;

import com.TransitApp.Modeles.Commandeclient;

public interface ICommandeClientDao {

	void saveCommandeClient(Commandeclient cmdClient);

	Commandeclient getCommandeClientById(int id);

	List<Commandeclient> getAllCommandeclient();

	void deleteCommandeclient(int id);

}
