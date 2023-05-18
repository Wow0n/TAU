Feature: Check cart functionality

  Scenario: Add to cart and check cart badge on the same site
    Given I am on the login page
    And I login correctly by username "standard_user" and password "secret_sauce"
    And I add two products to cart
    Then I check if cart badge equals "2"

  Scenario: Add to cart and check cart badge after refresh
    Given I am on the login page
    And I login correctly by username "standard_user" and password "secret_sauce"
    And I add two products to cart
    When I refresh the page
    Then I check if cart badge equals "2"

  Scenario: Add to cart and check cart badge on different sites
    Given I am on the login page
    And I login correctly by username "standard_user" and password "secret_sauce"
    And I add two products to cart
    And I click on product "#item_0_img_link > .inventory_item_img"
    Then I check if cart badge equals "2"
    And I go back to products page
    Then I check if cart badge equals "2"
    And I click on product "#item_5_img_link > .inventory_item_img"
    Then I check if cart badge equals "2"


  Scenario: Add product to the existing cart
    Given I am on the login page
    And I login correctly by username "standard_user" and password "secret_sauce"
    And I add two products to cart
    Then I check if cart badge equals "2"
    And I click on product "#item_5_img_link > .inventory_item_img"
    And I add it to the cart
    Then I check if cart badge equals "3"