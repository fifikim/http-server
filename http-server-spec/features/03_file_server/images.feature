@images @03-file-server
Feature: Images

  Scenario: Getting a JPEG image of a kitteh
    Given I make a GET request to '/kitteh.jpg'
    Then my response should have status code 200
    And my response should return a JPEG image
    And my response should have a non-empty body

  Scenario: Getting a PNG image of a doggo
    Given I make a GET request to '/doggo.png'
    Then my response should have status code 200
    And my response should return a PNG image
    And my response should have a non-empty body

  Scenario: Getting a GIF image of kissing bunnies
    Given I make a GET request to '/kisses.gif'
    Then my response should have status code 200
    And my response should return a GIF
    And my response should have a non-empty body
