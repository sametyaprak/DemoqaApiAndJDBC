package TESTNG.tests;

import static io.restassured.RestAssured.*;

import TESTNG.utilities.ExcelUtilX;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DemoqaApiBookStore {

    Response response;
    String baseUrl = "https://demoqa.com";

    String endPointGetBooks = "/BookStore/v1/Books";
    String endPointPostBooks = "/BookStore/v1/Books";
    String endPointGetBook =    "/BookStore/v1/Book";

    ExcelUtilX excelUtilX = new ExcelUtilX("src/test/resources/database.xlsx", "demoqa");
    List<String> myIsbnList;
    @Test
    public void getBooks() throws IOException {
        response = given().
                    accept(ContentType.JSON).
                    when().
                    get(baseUrl+endPointGetBooks);
        JsonPath json = response.jsonPath();
        myIsbnList = (json.getList("books.isbn"));
        int row = 1;
        for (String w: myIsbnList){
            excelUtilX.setCellData(w,row,4);
            row++;
        }
    }
    @Test
    public void test01() throws IOException {
        getBooks();

    }
    @Test
    public void postStoreBooks() throws IOException {
//        CollectionOfIsbnPojo collectionOfIsbnPojo = new CollectionOfIsbnPojo(excelUtilX.getCellData(1,4));
//        DemoqaBooksPojo demoqaBooksPojo = new DemoqaBooksPojo(excelUtilX.getCellData(1,3),collectionOfIsbnPojo);
//        System.out.println(MyUtilities.readOnList(bookIsbnList));
        String userId = excelUtilX.getCellData(1,3);
        String isbn = excelUtilX.getCellData(2,4);
        String data = "{\n" +
                "  \"userId\": \""+userId+"\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\": \""+isbn+"\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        response = given().
                    contentType(ContentType.JSON).
                    auth().
                    oauth2(excelUtilX.getCellData(1,2)).
                    body(data).
                    when().
                    post(baseUrl+endPointPostBooks);
        System.out.println(response.getStatusCode());
        response.prettyPrint();
    }
    @Test
    public void getBook(){
        String queryData = excelUtilX.getCellData(2,4);
        response = given().accept(ContentType.JSON).queryParam("ISBN",queryData).when().get(baseUrl+endPointGetBook);
        response.prettyPrint();
    }
}
