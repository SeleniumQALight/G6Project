Feature: User login with valid creds

  @R0012
  Scenario Outline: R0012 Login with valid login '<login>'
    Given User opens 'Login' page
    When User enters '<login>' login into 'Login' input on 'Login' page
    And User enters '<password>' passWord into 'PassWord' input on 'Login' page
    And User click on 'SingIn' button on 'Login' page
    Then User is logged in and sees the 'Sign out' button

    Examples:
    | login       | password      |
    |kamal        | Test12345678  |




