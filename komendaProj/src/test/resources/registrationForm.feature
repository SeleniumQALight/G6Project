Feature: User registrationForm

  @R003
  Scenario Outline: R003 Warning <messages>
    Given User opens 'Login' page
    When User enters '<username>' in the field 'Username' input on 'Login' page
    And User enters '<email>' address in the field 'Email' input on 'Login' page
    And User enters '<password>' in the field 'Password' input on 'Login' page
    Then User sees alert '<messages>'

    Examples:
      | username | email      | password     | messages                                                                                                                 |
      | 13       | 13         | 13           | Username must be at least 3 characters.,You must provide a valid email address.,Password must be at least 12 characters. |
      | Test     | test@t.com | test         | Password must be at least 12 characters.                                                                                 |
      | test     | test.com   | 123456qwerty | That username is already taken.,You must provide a valid email address.                                                  |
