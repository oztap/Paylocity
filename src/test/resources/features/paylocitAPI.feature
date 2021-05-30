@paylocityAPI
Feature:As an Employer, I want to input my employee data so that I can get a preview of benefit costs using endpoints
  @paylocityAPI1
  Scenario: add Employee
    Given an Employer I add new employee using endpoint
    Then I should see the employee in the table.
    And the benefit cost calculations are correct.
  @paylocityAPI2
  Scenario: Edit Employee
    Given an Employer I can edit the Employee info using endpoint
    Then the data should change in the table.
  @paylocityAPI3
  Scenario: Delete Employee
    Given an Employer I delete the employee using endpoint
    Then I will not find this employee in the table.