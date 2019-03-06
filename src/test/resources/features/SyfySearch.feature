Feature: Syfy Search
  Background:
    Given I opened Syfy site

  Scenario Outline: Searching articles on Syfy
    When I search <request>
    And I open search result
    Then Article body contains <request>

    Examples:
    |request|
    |James|
    |Batman|
    |Star Wars|