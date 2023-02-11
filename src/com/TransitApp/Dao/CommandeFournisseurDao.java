package com.TransitApp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.TransitApp.Modeles.Commandefournisseur;
import com.TransitApp.Util.HibernateUtil;

public class CommandeFournisseurDao implements ICommandeFournisseurDao {

	private Connection connect;

	private PreparedStatement prepare;

	private ResultSet result;

	private double x;

	private double y;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.javaguides.hibernate.dao.IStudentDao#saveStudent(net.javaguides.hibernate
	 * .model.Student)
	 */
	@Override
	public void saveCommandeFournisseur(Commandefournisseur cmdFourn) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();

			// save student object
			session.save(cmdFourn);

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
	public void updateCommandeFournisseur(Commandefournisseur cmdFourn) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();

			// save student object
			session.saveOrUpdate(cmdFourn);

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
	public Commandefournisseur getAdminById(int id) {
		Transaction transaction = null;
		Commandefournisseur cmdFourn = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			// start the transaction
			transaction = session.beginTransaction();

			// get student object
			cmdFourn = session.byId(Commandefournisseur.class).getReference(id);
			// or student = session.get(Student.class, id);
			// or student = session.load(Student.class, id);
			// or commit the transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

		}

		return cmdFourn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.javaguides.hibernate.dao.IStudentDao#getAllStudents()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Commandefournisseur> getAllCommandefournisseur() {
		Transaction transaction = null;
		List<Commandefournisseur> cmdFourn = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();

			// get admin
			cmdFourn = session.createQuery("from Commandefournisseur").list();

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return cmdFourn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.javaguides.hibernate.dao.IStudentDao#deleteStudent(long)
	 */
	@Override
	public void deleteCommandefournisseur(int id) {
		Transaction transaction = null;
		Commandefournisseur cmdFourn = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();

			cmdFourn = session.get(Commandefournisseur.class, id);
			// get student object
			session.delete(cmdFourn);
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
