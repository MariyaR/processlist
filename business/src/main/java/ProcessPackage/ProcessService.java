package ProcessPackage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessService {

    //todo реализовать @inject
    private List<GeneralProcess> processList;

    private int i;
    private org.slf4j.Logger log = LoggerFactory.getLogger(ProcessService.class);

    public ProcessService() {
        processList=new Repository().list();
        log.info("");
        log.info("------------------------------------------------------");
        log.info("processList=new Repository().list();");

    }

     public List<GeneralProcess> findAll(int offset, int limit, String name, String topic, String analog, String substrate){

        Collections.sort(processList, Comparator.comparing(GeneralProcess::getName));
        log.info("findAll is invoked and list is sorted");


        log.info("string 34");
        List<GeneralProcess> filteredList=processList;

        log.info("string 37");

        log.info("string 39");
        if(name!=null) {
//            log.info("Filter not null:"+filter);
//            log.info("----------------------------->");
//            log.info("return process lest with filter: offset: "+offset+" limit: "+limit);
            filteredList=filteredList.stream().filter((s) -> s.getName().startsWith(name))
                    .collect(Collectors.toList());
//            try {
//                return list.subList(offset, offset + limit);
//            }
//            catch (IndexOutOfBoundsException e) {
//                return list;
//            }
        }

        if(topic!=null) {
            filteredList=filteredList.stream().filter((s) -> s.getProjectTopic().toString().startsWith(topic))
                    .collect(Collectors.toList());
        }
        if(analog!=null) {
            filteredList=filteredList.stream().filter((s) -> s.getAnalogName().startsWith(analog))
                     .collect(Collectors.toList());
        }

        if(substrate!=null) {
            log.info("string 64");
             filteredList=filteredList.stream().filter((s) -> s.getSubstrate().toString().startsWith(substrate))
                     .collect(Collectors.toList());
        }


        try {
            return filteredList.subList(offset, offset + limit);
        }catch (IndexOutOfBoundsException e) {
                return filteredList;
        }
     }




    public int count(String name, String topic, String analog, String substrate) {

         List<GeneralProcess> filteredList=processList;

        if(name!=null) {
            filteredList=filteredList.stream().filter((s) -> s.getName().startsWith(name))
                    .collect(Collectors.toList());
        }

         if(topic!=null) {
            filteredList=filteredList.stream().filter((s) -> s.getProjectTopic().toString().startsWith(topic))
                    .collect(Collectors.toList());
        }
        if(analog!=null) {
            filteredList=filteredList.stream().filter((s) -> s.getAnalogName().startsWith(analog))
                    .collect(Collectors.toList());
        }

        if(substrate!=null) {
            filteredList=filteredList.stream().filter((s) -> s.getSubstrate().toString().startsWith(substrate))
                    .collect(Collectors.toList());
        }

        return filteredList.size();
//        if (filter!=null)
//            return processList.stream().filter((s) -> s.getName().startsWith(filter))
//                    .collect(Collectors.toList()).size();
//        else return processList.size();
    }

    public void addProcess(GeneralProcess p){
        processList.add(p);
        log.info("process is added to the list:" + p.getName());
        }

    public void updateProcess(GeneralProcess p){
        processList.forEach(n->{if (n.getName().equals(p.getName())) i=processList.indexOf(n); });
        processList.set(i,p);
        log.info("process is updated in the list:" + p.getName());
    }
}
