package TESTNG.tests.JDBC;

import TESTNG.utilities.DatabaseConnectorX;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class US_06_Test {
    String queryTC0601 = "select count (country_id) as cityes\n" +
                        "from tp_customer   \n" +
                        "where city = 'Athens' or city = 'athens'";
    String queryTC0602 = "";
    String queryTC0603 = "";

    @Test
    public void tc0601(){
        List<Map<String,String>>allData = DatabaseConnectorX.getQueryResultWithAListMap(queryTC0601);
        Assert.assertTrue(allData.get(0).get("cityes").equals("3"));
    }
    @Test
    public void tc0602(){

    }
    @Test
    public void tc0603(){

    }

}
