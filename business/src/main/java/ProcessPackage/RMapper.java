package ProcessPackage;

import org.springframework.jdbc.core.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RMapper implements RowMapper <GeneralLayer> {

    @Override
    public GeneralLayer mapRow(ResultSet resultSet, int i) throws SQLException {

        //String function = resultSet.getString("layerfunction");
       // String LayerName = resultSet.getString("LayerName");

       // if (function.equals(Functions.Buffer.toString()) && LayerName.equals("GaN")) {
            GeneralLayer L = new GaN_Buffer();
            L.setTemperature(resultSet.getInt("temperature"));
            L.setPressure(resultSet.getInt("pressure"));
            L.setH2(resultSet.getInt("h2"));
            L.setN2(resultSet.getInt("n2"));
            L.setNh3(resultSet.getInt("nh3"));
            L.setTmg(resultSet.getInt("tmg"));
            L.setTma(resultSet.getInt("tma"));
            L.setTmi(resultSet.getInt("tmi"));
            L.setSi(resultSet.getInt("si"));
            L.setMg(resultSet.getInt("mg"));
            L.setGrowthTime(resultSet.getInt("growthtime"));
            L.setGrowthRate(resultSet.getInt("growthrate"));
            return L;
      //  }
   // return null;
    }
}
