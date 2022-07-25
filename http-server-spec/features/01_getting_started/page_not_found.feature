@page-not-found @01-getting-started
Feature: Page Not Found

  Scenario: Getting an unconfigured resource returns a 404
    Given I make a GET request to a page that does not exist
    Then my response should have status code 404
