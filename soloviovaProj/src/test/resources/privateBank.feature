@PBTest @FullRegression
Feature: PB currency exchange Feature

  @R012
  Scenario Outline: R012 compare '<currency>'
    Given User obtains 'buy' and 'sale' rates for '<currency>' via API
    When User opens 'Private Bank' website
    And User obtains 'buy' and 'sale' rates for '<currency>' via Web
    Then User compares obtained 'buy' and 'sale' rate of 'currencies' from API and Web



    Examples:
    | currency |
    |     EUR   |
    |     USD   |

