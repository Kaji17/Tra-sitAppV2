package com.TransitApp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.TransitApp.Modeles.Contenir;
import com.TransitApp.Modeles.Contenir1;
import com.TransitApp.Util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class MouvementProduitDao implements IMouvementProduitDao {

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
    public void saveMouvementProduit(Contenir mvnProd) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.save(mvnProd);

            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#getStudentById(long)
     */
    @Override
    public Contenir getMouvementProduitById(int id) {
        Transaction transaction = null;
        Contenir mvnProd = null;
        try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			    // start the transaction
			    transaction = session.beginTransaction();

			    // get student object
			    mvnProd = session.byId(Contenir.class).getReference(id);
			     // or student = session.get(Student.class, id);
			    //or student = session.load(Student.class, id);
			   //or commit the transaction
			    transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
		}
			
		}
        
        
        return mvnProd;
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#getAllStudents()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List < Contenir > getMouvementProduit() {
        Transaction transaction = null;
        List < Contenir > mvnProd = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
            mvnProd = session.createQuery("from Contenir").list();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return mvnProd;
    }
    
    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#getAllStudents()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List < Contenir > getAllMouvementProduit() {
        Transaction transaction = null;
        List < Contenir > mvnProd = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get produit
            mvnProd = session.createQuery("from Contenir").list();
           
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return mvnProd;
    }
}
	
	
//	/**
//	 * méthode permettant de recuperer les fournisseur dans la base de donnée 
//	 * @author Kaji17
//	 */
//	public ObservableList<Contenir1> addFournisseurList() {
//
//		ObservableList<Contenir1> listligneCmdFourn = FXCollections.observableArrayList();
//
//		String sql = "SELECT * FROM contenir1";
//
//		connect = Database.connectDb();
//
//		try {
//
//			Contenir1 ligneCmdFourn;
//
//			prepare = connect.prepareStatement(sql);
//
//			result = prepare.executeQuery();
//
////			while (result.next()) {
////				ligneCmdFourn = new Contenir1(null, sql, null, null);
////				listFournisseurs.add(fournisseur);
////			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return listligneCmdFourn;
//	}
//	

   
