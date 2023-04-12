
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
        | login                            | email       | password                                            | errorsList                                                                                                               |
        | tt                               | ttt         | 1                                                   | Username must be at least 3 characters.,You must provide a valid email address.,Password must be at least 12 characters. |
        | 123456789012345678901234567890   | we@qa.team  | 123456789012345678901234567890123456789012345678901 | That username is already taken.,That email is already being used.,Password cannot exceed 50 characters.                  |
        | 12345678901234567890123456789015 |             |                                                     | Username cannot exceed 30 characters.                                                                                    |


