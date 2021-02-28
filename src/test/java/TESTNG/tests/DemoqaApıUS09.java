package TESTNG.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class DemoqaApıUS09 {


    Response response;
    String endPoint = "https://demoqa.com/BookStore/v1/Book";
    String isbnNumber = "9781593277574";
    JsonPath jsonPath;
    String result;
    Date date;

    Response getResponse(String isbnNumber,String endPoint){
        response = given().queryParam("ISBN",isbnNumber).when().get(endPoint);
        return response;
    }

    String returnTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd MMM yyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
//        yyyy-MM-dd	"1988-09-29"
//        dd/MM/yyyy	"29/09/1988"
//        dd-MMM-yyyy	"29-Sep-1988"
//        E, MMM dd yyyy	"Thu, Sep 29 1988"
//         E, dd MMM yyy HH:mm:ss 'GMT'
        return dtf.format(now);
    }

    @Test
    public void TC0901(){
        Assert.assertFalse(getResponse(isbnNumber,endPoint).equals(null));
        System.out.println(getResponse(isbnNumber,endPoint).getHeader("date"));

    }

    @Test
    public void TC0902(){
        getResponse(isbnNumber,endPoint).prettyPrint();
        getResponse(isbnNumber,endPoint).then().assertThat().statusCode(HttpStatus.SC_OK);
        getResponse(isbnNumber,endPoint).then().assertThat().body("subTitle",Matchers.equalTo("The Definitive Guide for JavaScript Developers"));
        getResponse(isbnNumber,endPoint).then().assertThat().body("publish_date",Matchers.equalTo("2016-09-03T00:00:00.000Z"));
        getResponse(isbnNumber,endPoint).then().assertThat().body("pages",Matchers.equalTo(352));
        jsonPath=response.jsonPath();
        Assert.assertTrue(jsonPath.getString("description").startsWith("ECMAScript 6 represents the biggest update"));
        Assert.assertTrue(jsonPath.getString("description").endsWith("that E"));
        Assert.assertTrue(jsonPath.getString("description").contains("expert developer Nicholas C. Zakas"));
    }
    @Test
    public void TC0903(){
        getResponse(isbnNumber,endPoint).then().assertThat().header("content-length",Matchers.equalTo("560"));
        getResponse(isbnNumber,endPoint).then().assertThat().header("content-type",Matchers.equalTo("application/json; charset=utf-8"));
        Assert.assertEquals(getResponse(isbnNumber,endPoint).getHeader("date").substring(0,16),returnTime());
        getResponse(isbnNumber,endPoint).then().assertThat().header("server",Matchers.equalTo("nginx/1.17.10 (Ubuntu)"));
    }




}
