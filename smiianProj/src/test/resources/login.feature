
@LoginTest @FullRegression
  Feature: User login

    @R001
    Scenario Outline: R001 Login with invalid login '<login>'
      Given User open 'Login' page
      When User enters '<login>' login into 'Login' input on 'Login' page
      And User enters '<password>' passWord into 'PassWord' input on 'Login' page
      And User click on 'SingIn' button on 'Login' page
      Then User sees alert message with text 'Invalid username / pasword.'


      Examples:
      | login        | password     |
      | wrong login  | 123456qwerty |
      | wrong login1 | 123456 |



    @R002
    Scenario: R002 Login with valid credentials
      Given User open 'Login' page
      When User enters valid login into 'Login' input on 'Login' page
      And User enters valid password into 'Password' input on 'Login' page
      And User click on 'SingIn' button on 'Login' page
      Then User sees users account avatar

    @R003
    Scenario Outline: R003 Check error messages in registration form
      Given User open 'Login' page
      When User enters not valid '<login>' into 'Login' input on 'Login' page
      And User enters not valid '<email>' into 'Email' input on 'Login' page
      And User enters not valid '<password>' into 'Password' input on 'Login' page
      Then Check '<errorsList>' text


      Examples:
        | login                            | email       | password                                            | errorsList                                                                                                                 |
        | tt                               | ttt         | 1                                                   | Username must be at least 3 characters., You must provide a valid email address., Password must be at least 12 characters. |
        | tt                               | we@qa.team  | 123456789012345678901234567890123456789012345678901 | Username must be at least 3 characters., That email is already being used.,       Password cannot exceed 50 characters.    |
        | tt                               | val@qa.com  | validPass                                           | Username must be at least 3 characters.,                                                                                   |
        | 1234567890123456789012345678901  | ttt         | 123456789012345678901234567890123456789012345678901 | That username is already taken.,         You must provide a valid email address., Password cannot exceed 50 characters.    |
        | 1234567890123456789012345678901  | we@qa.team  | validPass                                           | That username is already taken.,         That email is already being used.                                                 |
        | 1234567890123456789012345678901  | val@qa.com  | 1                                                   | That username is already taken.,                                                  Password must be at least 12 characters. |
        | 12345678901234567890123456789012 | ttt         | validPass                                           | Username cannot exceed 30 characters.,   You must provide a valid email address.                                           |
        | 12345678901234567890123456789012 | we@qa.team  | 1                                                   | Username cannot exceed 30 characters.,   That email is already being used.,       Password must be at least 12 characters. |
        | 12345678901234567890123456789012 | val@qa.com  | 123456789012345678901234567890123456789012345678901 | Username cannot exceed 30 characters.,                                            Password cannot exceed 50 characters.    |
        | testUserA                        | ttt         | 1                                                   |                                          You must provide a valid email address., Password must be at least 12 characters. |
        | testUserA                        | we@qa.team  | 123456789012345678901234567890123456789012345678901 |                                          That email is already being used.       , Password cannot exceed 50 characters.   |
        | testUserA                        | val@qa.com  | validPass                                           |                                                                                                                            |
