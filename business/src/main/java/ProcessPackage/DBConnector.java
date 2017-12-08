package ProcessPackage;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {

    private static final Logger log = Logger.getLogger(DBConnector.class);

    ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"springContext.xml"});

    JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);

    private static void separator(String title) {
        log.debug("\r\n\r\n\r\n\r\n");
        log.debug("========== {} ==========" + title);
        log.debug("");
    }

    private static void queryForLayerByName(JdbcTemplate jdbc) {
        separator("queryForLaler");
        GeneralLayer Layer = jdbc.queryForObject("select region_id, region_name from region.jc_region where region_id = ? and region_name = ?", new Object[]{1L, "Moscow"}, new RMapper());
        log.debug("Region = {}" + Layer.toString());
    }


}
