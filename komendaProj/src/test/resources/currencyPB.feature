@PostTest @FullRegression
Feature: CurrencyPB Feature

  @R013
  Scenario Outline: R013 Compare currencyPB '<currency>'/'<baseCurrency>'
    Given Get '<currency>'/'<baseCurrency>' exchange from API
    When Open the page of the PB
    And Get the '<currency>'/'<baseCurrency>' exchange from the UI
    Then Compare exchange rates between UI and API '<currency>'/'<baseCurrency>'

    Examples:
      | currency | baseCurrency |
      | EUR      | UAH          |
      | USD      | UAH          |
