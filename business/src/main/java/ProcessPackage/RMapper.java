package ProcessPackage;

import org.springframework.jdbc.core.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RMapper implements RowMapper <GeneralLayer> {

    @Override
    public GeneralLayer mapRow(ResultSet resultSet, int i) throws SQLException {

        String function = resultSet.getString("function");
        String LayerName = resultSet.getString("LayerName");

        if (function.equals(Functions.Buffer.toString()) && LayerName.equals("GaN")) {
            GeneralLayer L = new GaN_Buffer();
            L.setCondition(Parameters.T, resultSet.getInt("temperature"));
            L.setCondition(Parameters.P, resultSet.getInt("pressure"));
            L.setCondition(Parameters.H2, resultSet.getInt("h2"));
            L.setCondition(Parameters.N2, resultSet.getInt("n2"));
            L.setCondition(Parameters.NH3, resultSet.getInt("nh3"));
            L.setCondition(Parameters.TMG, resultSet.getInt("tmg"));
            L.setCondition(Parameters.TMA, resultSet.getInt("tma"));
            L.setCondition(Parameters.TMI, resultSet.getInt("tmi"));
            L.setCondition(Parameters.Si, resultSet.getInt("si"));
            L.setCondition(Parameters.Mg, resultSet.getInt("mg"));
            L.setCondition(Parameters.time, resultSet.getInt("time"));
            L.setCondition(Parameters.growth_rate, resultSet.getInt("growthrate"));
            return L;
        }
    return null;
    }
}
