 Feature: Get currency exchange rate by ID

   @R001
    Scenario Outline: Get currency exchange rate for '<currency>'/UAH
      Given Get '<currency>' currency exchange rate
      When User goes to 'PrivatBank Home page'
      And Get '<currency>' currency exchange rate from UI
      Then compare '<currency>' exchange rate from UI and API


      Examples:
        | currency |
        |  USD     |
        |  EUR     |
