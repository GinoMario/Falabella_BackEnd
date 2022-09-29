package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import services.Common.ServicesUtil;
import services.Test.ResponsePetServices;
import services.Test.ResponseUserServices;

import java.util.List;

public class UserInfoSteps {

    private int statusOk = HttpStatus.SC_OK;
    private int StatusNotFound = HttpStatus.SC_NOT_FOUND;

    @Then("^I verify create a user and the result by get user$")
    public void iVerifyCreateAUserAndTheResultByGetUser(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        List<List<String>> rowsClean = rows.subList(1, rows.size());

        for (List<String> row : rowsClean) {
            String aUserId = "";
            String aUserName = ServicesUtil.getNumberAccountRandom(row.get(0));
            String aFirstName = row.get(1);
            String aLastName = row.get(2);
            String aMail = row.get(3);
            String aPassword = row.get(3);
            String aPhone = row.get(3);

            //SE REGISTRA EL USUARIO
            Response responseNewUser = ResponseUserServices.postCreateUser(aUserName, aFirstName, aLastName, aMail, aPassword, aPhone);
            Assert.assertEquals(responseNewUser.getStatusCode(), statusOk, "Error en post new user: Se esperaba status 200 al agregar un nuevo registro");
            aUserId = String.valueOf(responseNewUser.getBody().path("message"));

            //SE CONSULTA EL USERNAME
            Response responseGetUser = ResponseUserServices.getUser(aUserName);
            Assert.assertEquals(responseGetUser.getStatusCode(), statusOk, "Error en get user: Se esperaba status 200 al buscar el registro");
            Assert.assertEquals(aUserId,String.valueOf(responseGetUser.getBody().path("id")));
        }
    }

    @Then("^I verify delete a user and the result by get user$")
    public void iVerifyDeleteAUserAndTheResultByGetUser(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        List<List<String>> rowsClean = rows.subList(1, rows.size());

        for (List<String> row : rowsClean) {
            String aUserId = "";
            String aUserName = ServicesUtil.getNumberAccountRandom(row.get(0));
            String aFirstName = row.get(1);
            String aLastName = row.get(2);
            String aMail = row.get(3);
            String aPassword = row.get(3);
            String aPhone = row.get(3);

            //SE REGISTRA EL USUARIO
            Response responseNewUser = ResponseUserServices.postCreateUser(aUserName, aFirstName, aLastName, aMail, aPassword, aPhone);
            Assert.assertEquals(responseNewUser.getStatusCode(), statusOk, "Error en post new user: Se esperaba status 200 al agregar un nuevo registro");
            aUserId = String.valueOf(responseNewUser.getBody().path("message"));

            //SE ELIMINA EL USUARIO
            Response responseDeletetUser = ResponseUserServices.deleteUser(aUserName);
            Assert.assertEquals(responseDeletetUser.getStatusCode(), statusOk, "Error en get user: Se esperaba status 200 al buscar el registro");

            //SE CONSULTA EL USERNAME
            Response responseGetUser = ResponseUserServices.getUser(aUserName);
            Assert.assertEquals(responseGetUser.getStatusCode(), StatusNotFound, "Error en get user: Se esperaba status 200 al buscar el registro");
        }
    }

    @Then("^I verify logs user by credentials$")
    public void iVerifyLogsUserByCredentials(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        List<List<String>> rowsClean = rows.subList(1, rows.size());

        for (List<String> row : rowsClean) {
            String aUserId = "";
            String aUserName = ServicesUtil.getNumberAccountRandom(row.get(0));
            String aFirstName = row.get(1);
            String aLastName = row.get(2);
            String aMail = row.get(3);
            String aPassword = row.get(3);
            String aPhone = row.get(3);

            //SE REGISTRA EL USUARIO
            Response responseNewUser = ResponseUserServices.postCreateUser(aUserName, aFirstName, aLastName, aMail, aPassword, aPhone);
            Assert.assertEquals(responseNewUser.getStatusCode(), statusOk, "Error en post new user: Se esperaba status 200 al agregar un nuevo registro");
            aUserId = String.valueOf(responseNewUser.getBody().path("message"));

            //SE CONSULTA EL USERNAME
            Response responseGetUser = ResponseUserServices.getUser(aUserName);
            Assert.assertEquals(responseGetUser.getStatusCode(), statusOk, "Error en get user: Se esperaba status 200 al buscar el registro");
            Assert.assertEquals(aUserId,String.valueOf(responseGetUser.getBody().path("id")));

            //SE REALIZA EL LOGIN
            Response responseLoginUser = ResponseUserServices.getLoginUser(aUserName,aPassword);
            Assert.assertEquals(responseLoginUser.getStatusCode(), statusOk, "Error en get user: Se esperaba status 200 al buscar el registro");

        }
    }

    @Then("^I verify logs out user by credentials$")
    public void iVerifyLogsOutUserByCredentials() {
        Response responseGetUser = ResponseUserServices.logOutUser();
        Assert.assertEquals(responseGetUser.getStatusCode(), statusOk, "Error en get user: Se esperaba status 200 al buscar el registro");
    }
}
