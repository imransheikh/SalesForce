Feature: Customer Search Feature
  Verify if HD_ISO_VSC user is able to search customer account

  Scenario: Search Customer Account
    Given User is logged in with "Admin"
    When Internal User logged in with user "HD ISO VSC"
    And Search customer "A200543283"
    And Update the user
    Then Close the reporter
    Then Close the browser