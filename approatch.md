Defined the user management workflow: Create > Login > Update > Delete > Verify Deletion.

Implemented the tests in Java using Rest Assured for API automation.

Used TestNG to structure and control the execution order of the test cases.

Create User: Sent a POST request to /users, extracted the id from the response, and saved it for subsequent requests.

Login: Sent a POST request to /login, extracted the authentication token, and used it for authorized requests.

Update User: Sent a PUT request to /users/{id} using the extracted id and token to update user details. Verified the updated name and job fields.

Delete User: Sent a DELETE request to /users/{id} with the token to remove the user.

Verify Deletion: Sent a GET request to /users/{id} to ensure the user was deleted (expected 404).

Added assertions on status codes and response body content to validate correctness.

Integrated Allure Reports to document detailed test execution steps and results.

Tools & Technologies

Java – Main language.

Eclipse – IDE.

Maven – Dependency management and build automation.

Rest Assured – Framework for REST API testing.

TestNG – Test execution and management.

Allure Reports – Reporting tool for detailed test results.