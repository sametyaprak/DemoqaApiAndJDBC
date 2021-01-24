package TESTNG.tests.JDBC;

import TESTNG.utilities.DatabaseConnector;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class US_11_Test {
    String queryTC1101 =    "select *\n" +
                            "from tp_customer\n" +
                            "where \"id\">40000 and city !='Athens' and user_id>5";
    String queryTC1102 = "select *\n" +
                         "from tp_account\n" +
                           "where balance=5000\n" +
                         "limit 40";
    ResultSet resultSet;
    @Test
    public void TC1101(){
        List<Map<String,String>> myAllData;
        myAllData = DatabaseConnector.getQueryResultWithAListMap(queryTC1101);
        Assert.assertEquals(myAllData.size(),67);
         }
    @Test
    public void TC1102(){
        List<Map<String,String>> myAlldata;
        myAlldata = DatabaseConnector.getQueryResultWithAListMap(queryTC1102);
        for(int i=0;i<myAlldata.size();i++){
            Assert.assertTrue(myAlldata.get(i).get("account_status_type").equals("ACTIVE"));
            }
        }
    @Test
    public void TC1103(){

    }
}
