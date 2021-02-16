Feature: Cucumber Tests for Commonwealth bank

@SmokeTest
Scenario: Smoke Test End to End
	Given I launch the homepage
	And I validate homepage has loaded
	When I click on Tell me more section
	And I verify UI for the travel page
	Then I click on Netbank login
	And I validate netbank login page has loaded



