@simple-head @01-getting-started
Feature: Simple HEAD

  Scenario: Executing a HEAD request to /simple_get
    Given I make a HEAD request to "/simple_get"
    Then my response should have status code 200
    And my response should have an empty body

  Scenario: HEAD does not include body
    Given I make a HEAD request to "/head_request"
    Then my response should have status code 200
    And my response should have an empty body
