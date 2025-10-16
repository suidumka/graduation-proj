Feature: Docuport Document Upload And Remove


  @docuportUpload
  Scenario: User upload and remove document successfully
    Given the user is on the Docuport login page
    When user enters valid "b1g1_advisor@gmail.com" to "username" on "Login" page
    And user enters valid "Group1" to "password" on "Login" page
    And user clicks the "login" button on "Login" page
    And user should be redirected to the "Home" page and see My uploads folder
    And user clicks "My uploads" on "Home" page
    And user clicks "Upload documents" on "My uploads" page
    And user upload an document
    And user should be able see document first on a list
    Then user choose document
    And user clicks "Delete" on "My uploads" page
    And user clicks "Remove" on "My uploads" page
    And document should be successfully removed from list