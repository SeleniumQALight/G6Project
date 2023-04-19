Feature: User registration

  @R019
  Scenario Outline: R019 Comparing currency
    Given User opens PrivatHome page
    When User get buy and sell rate of '<currency>' from UI
    And User get '<baseCurrency>' and '<currency>' from API
    Then User compare currency from UI and API '<baseCurrency>' and '<currency>'

    Examples:
      | baseCurrency | currency |
      | UAH          | EUR      |
      | UAH          | USD      |
#      | UAH          | PLN      |


