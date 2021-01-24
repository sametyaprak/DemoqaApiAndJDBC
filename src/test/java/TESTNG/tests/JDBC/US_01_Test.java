package TESTNG.tests.JDBC;

import TESTNG.utilities.DatabaseConnectorX;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class US_01_Test {

    ResultSet resultSet;
    String queryTC0101 =    "select *\n" +
                            "from tp_customer\n" +
                            "where first_name like 'R%' and last_name like '_a%'";


    @Test
    public void tc0101 (){
        List<Map<String,String>> data = DatabaseConnectorX.getQueryResultWithAListMap(queryTC0101);
        Assert.assertTrue(data.size()<45);
    }
    @Test
    public void tc0102 (){

    }
    @Test
    public void tc0103 (){

    }
    @Test
    public void tc0104 (){

    }
}
