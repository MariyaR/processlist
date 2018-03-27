package ProcessPackage;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface ServePrinterEJBLocal {
    String ServePrint (GeneralProcess p1, GeneralProcess p2, ArrayList<Functions> f1, ArrayList <Functions> f2, ArrayList <Functions> l);
}
