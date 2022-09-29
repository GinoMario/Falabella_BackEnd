package services.Common;

import org.json.JSONObject;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ServicesUtil {
    public static String PetInformationBody(String id, Map<String, String> categoryName, String Petname,List<Map<String, String>> tags, String status, List<String> sPhoto) {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("id", id);
        jsonParams.put("category", categoryName);
        jsonParams.put("name", Petname);
        jsonParams.put("photoUrls", sPhoto);
        jsonParams.put("tags", tags);
        jsonParams.put("status", status);
        return jsonParams.toString();
    }

    public static Map<String, String> categoryBody(String Petname) {
        Map<String, String> mapCategory = new HashMap<String, String>();
        mapCategory.put("id", "0");
        mapCategory.put("name", Petname);
        return mapCategory;
    }

    public static List<Map<String, String>> tagsBody(String name) {
        List<Map<String, String>> listMapTags = new ArrayList<>();
        Map<String, String> mapTag = new HashMap<String, String>();
        mapTag.put("id", "0");
        mapTag.put("name", name);
        listMapTags.add(0,mapTag);
        return listMapTags;
    }

    public static List<String> PhotoBody(String sPhoto) {
        List<String> listPhoto = new ArrayList<>();
        listPhoto.add(0,sPhoto);
        return listPhoto;
    }

    public static String UserInformationBody(String idUser, String aUserName, String aFirstName, String aLastName, String aMail, String aPassword, String aPhone) {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("id", idUser);
        jsonParams.put("username", aUserName);
        jsonParams.put("firstName", aFirstName);
        jsonParams.put("lastName", aLastName);
        jsonParams.put("email", aMail);
        jsonParams.put("password", aPassword);
        jsonParams.put("phone", aPhone);
        jsonParams.put("userStatus", 0);
        return jsonParams.toString();
    }

    public static String getNumberAccountRandom(String number) {

        String newNro = "";
        Random random = new Random();
        for (int i = 0; i < number.length(); i++) {
            char car = number.charAt(i);
            if (car == '#') {
                int nro = ThreadLocalRandom.current().nextInt(0, 9 + 1);
                newNro = newNro + nro;
            } else {
                newNro = newNro + car;
            }
        }
//        System.out.println("New "+newNro+" Old "+number);
        return newNro;
    }
}
