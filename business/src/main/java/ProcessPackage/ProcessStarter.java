package ProcessPackage;


import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public class ProcessStarter {

//    public static void main(String[] args) {
//
//        System.out.println("connecting to db");
//
//        SpringDBConnector dbconnector = new SpringDBConnector();
//        System.out.println("connecting to db");
//        System.out.println(dbconnector.getLayer("a101010a", "GaN").toString());
//
//
//
//    }

    private org.slf4j.Logger log = LoggerFactory.getLogger(DBHandler.class);

    public static void main(String[] args) {



    List<GeneralProcess> processlist = new Repository().list();
        List<GeneralProcess> processlist2 = new Repository().list();
    GeneralProcess p1=new GeneralProcess();
    GeneralProcess p2=new GeneralProcess();
        Iterator<GeneralProcess> it= processlist.listIterator();
    while (it.hasNext()) {
        GeneralProcess p=it.next();
        if (p.getName().equals("a101111a")) {p1=p;}
        if (p.getName().equals("a101112a")) {p2=p;}
    }


        ProcessComparer comp=new ProcessComparer();

        System.out.println(comp.compare(p1,p2));
        //System.out.println(p1.toString();

        //LayerComparer compr=new LayerComparer(p2.getStructure().get(1),p1.getStructure().get(2),p2.getStructure().get(0),true);
       // System.out.println(compr.compare3());
       // compr.compare3();

    }

}
