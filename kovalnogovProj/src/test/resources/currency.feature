Feature: PrivatUIAPI

  @R031
  Scenario Outline: R031 Test comparing currencyRates from UI and API
    Given Open exchange-rates for currency '<Currency>' via UI and get sale and buy vales
    When  Get exchange rates via API for  currency '<Currency>'
    Then Compare currency '<Currency>'

    Examples:
      | Currency |
      | USD      |


