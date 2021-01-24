package TESTNG.tests.JDBC;

import TESTNG.utilities.ConfigReader;
import TESTNG.utilities.DatabaseConnector;
import TESTNG.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class US_04_Test extends TestBase {

    String insertQueryTc0401 = "insert into tp_state values (88,'Ankara', 1); ";
    String queryTC0401 = "select *\n" +
                            "from tp_state\n" +
                           "where id=88;";

    String insertQueryTc0402 = "insert into jhi_user (\"id\",\"login\",password_hash,first_name,last_name,activated,created_by)\n" +
            "            values (201,'DB','abc123','Ali','Adiguzel',true,'Veli')";
    String queryTC0402 = "select *\n" +
                        "from jhi_user\n" +
                        "where \"id\"=201" ;

    String insertQueryTc0403 = "insert into tp_country values (10,'japonya')";
    String queryTC0403 = "select *\n" +
                         "from tp_country\n" +
                         "where \"id\"=10;";

    ResultSet resultSet;

    @AfterMethod
    public void test() {
        DatabaseConnector.closeConnection();
    }

    @Test
    public void tc0401() throws SQLException {
        ConfigReader.getProperty("ali");
        String queryDataResult ="";
        DatabaseConnector.executeInsertQuery(insertQueryTc0401);
        resultSet = DatabaseConnector.getResultSet(queryTC0401);
        while (resultSet.next()) {
            queryDataResult = resultSet.getString("name");
            System.out.println(queryDataResult);
        }
        Assert.assertEquals(queryDataResult,"Ankara");
        }
        @Test
    public void tc0402(){
        DatabaseConnector object = new DatabaseConnector();
        List<Map<String,String>> myDataList;
        DatabaseConnector.executeInsertQuery(insertQueryTc0402);
        //myDataList = DatabaseConnector.getQueryResultWithAListMap(queryTC0402);
        myDataList = object.getQueryResultWithAListMap(queryTC0402);
        Assert.assertEquals(myDataList.get(0).get("id"),"201");
        Assert.assertEquals(myDataList.get(0).get("login"),"DB");
        }
        @Test
    public void tc0403() throws SQLException {
        DatabaseConnector.executeInsertQuery(insertQueryTc0403);
        resultSet = DatabaseConnector.getResultSet(queryTC0403);
        while (resultSet.next()){
            Assert.assertEquals(resultSet.getString("id"),"10");
            Assert.assertEquals(resultSet.getString(2),"japonya");
        }
        }

    }






