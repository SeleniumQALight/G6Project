@LoginTest @FullRegression
Feature: User login

  @R001
  Scenario Outline:  R001 Login with invalid '<login>'
    Given  User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees alert message with text 'Invalid username / password.'


    Examples:
    | login       | password     |
    | wrong login | 123456qwerty |
    | wrong login1| 123456       |

  @R002
  Scenario:  R002 Login with invalid login
    Given  User opens 'Login' page
    When User enters 'qaauto' login into 'Login' input on 'Login' page
    And User enters '123456qwerty' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User sees 'My Profile' avatar