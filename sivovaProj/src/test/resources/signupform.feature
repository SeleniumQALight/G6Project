@R002
Feature: Verify error messages on 'Sign Up' form

  Scenario Outline: R002 Verify error messages
    Given User opens 'Login' page
    When User enters '<username>' username into 'Username' input on 'Login' page
    And User enters '<email>' email into 'Email' input on 'Login' page
    And User enters '<password>' password into 'Password' input on 'Login' page
    Then User sees error messages '<messages>'

    Examples:
      | username | email         | password | messages                                                                                                                  |
      | te       | tttt          | pa       | Username must be at least 3 characters.,You must provide a valid email address.,Password must be at least 12 characters.  |
      | qaauto   | test@test.com | pa       | This username is already taken.,This email is already being used.,Password must be at least 12 characters.                |
      | @#1      | test@test.com | pa       | Username can only contain letters and numbers.,This email is already being used.,Password must be at least 12 characters. |