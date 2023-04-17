Feature: User registration

  @R003
  Scenario Outline: R003 Invalid registration
    Given User opens 'Login' page
    When User enters '<username>' in the field 'Username' input on 'Login' page
    And User enters '<email>' address in the field 'Email' input on 'Login' page
    And User enters '<password>' in the field 'Password' input on 'Login' page
    Then User see alert '<messages>'

    Examples:
      | username | email        | password     | messages                                                                                                                 |
      | wrong    | 1wrong       | wrong.com    | Username must be at least 3 characters.,You must provide a valid email address.,Password must be at least 12 characters. |
      | username | username.com | test         | Password must be at least 12 characters.                                                                                 |
      | test     | test.com     | 123456qwe    | That username is already taken.,You must provide a valid email address.                                                  |