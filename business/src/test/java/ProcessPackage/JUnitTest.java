package ProcessPackage;


import org.junit.Assert;
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
    public void testLayersCompareFalse() {

        LayerComparer lc =new LayerComparer(GaN_B1, GaN_B2,false);
        String actual=lc.compare2();
        String expected = "Buffer GaN_Buffer                                   Buffer GaN_Buffer\n" +
                "TMG=30                                              TMG=35\n" +
                "T=1000                                              T=1040\n" +
                "P=180                                               P=200\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLayersCompareTrue() {

        LayerComparer lc =new LayerComparer(GaN_B1, GaN_B2,true);
        String actual=lc.compare2();
        String expected = "Buffer GaN_Buffer                                   Buffer GaN_Buffer\n" +
                "TMG=30                                              TMG=35\n" +
                "T=1000                                              T=1040\n" +
                "P=180                                               P=200\n" +
                "H2=5000 NH3=3000 ";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCompareThreeLayers () {

        String expected="Buffer GaN_Buffer                                   Buffer GaN_Buffer\n" +
                "H2=5000 NH3=3000 TMG=30                             H2=5000 NH3=3000 TMG=35 \n" +
                "T=1000 P=180 N2=5000                                T=1040 P=200 N2=5010 \n" +
                "Mg=20 time=100                                      Mg=30 time=200 \n";

        GaN_B1.setCondition(Parameters.N2,5000);
        GaN_B1.setCondition(Parameters.Mg,20);
        GaN_B1.setCondition(Parameters.time,100);
        GaN_B2.setCondition(Parameters.N2,5010);
        GaN_B2.setCondition(Parameters.Mg,30);
        GaN_B2.setCondition(Parameters.time,200);
        GaN_B3.setCondition(Parameters.N2,5100);
        GaN_B3.setCondition(Parameters.Mg,50);
        GaN_B3.setCondition(Parameters.time,300);

        LayerComparer lc1 =new LayerComparer( GaN_B1, GaN_B2,GaN_B3,false);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String actual=lc1.compare3();
        System.out.println(actual);

        Assert.assertEquals(expected,actual);
    }

}
