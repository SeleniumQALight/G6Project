
@LoginTest @FullRegression
  Feature: User registration

    @R003
   Scenario Outline: R003 Check registration form
      Given User opens 'Login' page
      When User enters '<username>' username into 'Username' input on 'Login' page
      And User enters '<email>' email into 'Email' input on 'Login' page
      And User enters '<password>' password into 'Password' input on 'Login' page
      Then User sees error messages '<messages>'

      Examples:
      | username      | email    | password     | messages |
      | wt            | ttt      | 1234y        | Username must be at least 3 characters.,You must provide a valid email address.,Password must be at least 12 characters. |
      | yry           | tt@t.com | 15yrc        | Password must be at least 12 characters.                                                                                 |
      | kosof68966    | ttt.com  | 123432123454 | This username is already taken.,You must provide a valid email address.                                                  |
