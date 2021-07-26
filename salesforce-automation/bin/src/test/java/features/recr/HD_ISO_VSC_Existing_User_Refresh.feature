Feature: Existing user record refresh and create a new service item with new response and approve with Supervisor user
  Verify if HD_ISO_VSC existing user record is getting refreshed and create new service item

  Scenario: Refresh Existing User Account and create new service item
    Given Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
    Then Verifying the current logged in user profile
    When Search for required Internal User "HD ISO VSC"
    Then Logging in as Internal user and verifying "HD ISO VSC"
    When Search for customer "A200453283"
    Then Refresh the customer data and wait for Refresh successful message
    Then Global Search with Customer ID "A200453283"
    Then Open application record from search result "WAC1690258857"
    Then Click on the new service item button
    And Provide Organization name for new service item "Test Organization"
    And Provide Email ID for new service item "zabid@acumensolutions.com"
    And Provide Sender type for new service item "ASC"
    And Provide Subject and Description for new service item
    And Provide Category and Kind for new service item
    And Provide Service Item Origin for new service item "Email"
    And Provide Initial Queue for new service item "VAWA_UPFD"
    And Provide Receive Date for new service item "10/4/2018 9:44 AM"
    Then Saving new service item
    Then Verify New Service Item number and Details
    Then Create a new response for new Service Item
    Then Verify newly created service response status
    Then Logout from current profile
    When Search for required Supervisor User "One HD Supervisor"
    Then Logging in as Supervisor user and verifying "One HD Supervisor"
    Then Select required service item with Supervisor user to approve/reject
    Then Approve the selected service request
    Then Validate approved request response
    Then Stop Report Generation for current scenario
    Then Close the browser for current scenario  
    
    
    