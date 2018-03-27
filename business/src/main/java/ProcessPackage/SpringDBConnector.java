package ProcessPackage;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class SpringDBConnector {

    private static final Logger log = LogManager.getLogger(SpringDBConnector.class);

    ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"springContext.xml"});

    JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);

    private static void separator(String title) {
        log.debug("\r\n\r\n\r\n\r\n");
        log.debug("========== {} ==========" + title);
        log.debug("");
    }

    private static GeneralLayer queryForLayerByName(JdbcTemplate jdbc, String ProcessName, String LayerName) {
        separator("queryForLayer");
        GeneralLayer Layer = jdbc.queryForObject("select temperature, pressure, h2, n2, nh3," +
                "tmg, tma, tmi, mg, si, growthtime, growthrate from layer " +
                "where processname = ? and layername = ? and layerid = ?", new Object[]{ProcessName, LayerName,1}, new RMapper());
        log.debug("Layer:" + Layer.toString());
        return Layer;
    }

    public GeneralLayer getLayer(String ProcessName, String LayerName) {
        return queryForLayerByName(jdbc, ProcessName, LayerName);
    }

}
