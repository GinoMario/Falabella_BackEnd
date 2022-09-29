package services.Common;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.framework.config.ConfigReader;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class RestAssuredConfiguration {

    @BeforeSuite(alwaysRun = true)
    public void configure() throws IOException {

        ConfigReader.PopulateSettings();

    }

    public RequestSpecification getRequestSpecification(String baseUri, String basePAth, int basePort) {

        RestAssured.baseURI = baseUri;
        if (basePort != 0) {
            RestAssured.port = basePort;
        }
        RestAssured.basePath = basePAth;

        return RestAssured.given().contentType(ContentType.JSON);
    }

    public RequestSpecification getRequestSpecificationAll(String baseUri, String basePAth, int basePort) {

        RestAssured.baseURI = baseUri;
        if (basePort != 0) {
            RestAssured.port = basePort;
        }
        RestAssured.basePath = basePAth;

        return RestAssured.given().contentType(ContentType.ANY);
    }

    public RequestSpecification getRequestSpecificationFile(String baseUri, String basePAth, int basePort) {

        RestAssured.baseURI = baseUri;
        if (basePort != 0) {
            RestAssured.port = basePort;
        }
        RestAssured.basePath = basePAth;

        return RestAssured.given().contentType("multipart/form-data");
    }


    public Response getResponseGet(RequestSpecification requestSpecification, String endpoint) {
        requestSpecification.log().all();
        Response response = requestSpecification.get(endpoint);
        response.then().log().all();
        return response;
    }

    public Response getResponsePost(RequestSpecification requestSpecification, String endpoint) {
        requestSpecification.log().all();
        Response response = requestSpecification.post(endpoint);
        response.then().log().all();
        return response;
    }

    public Response getResponsePut(RequestSpecification requestSpecification, String endpoint) {
        requestSpecification.log().all();
        Response response = requestSpecification.put(endpoint);
        response.then().log().all();
        return response;
    }

    public Response getResponseDelete(RequestSpecification requestSpecification, String endpoint) {
        requestSpecification.log().all();
        Response response = requestSpecification.delete(endpoint);
        response.then().log().all();
        return response;
    }

    public Response getResponsePatch(RequestSpecification requestSpecification, String endpoint) {
        requestSpecification.log().all();
        Response response = requestSpecification.patch(endpoint);
        response.then().log().all();
        return response;
    }


}