Feature: Privat Bank Currency Compare

  @R007
  Scenario Outline: R007 Comparing currency from API and UI '<currency>'
    Given Get '<currency>' rate from API
    When User opens Private bank 'HomePage' page
    And Get '<currency>' rate from UI
    Then User compare rate from UI and API

    Examples:
   | currency |
   |   EUR    |
   |   USD    |
