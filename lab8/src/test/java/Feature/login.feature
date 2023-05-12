Feature: Login to https://www.saucedemo.com/

  Scenario: Correct Login
    Given I am on the login page
    And I enter the username "standard_user"
    And I enter password "secret_sauce"
    When I click the login button
    Then I should be logged in

  Scenario: Correct Password but Wrong Username
    Given I am on the login page
    And I enter the username "user"
    And I enter password "secret_sauce"
    When I click the login button
    Then I should see an error message "Epic sadface: Username and password do not match any user in this service"

  Scenario: Correct Username but Wrong Password
    Given I am on the login page
    And I enter the username "standard_user"
    And I enter password "password"
    When I click the login button
    Then I should see an error message "Epic sadface: Username and password do not match any user in this service"

  Scenario: Swapped Username and Password
    Given I am on the login page
    And I enter the username "password"
    And I enter password "standard_user"
    When I click the login button
    Then I should see an error message "Epic sadface: Username and password do not match any user in this service"

  Scenario: Empty Login
    Given I am on the login page
    And I enter password "password"
    When I click the login button
    Then I should see an error message "Epic sadface: Username is required"

  Scenario: Empty Password
    Given I am on the login page
    And I enter the username "standard_user"
    When I click the login button
    Then I should see an error message "Epic sadface: Password is required"

  Scenario: Empty Login and Password
    Given I am on the login page
    When I click the login button
    Then I should see an error message "Epic sadface: Username is required"
