Feature: End to end tests

  Scenario: Checking first planet name
    When I send request to get information about the 1 planet
    Then I check "name" of 1 planet equals to "Tatooine"

    Scenario: Checking planet diameter
      When I get planets information
      Then I check at list one of them has diameter more than 1000 kilometers

      Scenario: Checking people
        When I get people information
        Then I check at list one of them has name "C-3PO"
        Then I check 1 person with name equals to "Luke Skywalker" has "blue" eyes