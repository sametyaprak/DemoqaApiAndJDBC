package TESTNG.tests.JDBC;

import TESTNG.utilities.DatabaseConnector;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class US_05_Test {

    String queryTC0501 = "select login " +
                        "from jhi_user " +
                        "    inner join jhi_user_authority " +
                        "    on jhi_user_authority.user_id=jhi_user.id " +
                        "where   jhi_user_authority.authority_name = 'ROLE_ADMIN' and login like '%admin%' ";


    String queryTC0502 = "select \"login\" ,jhi_user.activated, jhi_user_authority.authority_name\n" +
                        "from jhi_user\n" +
                        "    inner join jhi_user_authority\n" +
                        "    on jhi_user_authority.user_id=jhi_user.\"id\"\n" +
                        "where   jhi_user_authority.authority_name = 'ROLE_ADMIN' and activated ='false'";

    String queryTC0503 = "select first_name,last_name, substring (last_name,1,4)\n" +
                        "    from tp_customer\n" +
                        "    join tp_country\n" +
                        "    on tp_customer.country_id=tp_country.id\n" +
                        "    where name='Belgium' limit 1     ";

    ResultSet resultSet;
    List<String> myQueryDatatc0501 = new ArrayList<>();
    List<String> myQueryDatatc0502 = new ArrayList<>();

    @Test
    public void tc0501() throws SQLException {
        resultSet = DatabaseConnector.getResultSet(queryTC0501);
        while (resultSet.next()){
            String data = resultSet.getString("login");
            myQueryDatatc0501.add(data);
            Assert.assertTrue(data.contains("admin"));
        }
        //System.out.println(myQueryDatatc0501);
        Assert.assertEquals(myQueryDatatc0501.size(),47);
    }

    @Test
    public void tc0502 () throws SQLException {
        resultSet = DatabaseConnector.getResultSet(queryTC0502);
        while (resultSet.next()){
            String data = resultSet.getString("login");
            myQueryDatatc0502.add(data);
        }
        Assert.assertEquals(myQueryDatatc0502.size(),9);
    }
    @Test
    public void tc0503 () throws SQLException {
        resultSet = DatabaseConnector.getResultSet(queryTC0503);
        while (resultSet.next()){
            String data = resultSet.getString(1);

        }
    }

}
