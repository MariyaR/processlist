package ProcessPackage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Repository {

    private List<GeneralProcess> processList;
    private static StandardServiceRegistry standardRegistry;
    private static SessionFactory sessionFactory;
//    private org.slf4j.Logger log = LoggerFactory.getLogger(Repository.class);


    public Repository() {
        formRepository();
    }

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

    public void formRepository() {
       // log.info("formrepository is invoked");
        init();
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        Query query=s.createQuery("from GeneralProcess");
       // query.setCacheable(true);
        processList = query.list();
//        for (GeneralProcess p : processList) {
//            System.out.println("Process: " + p.getName());
//        }

        s.getTransaction().commit();
        s.close();
        destroy();

    }

    public List<GeneralProcess> list() {
        return processList;
    }


}



