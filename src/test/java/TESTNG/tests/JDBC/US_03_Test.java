package TESTNG.tests.JDBC;

import TESTNG.utilities.DatabaseConnectorX;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class US_03_Test {

    ResultSet resultSet;

    String queryTC0301 =    "select *\n" +
                            "from tp_account\n" +
                            "where \"id\"<30000 and account_type ='SAVING' and balance = 50000";
    String queryTC0302 =    "select *\n" +
                            "from jhi_user\n" +
                            "where first_name like '_a%' and first_name like '%e' " +
                            "and created_by = 'anonymousUser' and activated = false";
    String queryTC0303 =    "select *\n" +
                            "from jhi_user\n" +
                            "where last_modified_by like 'ad%' and  last_modified_by like '%n' \n" +
                            " last_modified_by like or '%e' or last_modified_by like '%a');";

    @Test
    public void tc0301() throws SQLException {
        resultSet = DatabaseConnectorX.getResultSet(queryTC0301);
        while (resultSet.next()){
            Assert.assertEquals(resultSet.getString("account_type"),"SAVING");
            Assert.assertEquals(resultSet.getInt("balance"),50000);
            Assert.assertTrue(resultSet.getInt("id")<30000);
        }
    }
    @Test
    public void tc0302(){
        List<Map<String,String>> data = DatabaseConnectorX.getQueryResultWithAListMap(queryTC0302);
        System.out.println(data.size());
    }
    @Test
    public void tc0303(){

    }
}
