@update-todo @04-todo-list
Feature: Update To-Do

  Scenario: Successfully updating a to-do item
    Given I make a valid PUT request to a to-do item
    Then my response should have status code 200
    And my response should return JSON
    And my response should return the details of the updated task

  Scenario: Trying to update a to-do item with an unsupported media type
    Given I make a PUT request with an unsupported media type to a to-do item
    Then my response should have status code 415
    And my response should have an empty body

  Scenario: Trying to update a to-do item with invalid values
    Given I make a PUT request with invalid values to the a to-do item
    Then my response should have status code 400
    And my response should have an empty body
