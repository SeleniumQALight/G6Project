@PostTest @FullRegression
Feature: Post test

  Background:
    Given User open 'Home' page

  @R011
    @BeforeDeletingAllPostsForDefaultUser
    @AfterDeletingAllPostsForDefaultUser
  Scenario Outline: R011 Check number of posts
    Given Create <numberOfPosts> new post via API for 'default' user and 'default' password
    When User click on 'MyProfile' button on 'Home' page
    Then User was redirected to 'MyProfile' page
    And User sees <numberOfPosts> posts in Posts list on 'MyProfile' page

    Examples:
      |numberOfPosts|
      |2            |
