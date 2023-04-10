Feature:  Registration

  @R001
  Scenario Outline: R001 Test error messages in registration fields <Username>_<Email>_<Password>
    Given  User opens 'Login' page
    When User type '<Username>' login into 'Username' registration input on 'Login' page
    And User type '<Email>' email into 'Email' registration input on 'Login' page
    And User type '<Password>' password into 'Password' registration input on 'Login' page
    Then User sees alerts messages with text '<Error>'

    Examples:
      |Username   |Email      |Password |Error                                                                                                                   |
      |tt          |ttt        |ttt     |Username must be at least 3 characters.,You must provide a valid email address.,Password must be at least 12 characters.|
      |tt          |tt@t       |ttt     |Username must be at least 3 characters.,Password must be at least 12 characters.                                        |
      |newUserName |tt@test.com|tt      |Password must be at least 12 characters.                                                                                |

