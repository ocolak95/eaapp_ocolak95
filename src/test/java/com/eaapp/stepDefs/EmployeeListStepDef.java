package com.eaapp.stepDefs;

import com.eaapp.pages.EmployeePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeListStepDef {
    EmployeePage employeePage =new EmployeePage();

    @Given("The user navigates to url and login with admin credentials")
    public void the_user_navigates_to_url_and_login_with_admin_credentials() {
        employeePage.login();
    }
    @When("The user navigates to employee list page")
    public void the_user_navigates_to_employee_list_page() {
        employeePage.employeeList_Btn.click();
    }
    @When("The user creates a new employee with credentials")
    public void the_user_creates_a_new_employee_with_credentials() {
        employeePage.enterEmployeeCredentials("aaa","1000","10","Junior","aaa@gmail.com");
    }
    @Then("The user verifies the new employee info in employee list")
    public void the_user_verifies_the_new_employee_info_in_employee_list() {
        employeePage.verifyEmployeeCreated();
    }
    @When("The user deletes the new employee from employee list")
    public void the_user_deletes_the_new_employee_from_employee_list() {
        employeePage.deleteEmployeeMtd();
    }
    @Then("The user verifies the new employee deleted from employee list")
    public void the_user_verifies_the_new_employee_deleted_from_employee_list() {
        employeePage.verifyEmployeeDeleted();
    }
}
