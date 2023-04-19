
Feature: Currency Exchange Rates

  @R111
  Scenario Outline: Compare API and UI exchange rates for '<currency>'/'<baseCurrency>'
    Given I get the '<currency>'/'<baseCurrency>' exchange rates from the API
    When I go to the PrivatBank website
    And I get the '<currency>'/'<baseCurrency>' exchange rate from the UI
    Then the API exchange rate should match the UI exchange rate for '<currency>'/'<baseCurrency>'

    Examples:
      | currency | baseCurrency |
      | USD      | UAH          |
      | EUR      | UAH          |
