package services.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.framework.config.Settings;
import services.Common.EndPoint;
import services.Common.RestAssuredConfiguration;
import services.Common.ServicesUtil;

import java.util.List;
import java.util.Map;

public class ResponsePetServices {

    public static String baseApiUri = Settings.UrlApiBase;
    public static String pathServices = Settings.UrlApiPath;

    public static Response postAddNewPet(String CategoryName,String PetName,String Tag,String Status) {
        System.out.println("==============================================================");
        System.out.println("==================== POST - Add new pet ======================");
        System.out.println("==============================================================");

        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(baseApiUri, pathServices, 0);
        Map<String, String> bodyCategory = ServicesUtil.categoryBody(CategoryName);
        List<Map<String, String>> bodyTags = ServicesUtil.tagsBody(Tag);
        List<String> bodyPhoto = ServicesUtil.PhotoBody("");
        String strBody = ServicesUtil.PetInformationBody("0",bodyCategory,PetName,bodyTags,Status,bodyPhoto);
        return new RestAssuredConfiguration().getResponsePost(requestSpecification.body(strBody), EndPoint.POST_NEW_PET);
    }

    public static Response getFindAPetById(String idPet) {
        System.out.println("==============================================================");
        System.out.println("================== GET - Find a pet by id ====================");
        System.out.println("==============================================================");

        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(baseApiUri, pathServices, 0);
        return new RestAssuredConfiguration().getResponseGet(requestSpecification, EndPoint.GET_FIND_BY_ID + idPet);
    }

    public static Response putAddNewPet(String id, String CategoryName,String PetName,String Tag,String Status) {
        System.out.println("==============================================================");
        System.out.println("==================== PUT - Update a pet ======================");
        System.out.println("==============================================================");

        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(baseApiUri, pathServices, 0);
        Map<String, String> bodyCategory = ServicesUtil.categoryBody(CategoryName);
        List<Map<String, String>> bodyTags = ServicesUtil.tagsBody(Tag);
        List<String> bodyPhoto = ServicesUtil.PhotoBody("string");
        String strBody = ServicesUtil.PetInformationBody(id,bodyCategory,PetName,bodyTags,Status,bodyPhoto);
        return new RestAssuredConfiguration().getResponsePut(requestSpecification.body(strBody), EndPoint.PUT_A_PET);
    }

    public static Response deletePet(String idPet) {
        System.out.println("==============================================================");
        System.out.println("================= DELETE - Delete a pet ======================");
        System.out.println("==============================================================");

        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(baseApiUri, pathServices, 0);
        return new RestAssuredConfiguration().getResponseDelete(requestSpecification, EndPoint.DELETE_A_PET+idPet);
    }

    public static Response getFindAPetByStatus(String sStatus) {
        System.out.println("==============================================================");
        System.out.println("================ GET - Find a pet by status ==================");
        System.out.println("==============================================================");

        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(baseApiUri, pathServices, 0);
        return new RestAssuredConfiguration().getResponseGet(requestSpecification, EndPoint.POST_FIND_PET_STATUS+"?status="+sStatus);
    }
}
