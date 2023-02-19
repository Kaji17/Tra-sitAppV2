package com.TransitApp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.TransitApp.Modeles.Admin;
import com.TransitApp.Modeles.Ordremission;
import com.TransitApp.Util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OrdremissionDao implements IOrdremissionDao{
	
	private Connection connect;

	private PreparedStatement prepare;

	private ResultSet result;
	 // save Student
    // get All Students
    // get Student By Id
    // Update Student
    // Delete Student

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#saveStudent(net.javaguides.hibernate.model.Student)*/
    @Override
    public void saveOrdremission(Ordremission ordremission) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.save(ordremission);

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
    public void updateordremission(Ordremission ordremission) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.saveOrUpdate(ordremission);

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
    public Ordremission getOrdremissionById(int id) {
        Transaction transaction = null;
        Ordremission ordremission = null;
        try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			    // start the transaction
			    transaction = session.beginTransaction();

			    // get student object
			    ordremission= session.byId(Ordremission.class).getReference(id);
			     // or student = session.get(Student.class, id);
			    //or student = session.load(Student.class, id);
			   //or commit the transaction
			    transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
		}
			
		}
        
        
        return ordremission;
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#getAllStudents()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List < Ordremission > getAllOrdremission(){
		
        Transaction transaction = null;
        List < Ordremission > ordremission = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
            ordremission = session.createQuery("from Ordremission").list();
        
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return ordremission;
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#deleteStudent(long)
     */
    @Override
    public	void deleteOrdremission(int id) {
        Transaction transaction = null;
        Ordremission ordremission = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            ordremission = session.get(Ordremission.class, id);
            // get student object
            session.delete(ordremission);
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
	public List<Ordremission> rechercher(String nom) {
		Transaction transaction = null;
        List < Ordremission > ordremission = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
           // students = session.createQuery("from Student").list();
            ordremission = session.createSQLQuery(nom).addEntity(getClass()).list();
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
	
	public void removeStyleBtn(Button btn1, Button btn2, Button btn3) {
		btn1.setStyle("-fx-background-color: transparent");
		btn2.setStyle("-fx-background-color: transparent");
		btn3.setStyle("-fx-background-color: transparent");
		
	}
	
	public void addStyle(Button btn, String color) {
		btn.setStyle("-fx-background-color:" + color);
	}
	
	
	public ObservableList<Ordremission> addOrdremissionList() {

		ObservableList<Ordremission> listOrdremission = FXCollections.observableArrayList();

		String sql = "SELECT * FROM ordremission";

		 connect = Database.connectDb();

		try {

			Ordremission ordremission;

			prepare = connect.prepareStatement(sql);

			result = prepare.executeQuery();

			while (result.next()) {
				ordremission = new Ordremission(result.getInt("idordremission"),result.getInt("idtransporteur"), result.getString("idcommandeclient"),result.getString("numeroordremission"), result.getDate("datedebut"), result.getDate("datefin"), result.getString("statue"),result.getString("rapport"));
				listOrdremission.add(ordremission);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOrdremission;
	}

	

	
	
}
