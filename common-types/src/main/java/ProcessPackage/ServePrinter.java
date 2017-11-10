package ProcessPackage;

import java.util.*;

import java.util.ArrayList;

public class ServePrinter {

    //function to provide output to String of principally different parts of 2 processes before by the common part
    public String ServePrint (Process p1, Process p2, ArrayList <Functions> f1, ArrayList <Functions> f2,ArrayList <Functions> l) {
        StringBuffer s = new StringBuffer();
        ListIterator<Functions> it = l.listIterator();
        while (it.hasNext()) {
            Functions f=it.next();
            if (f1.contains(f)) {
                //left vyvod sloev
                s.append(leftPrint(p1,f));
            }
            if (f2.contains(f)) {
                //right vyvod sloev
                s.append(rightPrint(p2,f));
            }
        }
    return s.toString();
    }

    //print from the process p all the layers with function f on the left side
    public String leftPrint (Process p, Functions f) {
        StringBuffer s = new StringBuffer();
        List<Layer> Structure =p.getStructure();
        ListIterator<Layer> it=Structure.listIterator();
        while (it.hasNext()) {
            Layer l= it.next();
            if (l.getFunction().equals(f)) {
                s.append(l.toString());
            }
        }
        return s.toString();
    }
    //print from the process p all the layers with function f on the right side
    public String rightPrint (Process p, Functions f) {
        StringBuffer s = new StringBuffer();
        List<Layer> Structure =p.getStructure();
        ListIterator<Layer> it=Structure.listIterator();
        while (it.hasNext()) {
            Layer l= it.next();
            if (l.getFunction().equals(f)) {
                s.append(l.toStringRight());
            }
        }
        return s.toString();
    }
}
