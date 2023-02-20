package com.TransitApp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.TransitApp.Modeles.Commandeclient;
import com.TransitApp.Modeles.Contenir2;
import com.TransitApp.Modeles.Contenir2Id;
import com.TransitApp.Modeles.Fournisseur;
import com.TransitApp.Util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;


public class LigneCommandeClientDao implements ILigneCommandeClientDao {

	private Connection connect;

	private PreparedStatement prepare;

	private ResultSet result;

    // save Student
    // get All Students
    // get Student By Id
    // Update Student
    // Delete Student

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#saveStudent(net.javaguides.hibernate.model.Student)
     */
    @Override
    public void saveLigneCommandeClient(Contenir2 lignecmdClient) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.save(lignecmdClient);

            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

//    /* (non-Javadoc)
//     * @see net.javaguides.hibernate.dao.IStudentDao#updateStudent(net.javaguides.hibernate.model.Student)
//     */
//    @Override
//    public void updateFournisseur(Fournisseur fournisseur) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            // start the transaction
//            transaction = session.beginTransaction();
//
//            // save student object
//            session.saveOrUpdate(fournisseur);
//
//            // commit the transaction
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        }
//    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#getStudentById(long)
     */
    @Override
    public Contenir2 getLigneCommandeClientById(int id) {
        Transaction transaction = null;
        Contenir2 lignecmdClient = null;
        try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			    // start the transaction
			    transaction = session.beginTransaction();

			    // get student object
			    lignecmdClient   = session.byId(Contenir2.class).getReference(id);
			     // or student = session.get(Student.class, id);
			    //or student = session.load(Student.class, id);
			   //or commit the transaction
			    transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
		}
			
		}
        
        
        return lignecmdClient;
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#getAllStudents()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List < Contenir2 > getAllLigneCommandeclient() {
        Transaction transaction = null;
        List < Contenir2 > LignecmdClient = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
            LignecmdClient = session.createQuery("from Contenir2").list();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return LignecmdClient;
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#deleteStudent(long)
     */
    @Override
    public void deleteLigneCommandeclient(int id) {
        Transaction transaction = null;
        Contenir2 LignecdmClient = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            LignecdmClient = session.get(Contenir2.class, id);
            // get student object
            session.delete(LignecdmClient);
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
	
	
	/**
	 * méthode permettant de recuperer les fournisseur dans la base de donnée 
	 * @author Kaji17
	 */
	public ObservableList<Contenir2> addLigneCommandeClientList() {

		ObservableList listLignecmdClients = FXCollections.observableArrayList();

		String sql = "SELECT * FROM contenir2";
		

		connect = Database.connectDb();

		try {

			Contenir2 lignecdmClient;

			prepare = connect.prepareStatement(sql);

			result = prepare.executeQuery();
		
			while (result.next()) {
				listLignecmdClients.add(result.getInt("idproduit"), 
						result.getString("idcommandeclient"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listLignecmdClients;
	}
    
    
}
