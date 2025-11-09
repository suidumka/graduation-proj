@smoke
Feature: Docuport: login and verify left navigation per role

  Background:
    Given the Docuport site is open

  @dd @ui
  #mapListProduct
  Scenario Outline: Login and verify left navigation for <role>
    When the user logs in as "<role>"
    And the user is on the Docuport home page
    Then the left navigation for "<role>" should be:
      | advisor    | Home | Received docs | My uploads | Clients     | Invitations | Users       | Leads       | Bookkeeping     | 1099 Form       | Reconciliations |
      | supervisor | Home | Received docs | My uploads | Clients     | Users       | Leads       | Bookkeeping | 1099 Form       | Reconciliations |                 |
      | employee   | Home | Received docs | My uploads | Clients     | Users       | Bookkeeping | 1099 Form   | Reconciliations |                 |                 |
     # | client     | Home | Received docs | My uploads | Invitations |             |             |             |                 |                 |                 |

    Examples:
      | role       |
      | client     |
      | employee   |
      | advisor    |
      | supervisor |