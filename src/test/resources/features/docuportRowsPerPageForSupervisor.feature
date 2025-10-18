Feature: Rows Per Page Functionality

  Background:
    Given user is on Docuport login page
    When user enters username "b1g1_supervisor@gmail.com"
    And user enters password "Group1"
    And user clicks login button
    Then user should be able to see home page for "supervisor"

  @rowsPerPage
  Scenario: Validate and change Rows Per Page on Leads and Users pages
    When user clicks "Leads" button on "Left navigate" page
    Then user should see "Rows per page" default value as 10
    When user changes "Rows per page" value to 5
    Then user should see "Rows per page" value updated to 5

    When user clicks "Users" button on "Left navigate" page
    Then user should see "Rows per page" default value as 10
    When user changes "Rows per page" value to 5
    Then user should see "Rows per page" value updated to 5