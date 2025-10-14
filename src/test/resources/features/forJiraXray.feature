@B5G2-95
Feature: demo how to upload json report to xray

  @xray @B5G2-125 @B5G2-130
  Scenario: Login as a client
    Given user is on Docuport login page
    When user enters username for client
    Then user enters password for client
    And user clicks login button
    Then user should be able to see home page for client