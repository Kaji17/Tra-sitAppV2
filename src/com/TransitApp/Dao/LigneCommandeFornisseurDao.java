package com.TransitApp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.TransitApp.Modeles.Contenir1;
import com.TransitApp.Modeles.Fournisseur;
import com.TransitApp.Util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;


public class LigneCommandeFornisseurDao implements ILigneCommandeFornisseurDao {

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
    public void saveLigneCommandeFornisseur(Contenir1 ligneCmdFourn) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.save(ligneCmdFourn);

            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#updateStudent(net.javaguides.hibernate.model.Student)
     */
    @Override
    public void updateLigneCommandeFornisseur(Contenir1 ligneCmdFourn) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.saveOrUpdate(ligneCmdFourn);

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
    public Contenir1 getLigneCommandeFornisseurById(int id) {
        Transaction transaction = null;
        Contenir1 ligneCmdFourn = null;
        try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			    // start the transaction
			    transaction = session.beginTransaction();

			    // get student object
			    ligneCmdFourn = session.byId(Contenir1.class).getReference(id);
			     // or student = session.get(Student.class, id);
			    //or student = session.load(Student.class, id);
			   //or commit the transaction
			    transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
		}
			
		}
        
        
        return ligneCmdFourn;
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#getAllStudents()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List < Contenir1 > getLigneCommandeFornisseur() {
        Transaction transaction = null;
        List < Contenir1 > ligneCmdFourn = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
            ligneCmdFourn = session.createQuery("from Contenir1").list();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return ligneCmdFourn;
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#deleteStudent(long)
     */
    @Override
    public void deleteLigneCommandeFornisseur(int id) {
        Transaction transaction = null;
        Contenir1 ligneCmdFourn = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            ligneCmdFourn = session.get(Contenir1.class, id);
            // get student object
            session.delete(ligneCmdFourn);
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
	public ObservableList<Contenir1> addFournisseurList() {

		ObservableList<Contenir1> listligneCmdFourn = FXCollections.observableArrayList();

		String sql = "SELECT * FROM contenir1";

		connect = Database.connectDb();

		try {

			Contenir1 ligneCmdFourn;

			prepare = connect.prepareStatement(sql);

			result = prepare.executeQuery();

//			while (result.next()) {
//				ligneCmdFourn = new Contenir1(null, sql, null, null);
//				listFournisseurs.add(fournisseur);
//			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listligneCmdFourn;
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
    
    
    
}
