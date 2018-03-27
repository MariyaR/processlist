package ProcessPackage;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class JUnitTest {

    static GeneralLayer GaN_B1=new GaN_Buffer();
    static GeneralLayer GaN_B2=new GaN_Buffer();
    static GeneralLayer GaN_B3=new GaN_Buffer();

    @BeforeClass
    public static void init(){
        GaN_B1.setH2(5000);
        GaN_B1.setNh3(3000);
        GaN_B1.setTmg(30);
        GaN_B1.setTemperature(1000);
        GaN_B1.setPressure(180);
        GaN_B1.setGrowthRate(0);
        GaN_B1.setGrowthTime(0);
        GaN_B1.setMg(0);
        GaN_B1.setSi(0);
        GaN_B1.setTmi(0);
        GaN_B1.setTma(0);
        GaN_B1.setN2(0);
        GaN_B1.setLayerName("GaN");
        GaN_B1.setFunction(Functions.Buffer);
        GaN_B1.initConditions();

        GaN_B2.setH2(5000);
        GaN_B2.setNh3(3000);
        GaN_B2.setTmg(35);
        GaN_B2.setTemperature(1040);
        GaN_B2.setPressure(200);
        GaN_B2.setGrowthRate(0);
        GaN_B2.setGrowthTime(0);
        GaN_B2.setMg(0);
        GaN_B2.setSi(0);
        GaN_B2.setTmi(0);
        GaN_B2.setTma(0);
        GaN_B2.setN2(0);
        GaN_B2.setLayerName("GaN");
        GaN_B2.setFunction(Functions.Buffer);
        GaN_B2.initConditions();

        GaN_B3.setH2(5050);
        GaN_B3.setNh3(3100);
        GaN_B3.setTmg(40);
        GaN_B3.setTemperature(1020);
        GaN_B3.setPressure(220);
        GaN_B3.setGrowthRate(0);
        GaN_B3.setGrowthTime(0);
        GaN_B3.setMg(0);
        GaN_B3.setSi(0);
        GaN_B3.setTmi(0);
        GaN_B3.setTma(0);
        GaN_B3.setN2(0);
        GaN_B3.setLayerName("GaN");
        GaN_B3.setFunction(Functions.Buffer);
        GaN_B3.initConditions();

    }

    @Test
    public void testLayersCompareFalse() {

        LayerComparer lc =new LayerComparer(GaN_B1, GaN_B2,false);
        String actual=lc.compare2();
        String expected = "Buffer GaN                                          Buffer GaN\n" +
                          "TMG=30                                              TMG=35\n" +
                          "T=1000                                              T=1040\n" +
                          "P=180                                               P=200\n\n\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLayersCompareTrue() {

        LayerComparer lc =new LayerComparer(GaN_B1, GaN_B2,true);
        String actual=lc.compare2();
        String expected = "Buffer GaN                                          Buffer GaN\n" +
                          "TMG=30                                              TMG=35\n" +
                          "T=1000                                              T=1040\n" +
                          "P=180                                               P=200\n" +
                          "H2=5000 NH3=3000 growth_rate=0 \n" +
                          "time=0 Mg=0 Si=0 \n" +
                          "TMI=0 TMA=0 N2=0 \n\n";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCompareThreeLayers () {

        String expected="Buffer GaN                                          Buffer GaN\n" +
                        "H2=5000 NH3=3000 TMG=30                             H2=5000 NH3=3000 TMG=35 \n" +
                        "T=1000 P=180 time=100                               T=1040 P=200 time=200 \n" +
                        "Mg=20 N2=5000                                       Mg=30 N2=5010 \n\n";

        GaN_B1.setN2(5000);
        GaN_B1.setMg(20);
        GaN_B1.setGrowthTime(100);
        GaN_B2.setN2(5010);
        GaN_B2.setMg(30);
        GaN_B2.setGrowthTime(200);
        GaN_B3.setN2(5100);
        GaN_B3.setMg(50);
        GaN_B3.setGrowthTime(300);

        LayerComparer lc1 =new LayerComparer( GaN_B1, GaN_B2,GaN_B3,false);
        //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String actual=lc1.compare3();

        Assert.assertEquals(expected,actual);
    }

}
