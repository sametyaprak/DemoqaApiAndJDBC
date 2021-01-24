package TESTNG.tests;

import TESTNG.pojos.DemoqaAuthorizedPojo;
import TESTNG.utilities.ExcelUtilX;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoqaApiAccountUsAndTest {

    DemoqaApiAccount demoqaApiAccount = new DemoqaApiAccount();
    ExcelUtilX excelUtilX = new ExcelUtilX("src/test/resources/database.xlsx","demoqa");
    Response response;
    @Test
    public void TC0101(){
        DemoqaAuthorizedPojo demoqaAuthorizedPojo =
                new DemoqaAuthorizedPojo("ttrycatch","JQ3iPpTEKTLjSQJ!");
        response = given()
                    .contentType(ContentType.JSON)
                    .auth()
                    .oauth2(excelUtilX.getCellData(1,0))
                    .body(demoqaAuthorizedPojo)
                    .post(demoqaApiAccount.getBaseUrl()+demoqaApiAccount.getEndPointPostAuthorized());
        response.prettyPrint();
    }
    public Response getAutorizedResponse(Object username, Object password){
        DemoqaAuthorizedPojo demoqaAuthorizedPojo =
                new DemoqaAuthorizedPojo(username,password);
        response = given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(excelUtilX.getCellData(1,0))
                .body(demoqaAuthorizedPojo)
                .post(demoqaApiAccount.getBaseUrl()+demoqaApiAccount.getEndPointPostAuthorized());
        return response;
    }
    @Test
    public void TC0101v2(){
        getAutorizedResponse("ttrycatch","JQ3iPpTEKTLjSQJ!").prettyPrint();
        System.out.println(response);
        Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test
    public void TC0102(){
        getAutorizedResponse(123456,"JQ3iPpTEKTLjSQJ!").prettyPrint();
        System.out.println();
    }
    @Test
    public void TC0103(){
        getAutorizedResponse(123456,"").prettyPrint();
        System.out.println();
    }


}
