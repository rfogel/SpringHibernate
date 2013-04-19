package dao.hibernate;

import model.ModelPersistenty;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Dao 
{
	protected SessionFactory sessionFactory;
	protected Transaction transaction;
	protected Session session;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean save(ModelPersistenty object) {
		try {
			if (object == null) {
				return false;
			}
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(object);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			session.close();
			return false;
		}
		return true;
	}

	public boolean saveOrUpdate(ModelPersistenty object) {
		try {
			if (object == null) {
				return false;
			}
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(object);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			session.close();
			return false;
		}
		return true;
	}

	public boolean update(ModelPersistenty object) {
		try {
			if (object == null) {
				return false;
			}
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(object);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			session.close();
			return false;
		}
		return true;
	}

	public boolean delete(ModelPersistenty object) {
		try {
			if (object == null) {
				return false;
			}
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(object);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			session.close();
			return false;
		}
		return true;
	}

	public boolean databaseConnectionIsUp() {
		session = sessionFactory.openSession();
		if (session.isConnected() && session.isOpen()) {
			session.close();
			return true;
		}
		session.close();
		return false;
	}
}
