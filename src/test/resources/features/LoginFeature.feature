Feature: Docuport Login Logout Feature

  Background:
    Given user is on Docuport login page

  @ddd
  Scenario: Login as a client
    #Given user is on Docuport login page
    When user enters username for client
    And user enters password for client
    Then user should be able to see home page for client

  @dd
  Scenario: Login as a employee
    #Given user is on Docuport login page
    When user enters username for employee
    And user enters password for employee
    Then user should be able to see home page for employee

  @dd
  Scenario: Login as a advisor
    #Given user is on Docuport login page
    When user enters username for advisor
    And user enters password for advisor
    Then user should be able to see home page for advisor

  @dd
  Scenario: Login as a supervisor
   # Given user is on Docuport login page
    When user enters username for supervisor
    And user enters password for supervisor
    Then user should be able to see home page for supervisor

  @dataTableLogin @dd
  Scenario: Login as a client map practice
    When user enters credentials
      | username | b1g1_client@gmail.com |
      | password | Group1                |
    Then user should be able to see home page for client