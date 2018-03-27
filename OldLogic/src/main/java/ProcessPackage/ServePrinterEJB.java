package ProcessPackage;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Stateless
public class ServePrinterEJB implements ServePrinterEJBLocal{


    //function to provide output to String of principally different parts of 2 processes before by the common part
    @Override
    public String ServePrint (GeneralProcess p1, GeneralProcess p2, ArrayList<Functions> f1, ArrayList <Functions> f2, ArrayList <Functions> l) {
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
    public String leftPrint (GeneralProcess p, Functions f) {
        StringBuffer s = new StringBuffer();
        List<GeneralLayer> Structure =p.getStructure();
        ListIterator<GeneralLayer> it=Structure.listIterator();
        while (it.hasNext()) {
            GeneralLayer l= it.next();
            if (l.getFunction().equals(f)) {
                s.append(l.toString());
            }
        }
        return s.toString();
    }
    //print from the process p all the layers with function f on the right side
    public String rightPrint (GeneralProcess p, Functions f) {
        StringBuffer s = new StringBuffer();
        List<GeneralLayer> Structure =p.getStructure();
        ListIterator<GeneralLayer> it=Structure.listIterator();
        while (it.hasNext()) {
            GeneralLayer l= it.next();
            if (l.getFunction().equals(f)) {
                s.append(l.toStringRight());
            }
        }
        return s.toString();
    }
}
