package ProcessPackage;

import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

//@WebServlet(name = "SimpleProcessListServlet", urlPatterns = {"/ProcessListServlet"})
public class ProcessListServlet {//extends HttpServlet{
//
//    @EJB(beanName = "LayerComparerEJB")
//    private ComparerEJBLocal comparerEJBLocal;
//
//    @EJB
//    private TranzactionManagerEJB ejb;
//
//    //access to DataBase via Spring
//
////    SpringDBConnector dbConnector = new SpringDBConnector();
//
//    GeneralLayer Layer1= new GaN_Buffer();
//    GeneralLayer Layer2= new GaN_Buffer();
//
//
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    String ProcessName1 = request.getParameter("ProcessName1");
//    String LayerName1 = request.getParameter("LayerName1");
//    String ProcessName2 = request.getParameter("ProcessName2");
//    String LayerName2 = request.getParameter("LayerName2");
//    int LayerId1=parseInt(request.getParameter("Id1"));
//    int LayerId2=parseInt(request.getParameter("Id2"));
//
////    Layer1=dbConnector.getLayer(ProcessName1,LayerName1);
////    Layer2=dbConnector.getLayer(ProcessName2,LayerName2);
////
//        Layer1=ejb.getLayer(ProcessName1,LayerName1,LayerId1);
//        Layer2=ejb.getLayer(ProcessName2,LayerName2,LayerId2);
//        String comparision = comparerEJBLocal.compare2(Layer1,Layer2, false);
//
//
//    response.getWriter().write("layer1: "+"\n"+Layer1.toString()  + "\n");
//    response.getWriter().write("layer2: "+"\n"  +Layer2.toString()+ "\n");
//    response.getWriter().write("comparision: "+"\n"  +comparision+ "\n");
//    response.getWriter().close();
//
//    }
//

}
