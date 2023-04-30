@RegistrationTest @FullRegression
Feature: User registration

  @R003
  Scenario Outline: R003 Registration with invalid '<credentials>'
    Given User opens 'Login' page to register
    When User enters '<credentials>' username into registration input
    And User enters '<email>' into email registration input
    And User enters '<password>' into password registration input
    Then User observes '<errorMessage>' on 'Login' page



    Examples:
      | credentials | email           | password      | errorMessage|
      | wr          | wr.com          | qwerty1234567 |Username must be at least 3 characters.,You must provide a valid email address.|
      | ys20230411  | solyu@gmail.com | qwerty1234567 |This username is already taken.            |

