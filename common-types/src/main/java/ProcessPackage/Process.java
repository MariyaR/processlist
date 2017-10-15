package ProcessPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Process {

    private Process Analog;
    private String Name;
    private Topic ProjectTopic;
    private Devices Device;
    private String OperatorComment;
    private Substrates Substrate;
    private List <Layer> Structure = new ArrayList<Layer>();
    private List <ProcessError> Errors= new ArrayList<ProcessError>();
    private String EmptyString= "                                                    ";
    private StringBuffer st= new StringBuffer();

    public void setAnalog(Process analog) {
        Analog = analog;
    }
    public void setName (String s) {Name=s;}
    public void setProjectTopic (Topic s) {ProjectTopic=s;}
    public void setDevice (Devices s) {Device=s;}
    public void setOperatorComment (String s) {OperatorComment=s;}
    public void setSubstrate (Substrates s) {Substrate=s;}
    public void setStructure (List<Layer> l) {Structure=l;}
    private void setErrors (List<ProcessError> l) {Errors=l;}

    public Process getAnalog() {
        return Analog;
    }
    public String getName() {return Name;}
    public Topic getProjectTopic() { return ProjectTopic; }
    public Devices getDevice() {
        return Device;
    }
    public String getOperatorComment() {
        return OperatorComment;
    }
    public Substrates getSubstrate() {
        return Substrate;
    }
    public List<Layer> getStructure() {
        return Structure;
    }
    public List<ProcessError> getErrors() {
        return Errors;
    }


    public String compareTo (Process p, boolean b) {
        StringBuffer s =new StringBuffer();
        st=new StringBuffer(EmptyString.subSequence(0,EmptyString.length()-this.Name.toString().length()));

        s.append(this.Name + st + p.Name + "\n");
        s.append("---------------------------------------------------------------"+"\n");
        ListIterator <Layer> FirstIterator = this.Structure.listIterator();
        ListIterator <Layer> SecondIterator = p.Structure.listIterator();

        while (FirstIterator.hasNext()) {
            Layer l= FirstIterator.next();
            int i=p.lastIndexOf(l.getFunction());
            int j=0;

            if (i==-1) {
                s.append(l.toString()+"\n");
            }
            
            while (SecondIterator.nextIndex()<=i) {
                Layer pl= SecondIterator.next();
                if (l.getFunction().equals(pl.getFunction()) && j==0) {
                    s.append(l.compareTo(pl, b)+"\n");
                    j++;
                }
                else if (l.getFunction().equals(pl.getFunction()) && j>0) {
                    s.append(l.compareTo(pl)+"\n");
                }
                if (!l.getFunction().equals(pl.getFunction())) {
                    s.append(pl.toStringRight()+"\n"+"\n");
                }
            }
            s.append("---------------------------------------------------------------"+"\n");
        }
        return s.toString();
    }

    public int lastIndexOf(Functions f) {
        ListIterator<Layer> it = this.Structure.listIterator();
        int i=-1;
        while (it.hasNext()) {
            if (it.next().getFunction().equals(f)) {
                i=it.previousIndex();
            }
        }
    return i;
    }

    @Override
    public String toString () {
        return Name + " " + Device + " " + Substrate  + " " + Errors.toString() + " " + OperatorComment + "\n" + this.StructureToString();
    }

    public void addLayer (Layer l) {
        this.Structure.add(l);
    }

    public String StructureToString () {

        ListIterator<Layer> It = this.Structure.listIterator();
        StringBuffer s = new StringBuffer();

        while (It.hasNext()) {
            s.append(It.next().toString());
        }
        return s.toString();
    }

}
enum Topic { Project_2015, Project_2016, Project_2017}

enum Devices { HEMT, LED, PL_Structure, N_Dopped_Buffer, P_Dopped_Buffer, SuperLattice, Stack_on_Si}

enum Substrates {Sapphire, Si, SiC, SiC_on_Si, DP_Sapphire, Profiled_Sapphire}