package ProcessPackage;

import java.util.*;
import java.util.concurrent.Semaphore;

import static sun.swing.MenuItemLayoutHelper.max;

public abstract class GeneralProcess {

    private Process Analog;
    private String Name;
    private Topic ProjectTopic;
    private String OperatorComment;
    private Substrates Substrate;
    private List <GeneralLayer> Structure = new ArrayList<GeneralLayer>();
    private List <ProcessError> Errors= new ArrayList<ProcessError>();
    private String EmptyString= "                                                    ";
    private StringBuffer st= new StringBuffer();

    public void setAnalog(Process analog) {
        Analog = analog;
    }
    public void setName (String s) {Name=s;}
    public void setProjectTopic (Topic s) {ProjectTopic=s;}
    public void setOperatorComment (String s) {OperatorComment=s;}
    public void setSubstrate (Substrates s) {Substrate=s;}
    public void setStructure (List<GeneralLayer> l) {Structure=l;}
    private void setErrors (List<ProcessError> l) {Errors=l;}

    public Process getAnalog() {
        return Analog;
    }
    public String getName() {return Name;}
    public Topic getProjectTopic() { return ProjectTopic; }
    public String getOperatorComment() {
        return OperatorComment;
    }
    public Substrates getSubstrate() {
        return Substrate;
    }
    public List<GeneralLayer> getStructure() {
        return Structure;
    }
    public List<ProcessError> getErrors() {
        return Errors;
    }


    public int lastIndexOf(Functions f) {
        ListIterator<GeneralLayer> it = this.Structure.listIterator();
        int i=-1;
        while (it.hasNext()) {
            if (it.next().getFunction().equals(f)) {
                i=it.previousIndex();
            }
        }
    return i;
    }

    //public abstract void theOnlyAbstractMethod(); //the place for other methods

    @Override
    public String toString () {
        return Name + " " + Substrate  + " " + Errors.toString() + " " + OperatorComment + "\n" + this.StructureToString();
    }

    public void addLayer (GeneralLayer l) {
        this.Structure.add(l);
    }

    public String StructureToString () {

        ListIterator<GeneralLayer> It = this.Structure.listIterator();
        StringBuffer s = new StringBuffer();

        while (It.hasNext()) {
            s.append(It.next().toString());
        }
        return s.toString();
    }

    abstract void firstAbstractMethod();

}


