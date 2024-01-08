
Feature: Employee List
  Background:
    Given The user navigates to url and login with admin credentials

    Scenario: Creating and deleting a new employee in the employee list
      When The user navigates to employee list page
      And The user creates a new employee with credentials
      Then The user verifies the new employee info in employee list
      When The user deletes the new employee from employee list
      Then The user verifies the new employee deleted from employee list

