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
import com.TransitApp.Modeles.Fournisseur;
import com.TransitApp.Util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;


public class CommandeClientDao implements ICommandeClientDao {

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
    public void saveCommandeClient(Commandeclient cmdClient) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.save(cmdClient);

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
    public Commandeclient getCommandeClientById(int id) {
        Transaction transaction = null;
        Commandeclient cmdClient = null;
        try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			    // start the transaction
			    transaction = session.beginTransaction();

			    // get student object
			    cmdClient  = session.byId(Commandeclient.class).getReference(id);
			     // or student = session.get(Student.class, id);
			    //or student = session.load(Student.class, id);
			   //or commit the transaction
			    transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
		}
			
		}
        
        
        return cmdClient;
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#getAllStudents()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List < Commandeclient > getAllCommandeclient() {
        Transaction transaction = null;
        List < Commandeclient > cmdClient = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
            cmdClient = session.createQuery("from Commandeclient").list();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return cmdClient;
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#deleteStudent(long)
     */
    @Override
    public void deleteCommandeclient(int id) {
        Transaction transaction = null;
        Commandeclient cdmClient = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            cdmClient = session.get(Commandeclient.class, id);
            // get student object
            session.delete(cdmClient);
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
	public ObservableList<Commandeclient> addCommandeClientList() {

		ObservableList<Commandeclient> listcmdClients = FXCollections.observableArrayList();

		String sql = "SELECT * FROM commandeclient";

		connect = Database.connectDb();

		try {

			Commandeclient cdmClient;

			prepare = connect.prepareStatement(sql);

			result = prepare.executeQuery();

		
			while (result.next()) {
				cdmClient = new Commandeclient(
						result.getString("idcommandeclient"), 
						result.getString("numerocommande"), 
						result.getDate("datecreationcmd"), 
						result.getDate("datelivraison"), 
						result.getBoolean("delivrer"), 
						result.getString("device"), 
						result.getString("moyenpaiement"));
				listcmdClients.add(cdmClient);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listcmdClients;
	}
    
    
}
