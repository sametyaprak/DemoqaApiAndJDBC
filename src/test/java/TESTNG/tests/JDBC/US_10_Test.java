package TESTNG.tests.JDBC;

import TESTNG.utilities.DatabaseConnector;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class US_10_Test {
    ResultSet resultSet;
    String queryTC1001 = "select sum(balance) as total_balance\n" +
                         "from tp_account";
    String queryTC1002 = "select sum(transaction_amount) as total_transaction\n" +
                         "from tp_transaction_log\n" +
                         "where transaction_date between 'Nov 01, 2020, 00:00:01 AM' and 'Nov 30, 2020, 12:59:59 PM'";

    @Test
    public void TC1001() throws SQLException {
        String data ="";
        resultSet = DatabaseConnector.executeQuery(queryTC1001);
        while (resultSet.next()){
            data = resultSet.getString(1);
        }
        Assert.assertEquals(data,"5605212527");
    }
    @Test
    public void TC1003() throws SQLException {
        String data ="";
        resultSet = DatabaseConnector.executeQuery(queryTC1002);
        while (resultSet.next()){
            data = resultSet.getString(1);
        }
        Assert.assertEquals(data,"18879884");
    }



}
