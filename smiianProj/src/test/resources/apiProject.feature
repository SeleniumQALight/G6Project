
@PostTest @FullRegression
  Feature: ApiProject Feature

#    Background:
#      Given User open 'Home' page

    @R007
   Scenario Outline: R007 Project task
      Given User send API request to PrivatBank to receive and save the exchange rate for '<currency>'
      When User opened Ui of PrivatBank to receive and save the exchange rate for '<currency>'
      Then User compare saved strings


      Examples:
        | currency  |
        | EUR       |
        | USD       |




