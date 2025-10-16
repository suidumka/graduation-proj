Feature: Docuport Sample Scenario

  @sampleDocuport
  Scenario: Practice click buttons on different pages as a client
    Given user is on Docuport login page
    When user inserts "b1g1_client@gmail.com" to "username" field on "Login" page
    And user inserts "Group1" to "password" field on "Login" page
    And user clicks "Login" button on "Login" page
    And user clicks "Continue" button on "Choose account" page
    Then user should be able to see home page for "<role>"
    And user clicks "Invitations" button on "Left Navigate" page
    And user clicks "My uploads" button on "Left Navigate" page
    And user clicks "Home" button on "Left Navigate" page
    And user clicks "Received Doc" button on "Left Navigate" page
    And user clicks "Search" button on "Received Doc" page
    And user inserts "Tax document" to "Document name" field on "Received Doc" page
    And user clicks "My uploads" button on "Left Navigate" page
    And user clicks "Upload Documents" button on "My uploads" page
    # we do NOT need the step below, becuz in HTML it handles upload file with [@type='file']
    # so, we just need to provide a pathname of file we need to upload and it's fine
    And user clicks "Upload file" button on "My uploads" page
    And user uploads a document

    Examples:
      | role   |
      | client |
