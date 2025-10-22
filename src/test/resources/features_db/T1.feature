@T1
Feature: As a data consumer, I want the department information stored in postgress DB correctly in department table.

  Background:
    Given Establish the database connection


  @uiDB @US1245
  Scenario: verify departments name in database
    When Execute query to get all columns for "departments"
    Then verify the below columns are listed in result for
      | tax                  |
      | bookkeeping          |
      | payroll              |
      | consultancy-services |

  @uiDB
  Scenario: verify users has unique IDs
    When Execute query to get all IDs from users
    Then verify all users has unique ID

  @uiDB
  Scenario: verify users table columns
    When Execute query to get all columns
    Then verify the below columns are listed in result

      | id                           |
      | first_name                   |
      | last_name                    |
      | email_address                |
      | phone_number                 |
      | role_name                    |
      | supervisor_user_id           |
      | advisor_user_id              |
      | department_id                |
      | department_name              |
      | username                     |
      | inviter_user_id              |
      | is_lead_onboarding_completed |