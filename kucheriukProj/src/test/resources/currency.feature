
@LoginTest @FullRegression
  Feature: Get currency exchange rate

    @R004
   Scenario Outline: R004 Check exchange rate for PB
      Given Get '<currency>' currency exchange rate
      When User opens PrivateBankHome page
      And Get '<currency>' currency exchange rate from UI
      Then Compare '<currency>' exchange rate from UI and API

      Examples:
      | currency |
      | USD      |
      | EUR      |