package ProcessPackage;


import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class JUnitTest {

    static GaN_Buffer GaN_B1=new GaN_Buffer();
    static GaN_Buffer GaN_B2=new GaN_Buffer();
    static GaN_Buffer GaN_B3=new GaN_Buffer();

    @BeforeClass
    public static void init(){
        GaN_B1.setCondition(Parameters.H2,5000);
        GaN_B1.setCondition(Parameters.NH3,3000);
        GaN_B1.setCondition(Parameters.TMG,30);
        GaN_B1.setCondition(Parameters.T,1000);
        GaN_B1.setCondition(Parameters.P,180);

        GaN_B2.setCondition(Parameters.H2,5000);
        GaN_B2.setCondition(Parameters.NH3,3000);
        GaN_B2.setCondition(Parameters.TMG,35);
        GaN_B2.setCondition(Parameters.T,1040);
        GaN_B2.setCondition(Parameters.P,200);

        GaN_B3.setCondition(Parameters.H2,5050);
        GaN_B3.setCondition(Parameters.NH3,3100);
        GaN_B3.setCondition(Parameters.TMG,40);
        GaN_B3.setCondition(Parameters.T,1020);
        GaN_B3.setCondition(Parameters.P,220);

    }

    @Test
    public void testCompareToFalse() {
        String actual=GaN_B1.compareTo(GaN_B2, false);
        String expected = "Buffer GaN_Buffer                                   Buffer GaN_Buffer\n" +
                "TMG=30                                              TMG=35\n" +
                "T=1000                                              T=1040\n" +
                "P=180                                               P=200\n";
        assertEquals(expected, actual);
    }

    @Test
    public void testCompareToTrue() {
        String actual=GaN_B1.compareTo(GaN_B2, true);
        String expected = "Buffer GaN_Buffer                                   Buffer GaN_Buffer\n" +
                "TMG=30                                              TMG=35\n" +
                "T=1000                                              T=1040\n" +
                "P=180                                               P=200\n" +
                "H2=5000 NH3=3000 ";
        assertEquals(expected, actual);
    }

    @Test
    public void testCompareThreeLayers () {
        String actual="111";
        String expected="111";
        LedProcess p=new LedProcess();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(p.compare (GaN_B3, GaN_B1, GaN_B2));

        assertEquals(expected,actual);
    }

}