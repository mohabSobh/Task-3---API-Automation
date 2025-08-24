# Overview
This is an **API automation testing project** designed to validate key functionalities of the `reqres.in` API. The test cases cover complete user workflow including creation, authentication, update, and deletion of users with comprehensive reporting and validation.

# Technology Stack
- **Programming Language**: Java (JDK 8 or higher)
- **Testing Framework**: TestNG
- **API Testing Framework**: REST Assured
- **Build Tool**: Maven (3.6.0 or higher)
- **Reporting**: Allure Reports

An internet connection is required to execute the API calls.

# Installation & Execution

### 1. Clone the repository
```bash
git clone <repository-url>
cd RESTAssuredAutomation
```

### 2. Run tests with Maven
```bash
mvn clean test
```

### 3. Generate Allure report
```bash
mvn allure:serve
```

### 4. Run Tests with Maven and Generate Allure Report
```bash
mvn clean test && allure serve target/allure-results
```

# Test Implementation Details

**Test Class**: `AIBB_Test.java`

### Key Features
- Sequential test execution using TestNG priorities
- Dynamic data extraction between test methods
- Comprehensive response validation
- Allure integration for detailed reporting
- Authentication token management

### Test Methods
- `createUserAndExtractId()` → Creates a user and captures ID
- `loginAndExtractToken()` → Obtains authentication token
- `updateUserAndVerify()` → Updates user information
- `deleteUserWithIdAndToken()` → Deletes user account
- `verifyUserDeletion()` → Verifies successful deletion

# Reporting

**Allure Report Includes:**
1. Test execution timeline
2. Step-by-step test documentation
3. Request/response logging
4. Attachment of extracted data
5. Environment configuration details
6. Comprehensive test results dashboard

# Key Achievements

### Technical Implementation
- Implemented complete API workflow testing
- Successful data extraction and chaining between requests
- Comprehensive response validation using Hamcrest matchers
- Robust error handling and assertions
- Reporting with Allure integration

### Testing Best Practices
- Maintainable and readable test code
- Proper test isolation with setup methods
- Sequential test execution management
- Detailed test documentation
- Comprehensive result reporting

# Author
**Mohab Hossam Mostafa** – Software Testing Engineer
