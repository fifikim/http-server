@structured-data @02-structured-data
Feature: Structured Data

  Scenario: Returning a text response
    Given I make a GET request to "/text_response"
    Then my response should have status code 200
    And my response should return text
    And my response should have a text body

  Scenario: Returning an HTML response
    Given I make a GET request to "/html_response"
    Then my response should have status code 200
    And my response should return HTML
    And my response should have an HTML body

  Scenario: Returning a JSON response
    Given I make a GET request to "/json_response"
    Then my response should have status code 200
    And my response should return JSON
    And my response should have a JSON body

  Scenario: Returning an XML response
    Given I make a GET request to "/xml_response"
    Then my response should have status code 200
    And my response should return XML
    And my response should have an XML body
