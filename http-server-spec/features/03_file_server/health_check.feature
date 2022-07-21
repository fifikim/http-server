@health-check @03-file-server
Feature: Health Check

  Scenario: Health check request
    Given I make a GET request to '/health-check.html'
    Then my response should have status code 200
    And my response should return HTML
    And my response should have a non-empty body
    And the body should say that the status is passing
