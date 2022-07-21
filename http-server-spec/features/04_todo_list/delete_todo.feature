@delete-todo @04-todo-list
Feature: Delete To-Do

  Scenario: Successfully deleting a to-do item
    Given I make a valid POST request to create a to-do task
    And I make a DELETE request to delete the to-do task
    Then my response should have status code 204
    And my response should have an empty body

  Scenario: Attempting to delete a to-do item that does not exist
    Given I make a valid POST request to create a to-do task
    And I make a DELETE request to a to-do item that does not exist
    Then my response should have status code 204
    And my response should have an empty body
