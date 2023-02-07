package com.TransitApp.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.TransitApp.Modeles.Admin;
import com.TransitApp.Modeles.Categorie;
import com.TransitApp.Modeles.Fournisseur;
import com.TransitApp.Util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CategorieDao implements ICategorieDao {

	private Connection connect;

	private PreparedStatement prepare;

	private ResultSet result;

	private double x;

	private double y;

	// save Student
	// get All Students
	// get Student By Id
	// Update Student
	// Delete Student

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.javaguides.hibernate.dao.IStudentDao#saveStudent(net.javaguides.hibernate
	 * .model.Student)
	 */
	@Override
	public void saveCategorie(Categorie categorie) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();

			// save student object
			session.save(categorie);

			// commit the transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.javaguides.hibernate.dao.IStudentDao#updateStudent(net.javaguides.
	 * hibernate.model.Student)
	 */
	@Override
	public void updateCategorie(Categorie categorie) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();

			// save student object
			session.saveOrUpdate(categorie);

			// commit the transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.javaguides.hibernate.dao.IStudentDao#getStudentById(long)
	 */
	@Override
	public Categorie getCategorieById(int id) {
		Transaction transaction = null;
		Categorie categorie = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// start the transaction
			transaction = session.beginTransaction();

			// get student object
			categorie = session.byId(Categorie.class).getReference(id);
			// or student = session.get(Student.class, id);
			// or student = session.load(Student.class, id);
			// or commit the transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

		}

		return categorie;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.javaguides.hibernate.dao.IStudentDao#getAllStudents()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Categorie> getAllCategorie() {
		Transaction transaction = null;
		List<Categorie> categorie = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();

			// get admin
			categorie = session.createQuery("from Categorie").list();

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return categorie;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.javaguides.hibernate.dao.IStudentDao#deleteStudent(long)
	 */
	@Override
	public void deleteCategorie(int id) {
		Transaction transaction = null;
		Categorie categorie = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();

			categorie = session.get(Categorie.class, id);
			// get Categorie object
			session.delete(categorie);
			// student = session.load(Student.class, id);
			// commit the transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	


}

/*
 * public List<Student > recupererCommandeByDateAndMenu(String idMenu , Date
 * dateCommande){ String query =
 * "SELECT `commande`.`DATECOMMANDE` FROM `commande` WHERE ((`commande`.`ID_MENU` = "
 * +idMenu+") AND (`commande`.`DATECOMMANDE` ='"+dateCommande+"'))"; //String
 * query = "SELECT * FROM `` WHERE ((`CODETYPE_LOGEMENT`='"
 * +typeLogement+"') AND (`CODE_TYPENATIONALITE` ='"
 * +typeNationalite+"') AND (`CODE_ANNEES`='"+codeAnne+"'))";
 * 
 * Commande commande = session.createSQLQuery().addEntity(getClass()).list();
 * (TypeLogementNationalite)
 * getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(
 * TypeLogementNationalite.class).uniqueResult(); return object; }
 */
