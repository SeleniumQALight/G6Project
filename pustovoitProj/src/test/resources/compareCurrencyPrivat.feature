Feature: Currency Private Bank

  @R222
  Scenario Outline: R222 Compare currency from UI and API
    Given User get rate '<currency>' from UI
    When User get rate '<baseCurrency>' , '<currency>' from API
    Then User compering currency from UI and API '<baseCurrency>' , '<currency>'


    Examples:
      | baseCurrency | currency |
      | UAH          | EUR      |
      | UAH          | USD      |
