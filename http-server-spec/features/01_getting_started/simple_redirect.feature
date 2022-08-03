@simple-redirect @01-getting-started
Feature: Simple Redirect

  Scenario: Getting a resource that has been moved to a different location
    Given I make an GET request to "/redirect"
    Then my response should have status code 301
    And my response should have a location header with the "http://0.0.0.0:5000/simple_get" URI
    And my response should have an empty body
