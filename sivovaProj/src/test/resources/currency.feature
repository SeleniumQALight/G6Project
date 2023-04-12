@R001

  Feature: Get currency exchange rate by ID

    Scenario Outline: Get currency exchange rate by '<id>' for '<currency>'/UAH
      Given Get '<currency>' currency exchange rate by '<id>'
      When User goes to 'PrivatBank Home page'
      Then compare '<currency>' exchange rate from UI and API




      Examples:
      |  id  | currency |
      |  5   |  USD     |
      |  5   |  EUR     |
