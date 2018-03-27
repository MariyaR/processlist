package ProcessPackage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.LoggerFactory;

import javax.persistence.PersistenceException;


public class DBHandler {

    private static StandardServiceRegistry standardRegistry;
    private static SessionFactory sessionFactory;
    private org.slf4j.Logger log = LoggerFactory.getLogger(DBHandler.class);

    public void init() {
        standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources( standardRegistry )
                .addAnnotatedClass( GeneralProcess.class )
                .getMetadataBuilder()
                .build();

        sessionFactory = metadata.buildSessionFactory();
    }

    public void destroy() {
        StandardServiceRegistryBuilder.destroy(standardRegistry);

    }

    public void send(GeneralProcess p) throws PersistenceException {
            init();
            Session s = sessionFactory.openSession();
            s.beginTransaction();
            s.save(p);
            s.getTransaction().commit();
            s.close();
            destroy();
    }

    public void update(GeneralProcess p) {
        init();
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        s.saveOrUpdate(p);
        s.getTransaction().commit();
        s.close();
        destroy();
    }

}
