package ProcessPackage;


import javax.ejb.Local;

@Local
public interface ProcessComparerEJBLocal {
    String compare (GeneralProcess LeftProcess, GeneralProcess RightProcess);
}
