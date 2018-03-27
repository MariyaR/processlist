package ProcessPackage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

//@Local
//@Stateless
//@TransactionManagement(value = TransactionManagementType.BEAN)
public class TranzactionManagerEJB {

//    private Logger log = LoggerFactory.getLogger(TranzactionManagerEJB.class);
//
//
//    @Resource(mappedName = "java:/jdbc/ProcessList", type = DataSource.class)
//    private DataSource dataSource;
//
//    @Resource(mappedName = "java:comp/UserTransaction")
//    private UserTransaction transaction;
//
//
//    public GeneralLayer getLayer(String ProcessName, String LayerName, int LayerId) {
//        GaN_Buffer Layer = new GaN_Buffer();
//        StringBuffer s= new StringBuffer();
//        PreparedStatement st;
//        ResultSet rs;
//        log.info("getLayer is invoked");
//        System.out.println("getLayer is invoked");
//
//
//        try  {
//
//           transaction.begin();
//            Connection con = dataSource.getConnection();
//            log.debug("AutoCommit AllRegions: {}", con.getAutoCommit());
//
//            st = con.prepareStatement("select temperature, pressure, h2, n2, nh3, tmg, tma, tmi, " +
//                    "mg, si, growthtime, growthrate from layer where processname=? and layername=? and layerid=?");
//            st.setString(1, ProcessName);
//            st.setString(2, LayerName);
//            st.setInt(3,LayerId);
//            rs = st.executeQuery();
//
//
//            while (rs.next()) {
//
//                Layer.setCondition(Parameters.T, rs.getInt("temperature"));
//                Layer.setCondition(Parameters.P, rs.getInt("pressure"));
//                Layer.setCondition(Parameters.H2, rs.getInt("h2"));
//                Layer.setCondition(Parameters.N2, rs.getInt("n2"));
//                Layer.setCondition(Parameters.NH3, rs.getInt("nh3"));
//                Layer.setCondition(Parameters.TMG, rs.getInt("tmg"));
//                Layer.setCondition(Parameters.TMA, rs.getInt("tma"));
//                Layer.setCondition(Parameters.TMI, rs.getInt("tmi"));
//                Layer.setCondition(Parameters.Mg, rs.getInt("mg"));
//                Layer.setCondition(Parameters.Si, rs.getInt("si"));
//                Layer.setCondition(Parameters.time, rs.getInt("growthtime"));
//                Layer.setCondition(Parameters.growth_rate, rs.getInt("growthrate"));
//
//                s.append("result " +Layer.getCondition(Parameters.Mg));
//            }
//
//            transaction.commit();
//            rs.close();
//            st.close();
//            con.close();
//            return Layer;
//
//        } catch (Exception ex) {
//            log.error("{}: {}", ex.getClass().getCanonicalName(), ex.getMessage());
//            try {
//                transaction.rollback();
//            } catch (SystemException e) {
//                e.printStackTrace();
//                log.error("{}: {}", e.getClass().getCanonicalName(), e.getMessage());
//            }
//
//        }
//
//    return Layer;
//    }
//



}
