package com.TransitApp.Dao;

import java.util.List;

import com.TransitApp.Modeles.Admin;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public interface IAdminDao {

	void saveAdmin(Admin admin);

	void updateAdmin(Admin admin);

	Admin getAdminById(int id);

	List<Admin> getAllAdmin();

	void deleteAdmin(int id);
	
	void Login(String fxml, Button button);

	ObservableList<Admin> addAdminList();

}
