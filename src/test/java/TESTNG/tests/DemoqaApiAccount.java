package TESTNG.tests;

import static io.restassured.RestAssured.*;

import TESTNG.pojos.DemoqaAuthorizedPojo;
import TESTNG.utilities.ExcelUtilX;
import TESTNG.utilities.ReusableMethodsX;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DemoqaApiAccount {
    public Response getResponse() {
        return response;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getEndPointPostAuthorized() {
        return endPointPostAuthorized;
    }

    public String getEndpointPostGenerateToken() {
        return endpointPostGenerateToken;
    }

    public String getEndPointPostUser() {
        return endPointPostUser;
    }

    public ExcelUtilX getExcelUtilX() {
        return excelUtilX;
    }

    Response response;
    private String baseUrl = "https://demoqa.com";
    private String endPointPostAuthorized = "/Account/v1/Authorized";
    private String endpointPostGenerateToken = "/Account/v1/GenerateToken";
    private String endPointPostUser = "/Account/v1/User";

    ExcelUtilX excelUtilX = new ExcelUtilX("src/test/resources/database.xlsx","demoqa");

    @Test
    public void postAuthorized() throws IOException {
        DemoqaAuthorizedPojo demoqaAuthorizedPojo =
                new DemoqaAuthorizedPojo(   excelUtilX.getCellData(1,0),
                                            excelUtilX.getCellData(1,1));
        response = given().
                    contentType(ContentType.JSON).
                    auth().
                    basic(excelUtilX.getCellData(1,0), excelUtilX.getCellData(1,1)).
                    body(demoqaAuthorizedPojo).
                    when().
                    post(baseUrl+endPointPostAuthorized);
        response.prettyPrint();
    }
    @Test
    public void postGenerateToken() throws IOException, InvalidFormatException {
        DemoqaAuthorizedPojo demoqaAuthorizedPojo =
                new DemoqaAuthorizedPojo(   excelUtilX.getCellData(1,0),
                                            excelUtilX.getCellData(1,1));
        response = given().
                    contentType(ContentType.JSON).
                    auth().
                    basic(excelUtilX.getCellData(1,0), excelUtilX.getCellData(1,1)).
                    body(demoqaAuthorizedPojo).
                    when().
                    post(baseUrl+ endpointPostGenerateToken);

            response.prettyPrint();
            JsonPath json = response.jsonPath();
            System.out.println(json.getString("token"));
            String token = json.getString("token");
            excelUtilX.setCellData(token,1,2);
    }
    @Test
    public void postUser() throws IOException, InvalidFormatException {
        String passwordNew = "ABCahc987+%&";
        String usernameNew = ReusableMethodsX.randomString(4)+ "excelDENmu23";
        DemoqaAuthorizedPojo demoqaAuthorizedPojo = new DemoqaAuthorizedPojo(usernameNew,passwordNew);
        response = given().
                    contentType(ContentType.JSON).
                    auth().
                    basic(excelUtilX.getCellData(1,0), excelUtilX.getCellData(1,1)).
                    body(demoqaAuthorizedPojo).
                    when().
                    post(baseUrl+endPointPostUser);

            response.prettyPrint();
            JsonPath json = response.jsonPath();
            String userID = json.getString("userID");
            excelUtilX.setCellData(userID,1,3);
            String username = json.getString("username");
            excelUtilX.setCellData(username,1,0);
            excelUtilX.setCellData(passwordNew,1,1);
    }
    @Test
    public void getUser() throws FileNotFoundException {
        response = given().
                    accept(ContentType.JSON).
                    auth().
                    oauth2(excelUtilX.getCellData(1,2)).
                    when().
                    get(baseUrl+ "/Account/v1/User/"+ excelUtilX.getCellData(1,3));
            response.prettyPrint();
    }
}
