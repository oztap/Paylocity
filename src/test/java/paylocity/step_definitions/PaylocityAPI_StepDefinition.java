package paylocity.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PaylocityAPI_StepDefinition {
    public static String id;


    @Given("an Employer I add new employee using endpoint")

    public void an_employer_i_add_new_employee_using_endpoint() {


        baseURI = "https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/api/employees";
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("firstName", "Sergii");
        bodyMap.put("lastName", "Ostapchuk");
        bodyMap.put("dependants", 1);

        System.out.println("bodyMap = " + bodyMap);
        JsonPath jp =
                given()
                        .log().all()
                        .header("Authorization", "VGVzdFVzZXI1OTpHM3xVRmVOJmZyKDo=")
                        .auth().preemptive().basic("TestUser68", "[9/tslMpOCR/")
                        .header("Content-Type", "application/json")
                        .body(bodyMap).
                        when()
                        .post()
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().jsonPath();
        id = jp.getString("id");
        System.out.println(id);


    }

    @Then("I should see the employee in the table.")
    public void i_should_see_the_employee_in_the_table() {

        baseURI = "https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/api/employees";

        JsonPath jp1 = given()
                .log().all()
                .header("Authorization", "VGVzdFVzZXI1OTpHM3xVRmVOJmZyKDo=")
                .auth().preemptive().basic("TestUser68", "[9/tslMpOCR/")
                .header("Accept", ContentType.JSON).
                        when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .extract().jsonPath();

        List<String> listIDs = jp1.getList("id");
        for (String each : listIDs) {
            if (each.equals(id)) {
                Assert.assertEquals(each, id);

                System.out.println(each);
            }
        }
    }

    @Then("the benefit cost calculations are correct.")
    public void the_benefit_cost_calculations_are_correct() {

        baseURI = "https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/api/employees";


        JsonPath jp2 = given()
                //.log().all()
                .header("Authorization", "VGVzdFVzZXI1OTpHM3xVRmVOJmZyKDo=")
                .auth().preemptive().basic("TestUser68", "[9/tslMpOCR/")
                .header("Accept", ContentType.JSON)
                .pathParam("id", id).
                        when()
                .get("/{id}")
                .then()
                //.log().all()
                .statusCode(200)
                .extract().jsonPath();
        String firstName = jp2.getString("firstName");
        String lastName = jp2.getString("lastName");
        String salary = jp2.getString("salary");
        String gross = jp2.getString("gross");
        String benefitsCost = jp2.getString("benefitsCost");
        String net = jp2.getString("net");
        System.out.println(firstName);
        assertThat(firstName, equalTo("Sergii"));
        assertThat(lastName, equalTo("Ostapchuk"));
        assertThat(salary, equalTo("52000.0"));
        assertThat(gross, equalTo("2000.0"));
        assertThat(benefitsCost, equalTo("57.69231"));
        assertThat(net, equalTo("1942.3077"));
    }


    @Given("an Employer I can edit the Employee info using endpoint")
    public void an_employer_i_can_edit_the_employee_info_using_endpoint() {
        baseURI = "https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/api/employees";
        this. id=id;
        Map<String, Object> bodyMap1 = new LinkedHashMap<>();
        bodyMap1.put("id", id);
        bodyMap1.put("firstName", "Serg");
        bodyMap1.put("lastName", "Ostap");
        bodyMap1.put("dependants", 2);

        System.out.println("bodyMap = " + bodyMap1);


        given()
                .log().all()
                .header("Authorization", "VGVzdFVzZXI1OTpHM3xVRmVOJmZyKDo=")
                .auth().preemptive().basic("TestUser68", "[9/tslMpOCR/")
                .header("Accept", ContentType.JSON)
                .body(bodyMap1).
                when()
                .put()
                .then()
                .log().all()
                .statusCode(200);
    }

    @Then("the data should change in the table.")
    public void the_data_should_change_in_the_table() {

        baseURI = "https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/api/employees";
        JsonPath jp = given()
                .log().all()
                .header("Authorization", "VGVzdFVzZXI1OTpHM3xVRmVOJmZyKDo=")
                .auth().preemptive().basic("TestUser68", "[9/tslMpOCR/")
                .header("Accept", ContentType.JSON)
                .pathParam("id", id).
                        when()
                .get("/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract().jsonPath();
        String firstName = jp.getString("firstName");
        String lastName = jp.getString("lastName");
        int dependents = jp.getInt("dependants");

        System.out.println(firstName);
        assertThat(firstName, equalTo("Serg"));
        assertThat(lastName, equalTo("Ostap"));
        assertThat(dependents, equalTo(2));

    }

    @Given("an Employer I delete the employee using endpoint")
    public void an_employer_i_delete_the_employee_using_endpoint() {
        baseURI = "https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/api/employees";
        given()
                .log().all()
                .header("Authorization", "VGVzdFVzZXI1OTpHM3xVRmVOJmZyKDo=")
                .auth().preemptive().basic("TestUser68", "[9/tslMpOCR/")
                .header("Accept", ContentType.JSON)
                .pathParam("id", id).
                when()
                .delete("/{id}")
                .then()
                .log().all()
                .statusCode(200);


    }


    @Then("I will not find this employee in the table.")
    public void i_will_not_find_this_employee_in_the_table() {

        baseURI = "https://wmxrwq14uc.execute-api.us-east-1.amazonaws.com/Prod/api/employees";

        JsonPath jp = given()
                .log().all()
                .header("Authorization", "VGVzdFVzZXI1OTpHM3xVRmVOJmZyKDo=")
                .auth().preemptive().basic("TestUser68", "[9/tslMpOCR/")
                .header("Accept", ContentType.JSON).
                        when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .extract().jsonPath();

        List<String> listIDs = jp.getList("id");
        for (String each : listIDs) {
            if(each.equals(id)) {
                Assert.assertEquals(each, id);
            }
            System.out.println(each);

        }
    }
}
