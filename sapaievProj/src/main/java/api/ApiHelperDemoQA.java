package api;

import api.dto.DemoQADTO.BookDTO;
import api.dto.DemoQADTO.BooksDTO;
import api.dto.DemoQADTO.UserDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiHelperDemoQA {




    private final String USER_NAME_FOR_LOGIN="Test2107";
    private final String PASSWORD_FOR_LOGIN ="Test2107@";

    private Logger logger=Logger.getLogger(getClass());

    RequestSpecification requestSpecification=new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL).build();



    public String getToken() {

        return getToken(USER_NAME_FOR_LOGIN, PASSWORD_FOR_LOGIN);
    }

    public String getToken(String userName, String password) {

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userName",USER_NAME_FOR_LOGIN);
        jsonObject.put("password", PASSWORD_FOR_LOGIN);

        String userDTOresponse=
                given().contentType(ContentType.JSON)
                        .log()
                        .all()
                        .body(jsonObject.toMap())
                        .when().post(EndpointsHomeWorkDemoQA.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().jsonPath().get("token");
                ;

        return  userDTOresponse;
    }


    public String getId() {

        return getId(USER_NAME_FOR_LOGIN, PASSWORD_FOR_LOGIN);
    }

    public String getId(String userName, String password) {

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userName",USER_NAME_FOR_LOGIN);
        jsonObject.put("password", PASSWORD_FOR_LOGIN);

        String userDTOresponse=
                given().contentType(ContentType.JSON)
                        .log()
                        .all()
                        .body(jsonObject.toMap())
                        .when().post(EndpointsHomeWorkDemoQA.LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().jsonPath().get("userId");
        ;
        return  userDTOresponse;
    }


    public String getIsbnofBooksList(int numberOfBook){
        BooksDTO books =
                given().contentType(ContentType.JSON)
                        .log()
                        .all()
                        .when().get(EndpointsHomeWorkDemoQA.GET_LIST_OF_BOOKS)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().as(BooksDTO.class);

        BookDTO[] listOfbook=books.getBooks();
       if(numberOfBook<listOfbook.length+1) {
           return listOfbook[numberOfBook-1].getIsbn();
       }else {
           logger.info("You selected incorrect number of book");
           return "You selected incorrect number of book";
       }
    }







}
