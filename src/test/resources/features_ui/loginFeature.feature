@smoke
Feature: Docuport Login Logout Feature

  Background:
    Given user is on Docuport login page

  @docuport @ui
  Scenario Outline: Login as <role>
    When user enters username "<username>"
    And user enters password "<password>"
    And user clicks login button
    Then user should be able to see home page for "<role>"

    Examples:
      | role       | username                  | password |
      | client     | b1g1_client@gmail.com     | Group1   |
      | employee   | b1g1_employee@gmail.com   | Group1   |
      | advisor    | b1g1_advisor@gmail.com    | Group1   |
      | supervisor | b1g1_supervisor@gmail.com | Group1   |

