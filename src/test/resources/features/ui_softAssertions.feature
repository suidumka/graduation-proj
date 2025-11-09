@smoke
Feature: Practice soft assertions
  @ui @softAssertions
  Scenario: Soft assertions practise
    Given user is on Docuport login page
    Then user validates " Login " text as displayed
    Then user validates "Docuport" text as displayed
    When user inserts "b1g1_advisor@gmail.com" to "username" field on "Login" page
    And user inserts "Group1" to "password" field on "Login" page
    And user clicks "Login" button on "Login" page
    # And user validates "Choose account" text as displayed
      # And user clicks "Continue" button on "Choose account" page
    And user clicks "Home" button on "Left navigate" page
    And user clicks "Invitations" button on "Left navigate" page
    And user validates all assertions



