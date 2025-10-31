@smoke
Feature: Advisor API Client Management

  Background:
    Given the user has a valid access token for role "advisor"

  @db
  Scenario: Verify advisor can retrieve client information by search term
    Given the advisor adds query parameter "searchTerm" with value "Scottie Heaney"
    When the advisor sends a GET request to "api/v1/document/clients/all"
    Then the status code should be 200
    And the content type should be "application/json; charset=utf-8"
    And the first client object should match:
      | id         | 1406           |
      | name       | Scottie Heaney |
      | clientType | 2              |
      | isActive   | true           |
      | advisor    | null           |

