@simple-options @01-getting-started
Feature: Simple OPTIONS

  Scenario: Finding OPTIONS for an endpoint with only GET
    Given I make an OPTIONS request to "/method_options"
    Then my response should have status code 200
    And my response should have allowed headers of GET, HEAD, and OPTIONS
    And my response should have an empty body

  Scenario: Finding OPTIONS for an endpoint with multiple verbs
    Given I make an OPTIONS request to "/method_options2"
    Then my response should have status code 200
    And my response should have allowed headers of GET, HEAD, OPTIONS, PUT, and POST
    And my response should have an empty body
