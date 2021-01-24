package TESTNG.tests.JDBC;

import TESTNG.utilities.DatabaseConnector;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class US_07_Test {
    String queryTC0701 = "select \"id\",login,email, activation_key\n" +
                          "from jhi_user\n" +
                           "where activation_key is not null \n" +
                          "order by \"id\" asc ";

    String queryTC0702 =     "select *\n" +
                            "from jhi_user_authority\n" +
                             "where authority_name='ROLE_ADMIN'\n" +
                            "order by user_id desc ";
    ResultSet resultSet;


    @Test
    public void tc0701(){
        List<Map<String,String>> allDataList;
        allDataList = DatabaseConnector.getQueryResultWithAListMap(queryTC0701);
        for (int i=0; i<allDataList.size();i++){
            Assert.assertFalse(allDataList.get(4).containsValue(null));
        }
    }
    @Test
    public void tc0702(){
        List<Map<String,String>> allDataList;
        allDataList = DatabaseConnector.getQueryResultWithAListMap(queryTC0702);
        for (int i=0;i<allDataList.size()-1;i++){
            Assert.assertTrue(Integer.valueOf(allDataList.get(i).get("user_id"))>Integer.valueOf(allDataList.get(i+1).get("user_id")));
            Assert.assertEquals(allDataList.get(i).get("authority_name"),"ROLE_ADMIN");
        }
    }
}
