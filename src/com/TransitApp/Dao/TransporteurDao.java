package com.TransitApp.Dao;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.TransitApp.Modeles.Transporteur;
import com.TransitApp.Util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TransporteurDao implements ITransporteurDao {
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

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#saveStudent(net.javaguides.hibernate.model.Student)*/
    @Override
    public void saveTransporteur(Transporteur transporteur) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.save(transporteur);

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
    public void updateTransporteur(Transporteur transporteur) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // save student object
            session.saveOrUpdate(transporteur);

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
    public Transporteur getTransporteurById(int id) {
        Transaction transaction = null;
        Transporteur transporteur = null;
        try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			    // start the transaction
			    transaction = session.beginTransaction();

			    // get student object
			    transporteur= session.byId(Transporteur.class).getReference(id);
			     // or student = session.get(Student.class, id);
			    //or student = session.load(Student.class, id);
			   //or commit the transaction
			    transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
		}
			
		}
        
        
        return transporteur;
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#getAllStudents()
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Transporteur> getAllTransporteur() {
        Transaction transaction = null;
        List < Transporteur > transporteur = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
            transporteur = session.createQuery("from Transporteur").list();
            //student = session.load(Student.class, id);
            // commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return transporteur;
    }

    /* (non-Javadoc)
     * @see net.javaguides.hibernate.dao.IStudentDao#deleteStudent(long)
     */
    @Override
    public void deleteTransporteur(int id) {
        Transaction transaction = null;
        Transporteur transporteur = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            transporteur= session.get(Transporteur.class, id);
            // get student object
            session.delete(transporteur);
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
	public List<Transporteur> rechercher(String nom) {
		Transaction transaction = null;
        List < Transporteur > transporteur = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start the transaction
            transaction = session.beginTransaction();

            // get students
           // students = session.createQuery("from Student").list();
            transporteur = session.createSQLQuery(nom).addEntity(getClass()).list();
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
	
	
	public void Login(String fxml, Button button) {
    	try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource(fxml));

			Stage stage = new Stage();

			Scene scene = new Scene(root);

			button.getScene().getWindow().hide();
			
			
			
			// Permet de faire bouger la fenetre et d'éviter de la redimensionner
			root.setOnMousePressed((MouseEvent event) -> {
				x = event.getSceneX();
				y = event.getSceneY();
			});

			root.setOnMouseDragged((MouseEvent event) -> {
				stage.setX(event.getScreenX() - x);
				stage.setY(event.getScreenY() - y);
			});

			stage.setScene(scene);
			stage.initStyle(StageStyle.TRANSPARENT);

			stage.show();
		} catch (IOException error) {
			// TODO Auto-generated catch block
			error.printStackTrace();
		}
    }

	
	
	public ObservableList<Transporteur> addTransporteurList() {

		ObservableList<Transporteur> listTransporteur = FXCollections.observableArrayList();

		String sql = "SELECT * FROM transporteur";

		 connect = Database.connectDb();

		try {

			Transporteur transporteur;

			prepare = connect.prepareStatement(sql);

			result = prepare.executeQuery();

			while (result.next()) {
				transporteur = new Transporteur(result.getInt("idtransporteur"),result.getInt("idadmin"),result.getString("nomtransporteur"),result.getString("prenomtrnsporteur"), result.getString("civilite"), result.getDate("datedebutembauche"), result.getDate("datefinembauche"),result.getFloat("salaire"),result.getString("fonction"));
				listTransporteur.add(transporteur);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listTransporteur;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
