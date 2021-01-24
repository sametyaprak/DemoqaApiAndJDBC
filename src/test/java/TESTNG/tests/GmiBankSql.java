package TESTNG.tests;

import TESTNG.utilities.ConfigReader;
import TESTNG.utilities.DatabaseConnectorX;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GmiBankSql {

    String userDataQuery = "SELECT * FROM " + ConfigReader.getProperty("usersTableName");
    String userDataCountryQuery = "SELECT * FROM " + ConfigReader.getProperty("countriesTableName");
    ResultSet resultSetUser;
    List<String> firstNamesData = new ArrayList<>();
    List<String> countryesData = new ArrayList<>();

    @Test
    public void test1 () throws SQLException {
        //gmi database.e baglandik
        resultSetUser = DatabaseConnectorX.getResultSet(userDataQuery);
        //metadata.lari aldik
        ResultSetMetaData userMetaData = resultSetUser.getMetaData();
        //metadatalardan column sayisini aldik ve yazdirdik.
        int column = userMetaData.getColumnCount();
        System.out.println("total columns number: "+column);
        for (int i=1 ; i<=column;i++){
            System.out.println(userMetaData.getColumnName(i));
        }
        //first name columundaki tum verileri topluyoruz.
        while (resultSetUser.next()){
            String firstnames = resultSetUser.getString("first_name");
            firstNamesData.add(firstnames);
        }
        System.out.println(firstNamesData);

        String userFirstNameQuery = "SELECT first_name FROM " + ConfigReader.getProperty("usersTableName");
        resultSetUser = DatabaseConnectorX.getResultSet(userFirstNameQuery);
        List<String> actualFirstNameList = new ArrayList<>();

        while (resultSetUser.next()){
            String firstName = resultSetUser.getString(1);
            actualFirstNameList.add(firstName);
        }
        System.out.println(actualFirstNameList);
        Assert.assertEquals(actualFirstNameList,firstNamesData);
    }


    @Test
    public void countryDataQuery () throws SQLException {
        resultSetUser = DatabaseConnectorX.getResultSet(userDataCountryQuery);
        System.out.println(resultSetUser.getMetaData().getColumnCount());
        String firstColumn = resultSetUser.getMetaData().getColumnName(1);
        String secondColumn = resultSetUser.getMetaData().getColumnName(2);
        System.out.println(firstColumn);
        System.out.println(secondColumn);
        while (resultSetUser.next()){
            String countryNames = resultSetUser.getString("name");
            countryesData.add(countryNames);
        }
        System.out.println(countryesData.size());
        System.out.println(countryesData);
    }

}
