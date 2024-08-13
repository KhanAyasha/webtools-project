/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webtools.ayasha.WebProject.dao;


import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class BaseDAO {

    private static final Logger log = Logger.getAnonymousLogger();
    private static final ThreadLocal sessionThread = new ThreadLocal();
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


    public BaseDAO() {
        
    }

    protected Session getSession() {
        
        Session session = (Session) BaseDAO.sessionThread.get();

        if (session == null) {
            session = sessionFactory.openSession();
            BaseDAO.sessionThread.set(session);
        }
        return session;
    
    }

    protected void beginTransaction() {
       getSession().beginTransaction();
    }

    
	public void commitTransaction() {
		// TODO Auto-generated method stub
		this.getSession().getTransaction().commit();

	}

	
	public void rollbackTransaction() {
		// TODO Auto-generated method stub
		this.getSession().getTransaction().rollback();

	}

        protected void closeSession() {
            getSession().close();
            }


    //     Optional: Method to close the SessionFactory, usually called when the application is shutting down
        public void closeSessionFactory() {
            getSession().close();
           }
    
}
