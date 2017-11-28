package ProcessPackage;


import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class JUnitTest {

    static GaN_Buffer GaN_B1=new GaN_Buffer();
    static GaN_Buffer GaN_B2=new GaN_Buffer();

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
}
