@create-todo @04-todo-list
Feature: Create To-Do

  Scenario: Successfully creating a to-do task
    Given I make a valid POST request to create a to-do task
    Then my response should have status code 201
    And my response should return JSON
    And my response should have a body with the details of the task

  Scenario: Trying to create a to-do task with an unsupported media type
    Given I make a POST request with an unsupported media type
    Then my response should have status code 415
    And my response should have an empty body

  Scenario: Trying to create a to-do item with invalid values
    Given I make a POST request with invalid values
    Then my response should have status code 400
    And my response should have an empty body
