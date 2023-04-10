Feature: User registration

  @R003
  Scenario Outline: R003 Registration with invalid creds
    Given User opens 'Login' page
    When User enters '<login>' login into 'UserName' input on 'Login' page
    And User enters '<email>' email into 'Email' input on 'Login' page
    And User enters '<password>' password into 'Password' input on 'Login' page
    Then User sees error message with text '<error>'

    Examples:
      | login | email      | password     | error                                                                                                                 |
      | 13       | 13         | 13           | Username must be at least 3 characters.,You must provide a valid email address.,Password must be at least 12 characters. |
      | Test     | test@t.com | test         | Password must be at least 12 characters.                                                                                 |
      | test     | test.com   | 123456qwerty | That username is already taken.,You must provide a valid email address.                       |



