Feature: User Login


  Scenario Outline: R001 Login with invalid login '<login>'
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees alert message with text 'Invalid username / password.'



    Examples:
    | login       | password    |
    | wrong login | 123456qwerty|
    | wrong login1| 123456qwerty|

    @R001
    Scenario Outline: R001 Login with valid login
      Given User opens 'Login' page
      When User enters '<login>' login into 'Login' input on 'Login' page
      And User enters '<password>' passWord into 'PassWord' input on 'Login' page
      And User click on 'SingIn' button on 'Login' page
      Then User sees 'Signout' button

      Examples:
      |   login   |   password         |
      |    qaauto |   123456qwerty     |

    @R001
    Scenario Outline: R001 Verify error messages
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