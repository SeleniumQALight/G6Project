@LoginTest @FullRegression
Feature: Currency exchange Privat

  @R020
  Scenario Outline: R020 compare '<currency>'
    Given User get rate '<currency>' from UI
    When User get rate '<baseCurrency>' , '<currency>' from API
    Then User compering currency from UI and API '<baseCurrency>' , '<currency>'


    Examples:
      | baseCurrency | currency |
      | UAH          | EUR      |
      | UAH          | USD      |