@paylocity
Feature: As an Employer, I want to input my employee data so that I can get a preview of benefit costs.  @emre
  @paylocity1
  Scenario: Add employee
    Given an employer
    And I am on the benefits dashboard page
    When I select Add Employee
    Then I should be able to enter employee details
    And The employee should save
    And I should see the employee in the table
    And the benefit cost calculations are correct
  @paylocity2
  Scenario: Edit employee
    Given an employer
    And I am on the benefits dashboard page
    When I select the Action Edit
    Then I can edit employee details
    And the data should change in the table
  @paylocity3
  Scenario: Delete Employee
    Given an employer
    And I am on the benefits dashboard page
    When I click the Action X
    Then the employee should be deleted