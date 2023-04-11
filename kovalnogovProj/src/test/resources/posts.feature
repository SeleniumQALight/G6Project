@PostTest @FullRegression
Feature:  Post Feature

  Background:
    Given  User opens 'Home' page

  @R011
    @BeforeDeletingAllPostsForDefaultUser
    @AfterDeletingAllPostsForDefaultUser
  Scenario Outline: R011 Check number of posts
    Given  Create <numberOfPosts> new posts via API for 'default' user and 'default' password
    When User clicks on 'MyProfile' button on 'Home' page
    Then User was redirected to 'MyProfile' page
    And User sees <numberOfPosts> posts in Posts List

    Examples:
      | numberOfPosts |
      | 2             |


