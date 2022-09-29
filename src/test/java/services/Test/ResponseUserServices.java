package services.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.framework.config.Settings;
import services.Common.EndPoint;
import services.Common.RestAssuredConfiguration;
import services.Common.ServicesUtil;

import java.util.List;
import java.util.Map;

public class ResponseUserServices {
    public static String baseApiUri = Settings.UrlApiBase;
    public static String pathServices = Settings.UrlApiPath;

    public static Response postCreateUser(String aUserName, String aFirstName, String aLastName, String aMail, String aPassword, String aPhone) {
        System.out.println("==============================================================");
        System.out.println("==================== POST - Add new user =====================");
        System.out.println("==============================================================");

        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(baseApiUri, pathServices, 0);
        String strBody = ServicesUtil.UserInformationBody("0",aUserName, aFirstName, aLastName, aMail, aPassword, aPhone);
        return new RestAssuredConfiguration().getResponsePost(requestSpecification.body(strBody), EndPoint.POST_NEW_USER);
    }

    public static Response getUser(String aUserName) {
        System.out.println("==============================================================");
        System.out.println("==================== GET - Get the user ======================");
        System.out.println("==============================================================");

        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(baseApiUri, pathServices, 0);
        return new RestAssuredConfiguration().getResponseGet(requestSpecification, EndPoint.GET_USER+aUserName);
    }

    public static Response deleteUser(String aUserName) {
        System.out.println("==============================================================");
        System.out.println("================= DELETE - Delete the user ===================");
        System.out.println("==============================================================");

        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(baseApiUri, pathServices, 0);
        return new RestAssuredConfiguration().getResponseDelete(requestSpecification, EndPoint.DELETE_USER+aUserName);
    }

    public static Response getLoginUser(String aUserName, String aPassword) {
        System.out.println("==============================================================");
        System.out.println("===================== GET - Login user =======================");
        System.out.println("==============================================================");

        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(baseApiUri, pathServices, 0);
        requestSpecification.queryParam("username",aUserName);
        requestSpecification.queryParam("password",aPassword);
        return new RestAssuredConfiguration().getResponseGet(requestSpecification, EndPoint.LOGIN_USER);
    }

    public static Response logOutUser() {
        System.out.println("==============================================================");
        System.out.println("===================== GET - LogOut user ======================");
        System.out.println("==============================================================");

        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification(baseApiUri, pathServices, 0);
        return new RestAssuredConfiguration().getResponseGet(requestSpecification, EndPoint.LOGOUT_USER);
    }
}
