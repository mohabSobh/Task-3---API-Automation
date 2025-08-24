package tests;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AIBB_Test {

    private static final String BASE_URL = "https://reqres.in/api";
    private static final String API_KEY = "reqres-free-v1";
    private static String createdUserId;
    private static String authToken;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    // 1. Create User
    @Test(priority = 1, description = "Create a new user and extract its ID")
    public void createUserAndExtractId() {
        Allure.step("Sending request to create user");
        String requestBody = "{\"name\": \"Ahmed Mohamed\", \"job\": \"Software Developer\"}";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("X-API-Key", API_KEY)
                .body(requestBody)
            .when()
                .post("/users")
            .then()
                .statusCode(201)
                .body("name", equalTo("Ahmed Mohamed"))
                .body("id", notNullValue())
                .extract().response();

        createdUserId = response.path("id").toString();
        Allure.addAttachment("Created User ID", createdUserId);

        Assert.assertNotNull(createdUserId, "User ID should not be null");
    }

    // 2. Login
    @Test(priority = 2, description = "Login and extract authentication token")
    public void loginAndExtractToken() {
        Allure.step("Sending login request");
        String loginBody = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("X-API-Key", API_KEY)
                .body(loginBody)
            .when()
                .post("/login")
            .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract().response();

        authToken = response.path("token");
        Allure.addAttachment("Auth Token", authToken);

        Assert.assertNotNull(authToken, "Auth token should not be null");
    }

    // 3. Update User
    @Test(priority = 3, description = "Update user information and verify changes")
    public void updateUserAndVerify() {
        Assert.assertNotNull(createdUserId, "User ID should be created first");
        Assert.assertNotNull(authToken, "Auth token should be obtained first");

        Allure.step("Updating user with ID: " + createdUserId);
        String requestBody = "{\"name\": \"Mohamed Ali Taha\", \"job\": \"Senior DevOps Engineer\"}";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authToken)
                .header("X-API-Key", API_KEY)
                .body(requestBody)
            .when()
                .put("/users/" + createdUserId)
            .then()
                .statusCode(200)
                .body("name", equalTo("Mohamed Ali Taha"))
                .body("job", equalTo("Senior DevOps Engineer"))
                .extract().response();

        String updatedName = response.path("name");
        String updatedJob = response.path("job");

        Allure.addAttachment("Updated Name", updatedName);
        Allure.addAttachment("Updated Job", updatedJob);

        Assert.assertEquals(updatedName, "Mohamed Ali Taha", "Name should be updated");
        Assert.assertEquals(updatedJob, "Senior DevOps Engineer", "Job should be updated");
    }

    // 4. Delete User
    @Test(priority = 4, description = "Delete user with ID and token")
    public void deleteUserWithIdAndToken() {
        Assert.assertNotNull(createdUserId, "User ID should be created first");

        Allure.step("Deleting user with ID: " + createdUserId);
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authToken)
                .header("X-API-Key", API_KEY)
            .when()
                .delete("/users/" + createdUserId)
            .then()
                .statusCode(204);

        Allure.addAttachment("Deleted User ID", createdUserId);
    }

    // 5. Verify Deletion
    @Test(priority = 5, description = "Verify user deletion returns 404")
    public void verifyUserDeletion() {
        Assert.assertNotNull(createdUserId, "User ID should be created first");

        Allure.step("Verifying deletion of user: " + createdUserId);
        given()
                .header("Content-Type", "application/json")
                .header("X-API-Key", API_KEY)
            .when()
                .get("/users/" + createdUserId)
            .then()
                .statusCode(404);

        Allure.addAttachment("Verification", "User with ID " + createdUserId + " is successfully deleted");
    }
}
