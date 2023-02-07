package com.TransitApp.Util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.TransitApp.Modeles.Admin;
import com.TransitApp.Modeles.Categorie;
import com.TransitApp.Modeles.Client;
import com.TransitApp.Modeles.Commandeclient;
import com.TransitApp.Modeles.Commandefournisseur;
import com.TransitApp.Modeles.Contenir;
import com.TransitApp.Modeles.Contenir1;
import com.TransitApp.Modeles.Contenir1Id;
import com.TransitApp.Modeles.Contenir2;
import com.TransitApp.Modeles.Contenir2Id;
import com.TransitApp.Modeles.ContenirId;
import com.TransitApp.Modeles.Creer;
import com.TransitApp.Modeles.CreerId;
import com.TransitApp.Modeles.Effectuer;
import com.TransitApp.Modeles.EffectuerId;
import com.TransitApp.Modeles.Enregistrer;
import com.TransitApp.Modeles.EnregistrerId;
import com.TransitApp.Modeles.Entrepot;
import com.TransitApp.Modeles.Fournisseur;
import com.TransitApp.Modeles.Ligneordremission;
import com.TransitApp.Modeles.LigneordremissionId;
import com.TransitApp.Modeles.Manager;
import com.TransitApp.Modeles.Ordremission;
import com.TransitApp.Modeles.Produit;
import com.TransitApp.Modeles.Resporeapro;
import com.TransitApp.Modeles.Transporteur;


public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/transitbd?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                //settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Admin.class);
                configuration.addAnnotatedClass(Categorie.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Commandeclient.class);
                configuration.addAnnotatedClass(Commandefournisseur.class);
                configuration.addAnnotatedClass(Contenir.class);
                configuration.addAnnotatedClass(Contenir1.class);
                configuration.addAnnotatedClass(Contenir1Id.class);
                configuration.addAnnotatedClass(Contenir2.class);
                configuration.addAnnotatedClass(Contenir2Id.class);
                configuration.addAnnotatedClass(ContenirId.class);
                configuration.addAnnotatedClass(Creer.class);
                configuration.addAnnotatedClass(CreerId.class);
                configuration.addAnnotatedClass(Effectuer.class);
                configuration.addAnnotatedClass(EffectuerId.class);
                configuration.addAnnotatedClass(Enregistrer.class);
                configuration.addAnnotatedClass(EnregistrerId.class);
                configuration.addAnnotatedClass(Entrepot.class);
                configuration.addAnnotatedClass(Fournisseur.class);
                configuration.addAnnotatedClass(Ligneordremission.class);
                configuration.addAnnotatedClass(LigneordremissionId.class);
                configuration.addAnnotatedClass(Manager.class);
                configuration.addAnnotatedClass(Ordremission.class);
                configuration.addAnnotatedClass(Produit.class);
                configuration.addAnnotatedClass(Resporeapro.class);
                configuration.addAnnotatedClass(Transporteur.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
