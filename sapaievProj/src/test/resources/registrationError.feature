Feature: User enter not valid data for registration

  @R0014
  Scenario Outline: R0014 Create user with invalid creds
    Given User opens 'Login' page
    When User enters '<userName>' for registration into 'userName' input on 'Login' page
    And  User enters '<email>' for registration into 'email' input on 'Login' page
    Then User sees  '<expectedErrors>' under inputFields

    Examples:
      | userName    | email           | expectedErrors                                                                |
      |d            | w               |Username must be at least 3 characters.,You must provide a valid email address.|
      |@@@@       | w12122121         |Username can only contain letters and numbers.,You must provide a valid email address.        |