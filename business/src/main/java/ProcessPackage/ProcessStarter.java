package ProcessPackage;

import com.sun.org.apache.xalan.internal.xsltc.ProcessorVersion;

public class ProcessStarter {

    public static void main(String[] args) {
        Layer p1l1, p1l2, p1l3, p2l1,p2l2,p2l3,p2l4,p2l5,p2l6;
        Process p1, p2;

        p1l1=new Layer();
        p1l2=new Layer();
        p1l3=new Layer();
        p2l1=new Layer();
        p2l2=new Layer();
        p2l3=new Layer();
        p2l4=new Layer();
        p2l5=new Layer();
        p2l6=new Layer();

        p1=new Process();
        p2=new Process();

        //initializing layers first structure
        p1l1.setFunction(Functions.Seed_Layer);
        p1l1.setLayerName(LayerNames.twoDGaN);
        p1l1.setCondition(Parameters.H2,5000);
        p1l1.setCondition(Parameters.NH3,3000);
        p1l1.setCondition(Parameters.TMG,30);
        p1l1.setCondition(Parameters.T,1000);
        p1l1.setCondition(Parameters.P,200);

        p1l2.setFunction(Functions.Buffer);
        p1l2.setLayerName(LayerNames.GaN);
        p1l2.setCondition(Parameters.H2,5000);
        p1l2.setCondition(Parameters.NH3,3000);
        p1l2.setCondition(Parameters.TMG,35);
        p1l2.setCondition(Parameters.T,1040);
        p1l2.setCondition(Parameters.P,200);


        p1l3.setFunction(Functions.QW);
        p1l3.setLayerName(LayerNames.InGaN);
        p1l3.setCondition(Parameters.H2,5000);
        p1l3.setCondition(Parameters.NH3,3000);
        p1l3.setCondition(Parameters.TMG,25);
        p1l3.setCondition(Parameters.T,800);
        p1l3.setCondition(Parameters.P,400);
        p1l3.setCondition(Parameters.TMI,40);

        //second structure

        p2l1.setFunction(Functions.Buffer);
        p2l1.setLayerName(LayerNames.GaN);
        p2l1.setCondition(Parameters.H2,5000);
        p2l1.setCondition(Parameters.NH3,3000);
        p2l1.setCondition(Parameters.TMG,25);
        p2l1.setCondition(Parameters.T,1040);
        p2l1.setCondition(Parameters.P,200);

        p2l2.setFunction(Functions.Buffer);
        p2l2.setLayerName(LayerNames.GaN);
        p2l2.setCondition(Parameters.H2,5000);
        p2l2.setCondition(Parameters.NH3,3000);
        p2l2.setCondition(Parameters.TMG,25);
        p2l2.setCondition(Parameters.T,1030);
        p2l2.setCondition(Parameters.P,200);

        p2l3.setFunction(Functions.InterLayer);
        p2l3.setLayerName(LayerNames.SiN);
        p2l3.setCondition(Parameters.H2,5000);
        p2l3.setCondition(Parameters.NH3,3000);
        p2l3.setCondition(Parameters.T,1040);
        p2l3.setCondition(Parameters.P,200);
        p2l3.setCondition(Parameters.Si,20);

        p2l4.setFunction(Functions.Buffer);
        p2l4.setLayerName(LayerNames.AlGaN);
        p2l4.setCondition(Parameters.H2,5000);
        p2l4.setCondition(Parameters.NH3,3000);
        p2l4.setCondition(Parameters.TMG,25);
        p2l4.setCondition(Parameters.T,1040);
        p2l4.setCondition(Parameters.P,200);
        p2l4.setCondition(Parameters.TMA,10);

        p2l5.setFunction(Functions.QW);
        p2l5.setLayerName(LayerNames.InGaN);
        p2l5.setCondition(Parameters.H2,5000);
        p2l5.setCondition(Parameters.NH3,3000);
        p2l5.setCondition(Parameters.TMG,25);
        p2l5.setCondition(Parameters.T,800);
        p2l5.setCondition(Parameters.P,400);
        p2l5.setCondition(Parameters.TMI,40);

        //initializing processes
        p1.setName("a121212a");
        p1.setSubstrate(Substrates.Sapphire);
        p2.setName("a121212b");
        p2.setSubstrate(Substrates.Si);
        p1.addLayer(p1l1);
        p1.addLayer(p1l2);
        p1.addLayer(p1l3);
        p2.addLayer(p2l1);
        p2.addLayer(p2l2);
        p2.addLayer(p2l3);
        p2.addLayer(p2l4);
        p2.addLayer(p2l5);

        System.out.println(p1.compareTo(p2, true));
    }
}
