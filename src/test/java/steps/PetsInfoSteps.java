package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import services.Test.ResponsePetServices;

import java.util.List;
import java.util.Map;

public class PetsInfoSteps extends TestBase {

    private int statusOk = HttpStatus.SC_OK;
    private int StatusNotFound = HttpStatus.SC_NOT_FOUND;

    @Then("^I verify add a new pet and the result on the search by id$")
    public void iVerifyAddANewPetAndTheResultOnTheSearch(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        List<List<String>> rowsClean = rows.subList(1, rows.size());

        for (List<String> row : rowsClean) {
            String aPetId = "";
            String aCategoryName = row.get(0);
            String aPetName = row.get(1);
            String aTag = row.get(2);
            String aStatus = row.get(3);

            Response responseNewPet = ResponsePetServices.postAddNewPet(aCategoryName, aPetName, aTag, aStatus);
            Assert.assertEquals(responseNewPet.getStatusCode(), statusOk, "Error en post new pet: Se esperaba status 200 al agregar un nuevo registro");
            aPetId = String.valueOf(responseNewPet.getBody().path("id"));

            Response responseFindPetById = ResponsePetServices.getFindAPetById(aPetId);
            Assert.assertEquals(responseFindPetById.getStatusCode(), statusOk, "Error en get find pet by id: Se esperaba status 200 al buscar el registro");
        }
    }

    @Then("^I verify update a pet and the result on the search by id$")
    public void iVerifyUpdateAPetAndTheResultOnTheSearch(DataTable table) {
        SoftAssert sa = new SoftAssert();
        List<List<String>> rows = table.asLists(String.class);
        List<List<String>> rowsClean = rows.subList(1, rows.size());

        for (List<String> row : rowsClean) {
            String aPetId = "";
            String aCategoryName = row.get(0);
            String aPetName = row.get(1);
            String aTag = row.get(2);
            String aStatus = row.get(3);
            String aCategoryNameUpdated = row.get(4);
            String aPetNameUpdated = row.get(5);
            String aTagUpdated = row.get(6);
            String aStatusUpdated = row.get(7);

            //se registra la mascota
            Response responseNewPet = ResponsePetServices.postAddNewPet(aCategoryName, aPetName, aTag, aStatus);
            Assert.assertEquals(responseNewPet.getStatusCode(), statusOk, "Error en post new pet: Se esperaba status 200 al agregar un nuevo registro");
            aPetId = String.valueOf(responseNewPet.getBody().path("id"));

            //se actualizan los datos
            Response responseUpdatePet = ResponsePetServices.putAddNewPet(aPetId, aCategoryNameUpdated, aPetNameUpdated, aTagUpdated, aStatusUpdated);
            Assert.assertEquals(responseUpdatePet.getStatusCode(), statusOk, "Error en put update pet: Se esperaba status 200 al actualizar un registro");

            //se obtiene la mascota mediante la busqueda por id
            Response responseFindPetById = ResponsePetServices.getFindAPetById(aPetId);
            Assert.assertEquals(responseFindPetById.getStatusCode(), statusOk, "Error en get find pet by id: Se esperaba status 200 al buscar el registro");

            Map<String, String> mapCategoryFound = responseFindPetById.getBody().path("category");

            List<Map<String, String>> ListMapTagFound = responseFindPetById.getBody().path("tags");
            Map<String, String> mapTagFound = ListMapTagFound.get(0);

            sa.assertEquals(aCategoryNameUpdated, mapCategoryFound.get("name"),"Error en get find pet by id: campo Category > Name");
            sa.assertEquals(aPetNameUpdated, responseFindPetById.getBody().path("name"),"Error en get find pet by id: campo Name");
            sa.assertEquals(aTagUpdated, mapTagFound.get("name"),"Error en get find pet by id: campo Tag > Name");
            sa.assertEquals(aStatusUpdated, responseFindPetById.getBody().path("status"), "Error en get find pet by id: campo Status");

            sa.assertAll();
        }
    }

    @Then("^I verify delete a pet and the result on the search by id$")
    public void iVerifyDeleteAPetAndTheResultOnTheSearch(DataTable table) {

        SoftAssert sa = new SoftAssert();
        List<List<String>> rows = table.asLists(String.class);
        List<List<String>> rowsClean = rows.subList(1, rows.size());

        for (List<String> row : rowsClean) {
            String aPetId = "";
            String aCategoryName = row.get(0);
            String aPetName = row.get(1);
            String aTag = row.get(2);
            String aStatus = row.get(3);

            //se registra la mascota
            Response responseNewPet = ResponsePetServices.postAddNewPet(aCategoryName, aPetName, aTag, aStatus);
            Assert.assertEquals(responseNewPet.getStatusCode(), statusOk, "Error en post new pet: Se esperaba status 200 al agregar un nuevo registro");
            aPetId = String.valueOf(responseNewPet.getBody().path("id"));

            //se elimina la mascota
            Response responseUpdatePet = ResponsePetServices.deletePet(aPetId);
            Assert.assertEquals(responseUpdatePet.getStatusCode(), statusOk, "Error en delete pet: Se esperaba status 200 al eliminar el registro");

            //se obtiene la mascota mediante la busqueda por id
            Response responseFindPetById = ResponsePetServices.getFindAPetById(aPetId);
            Assert.assertEquals(responseFindPetById.getStatusCode(), StatusNotFound, "Error en get find pet by id: Se esperaba status 404 al buscar el registro eliminado");

            sa.assertAll();
        }
    }

    @Then("^I verify add a new pet and the result on the search by status$")
    public void iVerifyAddANewPetAndTheResultOnTheSearchByStatus(DataTable table) {

        SoftAssert sa = new SoftAssert();
        List<List<String>> rows = table.asLists(String.class);
        List<List<String>> rowsClean = rows.subList(1, rows.size());

        for (List<String> row : rowsClean) {
            String aPetId;
            String aCategoryName = row.get(0);
            String aPetName = row.get(1);
            String aTag = row.get(2);
            String aStatus = row.get(3);

            //se registra la mascota
            Response responseNewPet = ResponsePetServices.postAddNewPet(aCategoryName, aPetName, aTag, aStatus);
            Assert.assertEquals(responseNewPet.getStatusCode(), statusOk, "Error en post new pet: Se esperaba status 200 al agregar un nuevo registro");
            aPetId = responseNewPet.getBody().path("id").toString();

            //se obtiene la mascota mediante la busqueda por status
            Response responseFindPetByStatus = ResponsePetServices.getFindAPetByStatus(aStatus);
            Assert.assertEquals(responseFindPetByStatus.getStatusCode(), statusOk, "Error en get find pet by id: Se esperaba status 200 al buscar el registro");

            List<Map<String, String>> ListMapPets = responseFindPetByStatus.jsonPath().get();

            for (Map<String, String> map : ListMapPets){
                if(String.valueOf(map.get("id")).equals(aPetId)){
//                    System.out.println(map.get("id"));
                    System.out.println(map.get("name"));
                    System.out.println(map.get("status"));
                    sa.assertEquals(map.get("name"),aPetName);
                    sa.assertEquals(map.get("status"),aStatus);
                    break;
                }

            }

            sa.assertAll();

//            Map<String, String> mapCategoryFound = responseFindPetById.getBody().path("category");
//
//            List<Map<String, String>> ListMapTagFound = responseFindPetById.getBody().path("tags");
//            Map<String, String> mapTagFound = ListMapTagFound.get(0);
//
//            sa.assertEquals(aCategoryNameUpdated, mapCategoryFound.get("name"),"Error en get find pet by id: campo Category > Name");
//            sa.assertEquals(aPetNameUpdated, responseFindPetById.getBody().path("name"),"Error en get find pet by id: campo Name");
//            sa.assertEquals(aTagUpdated, mapTagFound.get("name"),"Error en get find pet by id: campo Tag > Name");
//            sa.assertEquals(aStatusUpdated, responseFindPetById.getBody().path("status"), "Error en get find pet by id: campo Status");
        }
    }
}
