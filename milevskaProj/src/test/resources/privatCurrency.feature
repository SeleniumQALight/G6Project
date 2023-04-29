Feature: Privat Currency Exchange

  @PR001
  Scenario Outline: Check UI and API exchange rates for '<currency>'/'<baseCurrency>'
    Given Get '<currency>'/'<baseCurrency>' exchange rates from Privat API
    When User open Privat 'Main Page'
    And Get '<currency>'/'<baseCurrency>' exchange rates from Privat 'Main Page'
    Then Check matching exchange rates from Api and Main Page

    Examples:
    Examples:
      | currency   | baseCurrency |
      | USD        | UAH          |
      | EUR        | UAH          |