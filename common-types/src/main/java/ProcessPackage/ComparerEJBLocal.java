package ProcessPackage;

import javax.ejb.Local;


@Local
public interface ComparerEJBLocal {

    String compare2(GeneralLayer l1, GeneralLayer l2, boolean b);
    String compare3 (GeneralLayer l1, GeneralLayer l2, GeneralLayer l3, boolean b);


}
