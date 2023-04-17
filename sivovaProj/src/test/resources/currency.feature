 Feature: Check exchange rate for PB from API and UI

   @Privat_001
    Scenario Outline: Check exchange rate for PB from API and UI - for '<currency>'/UAH
      Given Get '<currency>' currency exchange rate
      When User goes to 'PrivatBank Home page'
      And Get '<currency>' currency exchange rate from UI
      Then compare '<currency>' exchange rate from UI and API


      Examples:
        | currency |
        |  USD     |
        |  EUR     |
