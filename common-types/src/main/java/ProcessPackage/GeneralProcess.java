package ProcessPackage;

import java.util.*;

import static sun.swing.MenuItemLayoutHelper.max;

public abstract class GeneralProcess  implements Process<GeneralProcess>{

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

    //create comparator for sort of the Functioons according to Functions order
    Comparator<Functions> FuncComp = Comparator.comparingInt(Func -> Func.getIndex());

    public String compareP ( GeneralProcess p) {
        StringBuffer s = new StringBuffer();
        StringBuffer st=new StringBuffer(EmptyString.subSequence(0,EmptyString.length()-this.Name.toString().length()));
        s.append(this.Name + st + p.Name + "\n");
        s.append("---------------------------------------------------------------"+"\n");

        ArrayList<Functions> Func1 = new ArrayList<Functions>();
        ArrayList<Functions> Func2 = new ArrayList<Functions>();

        //collect all Functions from the first process. Every Function is collected 1 time only.
        for (GeneralLayer l : this.Structure)
            if (!Func1.contains(l.getFunction())) {
                Func1.add(l.getFunction());
            }
        //collect all Functions from the second process. Every Function is collected 1 time only.
        for (GeneralLayer l : p.Structure)
            if (!Func2.contains(l.getFunction())) {
                Func2.add(l.getFunction());
            }

        //arraylist, where different functions will be collected
        ArrayList<Functions> Serve = new ArrayList<Functions>();
        int i, j = 0, k = 0;
        for (i = 0; i < Func1.size(); i++) {
            if (Func2.contains(Func1.get(i))) { //stop at common function
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
                ServePrinter sp = new ServePrinter(); //create class to print different parts of processes to string
                s.append(sp.ServePrint(this, p, Func1, Func2,Serve)); // print different parts of processes to string
                //clean the served arraylist
                Serve.clear();
                //todo sravnenie i vyvod elementa Func1(i)
                ArrayList<GeneralLayer> SubStructure1 = getSubStructure(this,Func1.get(i));
                ArrayList<GeneralLayer> SubStructure2 = getSubStructure(p,Func1.get(i));
                s.append(compare(SubStructure1, SubStructure2));
            }

        }
    return s.toString();
    }

    //get block of the process with function f, for example all buffer layers
    public ArrayList<GeneralLayer> getSubStructure (GeneralProcess p, Functions f) {
        ArrayList<GeneralLayer> Substructure = new ArrayList();
        ListIterator<GeneralLayer> it = p.getStructure().listIterator();
        while (it.hasNext()) {
            GeneralLayer l=it.next();
            if (l.getFunction().equals(f)) {
                Substructure.add(l);
            }
        }
    return Substructure;
    }

    //method to compare 2 function blocks, for example 2 buffers
    public String compare (ArrayList<GeneralLayer> l1, ArrayList<GeneralLayer> l2) {
        StringBuffer s=new StringBuffer();
        int i=0;
        GeneralLayer Layer1=l1.get(0);
        GeneralLayer Layer2=l2.get(0);
        s.append(Layer1.compareTo(Layer2,true)); //todo true or false???????????????????????????????????????
        for (i=1; i< Math.max(l1.size(), l2.size()); i++) {
            if (i<l1.size()&& i<l2.size()) {
                GeneralLayer Layer3=l1.get(i);
                GeneralLayer Layer4=l2.get(i);
                s.append(compare(Layer1, Layer3, Layer4));
            }
            else if (i<l1.size()&& i>=l2.size()) {
                GeneralLayer Layer3=l1.get(i);
                GeneralLayer Layer4=null;
                s.append(compare(Layer1, Layer3, Layer4));
            }

            else if (i>=l1.size()&& i<l2.size()) {
                GeneralLayer Layer3=null;
                GeneralLayer Layer4=l2.get(i);
                s.append(compare(Layer1, Layer3, Layer4));
            }
        }

    return s.toString();
    }

    public String compare (GeneralLayer replayer, GeneralLayer leftlayer, GeneralLayer rightlayer) {
        StringBuffer s=new StringBuffer();


    return s.toString();
    }

    public String compareTo (GeneralProcess p, boolean b) {
        StringBuffer s =new StringBuffer();
        st=new StringBuffer(EmptyString.subSequence(0,EmptyString.length()-this.Name.toString().length()));

        s.append(this.Name + st + p.Name + "\n");
        s.append("---------------------------------------------------------------"+"\n");
        ListIterator <GeneralLayer> FirstIterator = this.Structure.listIterator();
        ListIterator <GeneralLayer> SecondIterator = p.Structure.listIterator();

        while (FirstIterator.hasNext()) {
            GeneralLayer l= FirstIterator.next();
            int i=p.lastIndexOf(l.getFunction());
            int j=0;

            if (i==-1) {
                s.append(l.toString()+"\n");
            }
            
            while (SecondIterator.nextIndex()<=i) {
                GeneralLayer pl= SecondIterator.next();
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
enum Topic { Project_2015, Project_2016, Project_2017}

//enum Devices { HEMT, LED, PL_Structure, N_Dopped_Buffer, P_Dopped_Buffer, SuperLattice, Stack_on_Si}

enum Substrates {Sapphire, Si, SiC, SiC_on_Si, DP_Sapphire, Profiled_Sapphire}