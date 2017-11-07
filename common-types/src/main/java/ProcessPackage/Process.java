package ProcessPackage;

import java.util.*;

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

    //create comparator for sort of the Functioons according to Functions order
    Comparator<Functions> FuncComp = Comparator.comparingInt(Func -> Func.getIndex());


    public String compareP (Process p) {
        StringBuffer s = new StringBuffer();
        List<Functions> Func1 = new ArrayList<Functions>();
        List<Functions> Func2 = new ArrayList<Functions>();

        //collect Functions of layers from the first process
        for (Layer l : this.Structure)
            if (!Func1.contains(l.getFunction())) {
                Func1.add(l.getFunction());
            }
        //collect Functions of layers from the second process
        for (Layer l : p.Structure)
            if (!Func2.contains(l.getFunction())) {
                Func2.add(l.getFunction());
            }

        //arraylist, where different functions will be collected
        ArrayList<Functions> Serve = new ArrayList<Functions>();
        int i, j = 0, k = 0;

        for (i = 0; i < Func1.size(); i++) {

            if (Func2.contains(Func1.get(i))) { //stop at common functions
                while (j < i) {
                    if (!Func2.contains(Func1.get(j))) { //add all different functions to the served arraylist
                        Serve.add(Func1.get(j));} // uslovie proverki!! Func1(i)!=Func2(j)
                    j++;
                }

                while (k < Func2.indexOf(Func1.get(i))) { //add all different functions to the served arraylist
                    if (!Func1.contains(Func2.get(k))){
                        Serve.add(Func2.get(k));} // uslovie proverki!! Func1(i)!=Func2(j)
                    k++;
                }

                //sort the served array list according to Functions order (priority)
               Collections.sort(Serve, FuncComp);
                //print the sorted array to the string s
                //Serve.out();

                //clean the served arraylist
                Serve.clear();
                //todo sravnenie i vyvod elementa Func1(i)
            }

        }

    return s.toString();
    }


    private String out (ArrayList <Functions> l) {
        String s = new String();

        return s;

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