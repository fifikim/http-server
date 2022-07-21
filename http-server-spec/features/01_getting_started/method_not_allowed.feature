@method-not-allowed @01-getting-started
Feature: Method Not Allowed

  Scenario: Finding GET for an endpoint with only HEAD
    Given I make an GET request to "/head_request"
    Then my response should have status code 405
    And my response should have allowed headers of HEAD, OPTIONS
    And my response should have an empty body
