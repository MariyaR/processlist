package ProcessPackage;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;

@Stateless
public class ProcessComparerEJB {//implements ProcessComparerEJBLocal{

//    final private String EmptyString= "                                                    ";
//
//    Comparator<Functions> FuncComp = Comparator.comparingInt(Func -> Func.getIndex());
//
//    @Override
//    public String compare (GeneralProcess LeftProcess, GeneralProcess RightProcess) {
//        StringBuffer s = new StringBuffer();
//        StringBuffer st=new StringBuffer(EmptyString.subSequence(0,EmptyString.length()-LeftProcess.getName().toString().length()));
//        s.append(LeftProcess.getName() + st + RightProcess.getName() + "\n");
//        s.append("---------------------------------------------------------------"+"\n");
//
//        ArrayList<Functions> Func1 = new ArrayList<Functions>();
//        ArrayList<Functions> Func2 = new ArrayList<Functions>();
//
//        //collect all Functions from the first process. Every Function is collected 1 time only.
//        for (GeneralLayer l : LeftProcess.getStructure())
//            if (!Func1.contains(l.getFunction())) {
//                Func1.add(l.getFunction());
//            }
//        //collect all Functions from the second process. Every Function is collected 1 time only.
//        for (GeneralLayer l : RightProcess.getStructure())
//            if (!Func2.contains(l.getFunction())) {
//                Func2.add(l.getFunction());
//            }
//
//        //arraylist, where different functions will be collected
//        ArrayList<Functions> Serve = new ArrayList<Functions>();
//        int i, j = 0, k = 0;
//        for (i = 0; i < Func1.size(); i++) {
//            if (Func2.contains(Func1.get(i))) { //stop at common function
//                while (j < i) {
//                    if (!Func2.contains(Func1.get(j))) { //add all different functions to the served arraylist
//                        Serve.add(Func1.get(j));} // uslovie proverki!! Func1(i)!=Func2(j)
//                    j++;
//                }
//                while (k < Func2.indexOf(Func1.get(i))) { //add all different functions to the served arraylist
//                    if (!Func1.contains(Func2.get(k))){
//                        Serve.add(Func2.get(k));} // uslovie proverki!! Func1(i)!=Func2(j)
//                    k++;
//                }
//
//                //sort the served array list according to Functions order (priority)
//                Collections.sort(Serve, FuncComp);
//                //print the sorted array to the string s
//                ServePrinter sp = new ServePrinter(); //create class to print different parts of processes to string
//                s.append(sp.ServePrint(LeftProcess, RightProcess, Func1, Func2,Serve)); // print different parts of processes to string
//                //clean the served arraylist
//                Serve.clear();
//                //todo sravnenie i vyvod elementa Func1(i)
//                ArrayList<GeneralLayer> SubStructure1 = getSubStructure(LeftProcess,Func1.get(i));
//                ArrayList<GeneralLayer> SubStructure2 = getSubStructure(RightProcess,Func1.get(i));
//                s.append(compare(SubStructure1, SubStructure2));
//            }
//
//        }
//        return s.toString();
//    }
//
//    //get block of the process with function f, for example all buffer layers
//    public ArrayList<GeneralLayer> getSubStructure (GeneralProcess p, Functions f) {
//        ArrayList<GeneralLayer> Substructure = new ArrayList();
//        ListIterator<GeneralLayer> it = p.getStructure().listIterator();
//        while (it.hasNext()) {
//            GeneralLayer l=it.next();
//            if (l.getFunction().equals(f)) {
//                Substructure.add(l);
//            }
//        }
//        return Substructure;
//    }
//
//    //method to compare 2 function blocks, for example 2 buffers
//    public String compare (ArrayList<GeneralLayer> l1, ArrayList<GeneralLayer> l2) {
//        StringBuffer s=new StringBuffer();
//        int i=0;
//        GeneralLayer Layer1=l1.get(0);
//        GeneralLayer Layer2=l2.get(0);
//        LayerComparer lc = new LayerComparer(Layer1, Layer2, true);
//        s.append(lc.compare2()); //todo true or false???????????????????????????????????????
//        for (i=1; i< Math.max(l1.size(), l2.size()); i++) {
//            if (i<l1.size()&& i<l2.size()) {
//                GeneralLayer Layer3=l1.get(i);
//                GeneralLayer Layer4=l2.get(i);
//                LayerComparer lcc = new LayerComparer(Layer3, Layer4, Layer1, true);
//                s.append(lcc.compare3());
//            }
//            else if (i<l1.size()&& i>=l2.size()) {
//                GeneralLayer Layer3=l1.get(i);
//                GeneralLayer Layer4=null;
//                LayerComparer lcc= new LayerComparer(Layer3, Layer4, Layer1, true);
//                s.append(lcc.compare3());
//            }
//
//            else if (i>=l1.size()&& i<l2.size()) {
//                GeneralLayer Layer3=null;
//                GeneralLayer Layer4=l2.get(i);
//                LayerComparer lcc=new LayerComparer(Layer3, Layer4, Layer1, true);
//                s.append(lcc.compare3());
//            }
//        }
//
//        return s.toString();
//    }


}
