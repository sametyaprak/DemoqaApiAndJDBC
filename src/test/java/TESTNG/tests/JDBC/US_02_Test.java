package TESTNG.tests.JDBC;

import TESTNG.utilities.DatabaseConnectorX;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class US_02_Test {

    ResultSet resultSet;
    String queryTC0201  =   "select count (zelle_enrolled) as total_true, zelle_enrolled\n" +
                            "from tp_customer\n" +
                            "group by zelle_enrolled ";
    String queryTC0202 = "select *\n" +
                            "from jhi_user\n" +
                            "where activated = false ";
    String queryTC0203 =    "select *\n" +
                            "from tp_customer\n" +
                            "where zip_code is not null ";

    @Test
    public void tc0201(){
        List<Map<String,String>> data = DatabaseConnectorX.getQueryResultWithAListMap(queryTC0201);
        System.out.println(data.get(1).get("total_true"));
        System.out.println(data.get(2).get("total_true"));
    }
    @Test
    public void tc0202(){
        List<Map<String,String>> data = DatabaseConnectorX.getQueryResultWithAListMap(queryTC0202);
        System.out.println(data.size());
    }
    @Test
    public void tc0203(){
        List<Map<String,String>> data = DatabaseConnectorX.getQueryResultWithAListMap(queryTC0203);
        System.out.println(data.size());
    }
}
