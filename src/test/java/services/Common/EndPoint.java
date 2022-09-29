package services.Common;

public interface EndPoint {
    //PETS INFORMATION
    String POST_NEW_PET = "pet";
    String POST_FIND_PET_STATUS = "pet/findByStatus";
    String GET_FIND_BY_ID = "pet/";
    String PUT_A_PET = "pet";
    String DELETE_A_PET = "pet/";

    //USER INFORMATION
    String POST_NEW_USER = "user";
    String GET_USER = "user/";
    String DELETE_USER = "user/";
    String LOGIN_USER = "user/login";
    String LOGOUT_USER = "user/logout";
}
