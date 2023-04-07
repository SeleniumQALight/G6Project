
Feature: User registration

  @R003
  Scenario Outline:  R003 Error <messages> for invalid credentials
    Given  User opens 'Login' page
    When User enters '<username>' login into 'Username' input on 'Login' page
    And User enters '<email>' email into 'Email' input on 'Login' page
    And User enters '<password>' password into 'Password' input on 'Login' page
    Then User sees alert '<messages>'


    Examples:
      | username       | email        | password                                            | messages                                                                                                                |
      | 11             | 222          | 333                                                 | Username must be at least 3 characters.,You must provide a valid email address.,Password must be at least 12 characters.|
      | newuser        | oo.pp        | 123456poiuytr                                       | You must provide a valid email address.                                                                                 |
      | ne             | oo@pp        | efk                                                 | Username must be at least 3 characters.,Password must be at least 12 characters.                                        |
      | au             | oo@pp        | 1234567890qwertyuiop1234567890qwertyuiop12345678901 | Username must be at least 3 characters.,Password cannot exceed 50 characters.                                        |
