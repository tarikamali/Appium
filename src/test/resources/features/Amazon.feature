Feature: Login and checkout the item

Background: I basic setup amazon app
Given I am on my main screen

@run
Scenario: Login to Amazon
  Given I see login screen
  When I enter username and password
  Then I am on home page
  When I Search "65-inch TV"
  And I select item index "16" and add to cart



