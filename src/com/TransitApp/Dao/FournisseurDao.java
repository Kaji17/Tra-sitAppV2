package com.TransitApp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.TransitApp.Modeles.Fournisseur;
import com.TransitApp.Util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;


public class FournisseurDao implements IFournisseurDao {

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
    public void saveFournisseur(Fournisseur fournisseur) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.save(fournisseur);

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
    public void updateFournisseur(Fournisseur fournisseur) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.saveOrUpdate(fournisseur);

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
    public Fournisseur getFournisseurById(int id) {
        Transaction transaction = null;
        Fournisseur fournisseur = null;
        try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			    // start the transaction
			    transaction = session.beginTransaction();

			    // get student object
			    fournisseur = session.byId(Fournisseur.class).getReference(id);
			     // or student = session.get(Student.class, id);
			    //or student = session.load(Student.class, id);
			   //or commit the transaction
			    transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
		}
			
		}
        
        
        return fournisseur;
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#getAllStudents()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List < Fournisseur > getAllFournisseur() {
        Transaction transaction = null;
        List < Fournisseur > fournisseur = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
            fournisseur = session.createQuery("from Fournisseur").list();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return fournisseur;
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#deleteStudent(long)
     */
    @Override
    public void deleteFournisseur(int id) {
        Transaction transaction = null;
        Fournisseur fournisseur = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            fournisseur = session.get(Fournisseur.class, id);
            // get student object
            session.delete(fournisseur);
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

	@Override
	public List< Fournisseur > rechercher(String nom) {
		Transaction transaction = null;
        List < Fournisseur > fournisseur = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
           // students = session.createQuery("from Student").list();
            fournisseur = session.createSQLQuery(nom).addEntity(getClass()).list();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
		return null;
	}
	
	/**
	 * méthode permettant de donner une coleur transparente au background des
	 * buttons passés en paramètre
	 * 
	 * @param btn1 Button
	 * @param btn2 Button
	 * @param btn3 button
	 * @return void
	 * @author pevir
	 */
	public void removeStyleBtn(Button btn1, Button btn2, Button btn3, Button btn4) {
		btn1.setStyle("-fx-background-color: transparent");
		btn2.setStyle("-fx-background-color: transparent");
		btn3.setStyle("-fx-background-color: transparent");
		btn4.setStyle("-fx-background-color: transparent");
	}
	
	/**
	 * méthode permettant d'ajouter une couleur au choix au background d'un boutton
	 * 
	 * @param btn   Button au quel on veut ajouter la couleur
	 * @param color String couleur choisit en exadecimale ex: #34a39c
	 * @author Kaji17
	 */
	public void addStyle(Button btn, String color) {
		btn.setStyle("-fx-background-color:" + color);
	}
	
	
	/**
	 * méthode permettant de recuperer les fournisseur dans la base de donnée 
	 * @author Kaji17
	 */
	public ObservableList<Fournisseur> addFournisseurList() {

		ObservableList<Fournisseur> listFournisseurs = FXCollections.observableArrayList();

		String sql = "SELECT * FROM fournisseur";

		connect = Database.connectDb();

		try {

			Fournisseur fournisseur;

			prepare = connect.prepareStatement(sql);

			result = prepare.executeQuery();

			while (result.next()) {
				fournisseur = new Fournisseur(result.getInt("idfournisseur"), result.getString("nomfournisseur"), result.getString("adressefournisseur"), result.getString("villefournisseur"), result.getString("cpfournisseur"), result.getString("emailfournisseur"), result.getString("telephonefournisseur"), result.getString("paysfournisseur"));
				listFournisseurs.add(fournisseur);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listFournisseurs;
	}
    
    
}
