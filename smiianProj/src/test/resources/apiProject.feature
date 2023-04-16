
@PostTest @FullRegression
  Feature: ApiProject Feature

#    Background:
#      Given User open 'Home' page

    @R007
#      @BeforeDeletingAllPostsForDefaultUser
#      @AfterDeletingAllPostsForDefaultUser
   Scenario Outline: R007 Project task
      Given User sends a request to PrivatBank to receive and seve the exchange rate for '<currency>'
      When User opened Ui of Privat Bank to receive and seve the exchange rate for '<currency>'
      Then User compare saved strings


      Examples:
        | currency  |
        | EUR       |
        | USD       |




