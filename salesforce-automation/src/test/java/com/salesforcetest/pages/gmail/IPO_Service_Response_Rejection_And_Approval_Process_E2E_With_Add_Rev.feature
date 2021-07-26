Feature: Existing user record refresh and using an existing service item with new response and reject with Supervisor user and upon re-submitting after correction supervisor will approve
  Verify End to End flow for new response rejection and approval process enabling Additional Review Checkbox
  @RegForIpo
  Scenario: Log in with IPO CSR user to refresh the application data
  	Given For Ipo rejection and approval of response scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
  	Then For Ipo rejection and approval of response scenario set QC Percentage to "100"
    Then For Ipo rejection and approval of response scenario Verifying the current logged in user profile
    When For Ipo rejection and approval of response scenario Search for required Internal User "IPO CSR"
    Then For Ipo rejection and approval of response scenario Logging in as Internal user and verifying "IPO CSR"
  @RegForIpo
  Scenario: Opening an Existing Service Item from Open service Item
   Then For Ipo rejection and approval of response scenario Opening an Existing SI with Receipt No "IPO CSR" to verify Additional Review Checkbox functionality
  @RegForIpo
  Scenario: Creating a new response and validating its status
   Then For Ipo rejection and approval of response scenario Create a new response for random Service Item
   Then For Ipo rejection and approval of response scenario Verify newly created service response status "Under Review"
  @RegForIpo
  Scenario: Logging out from IPO CSR and logging in with IPO Super User
   Then For Ipo rejection and approval of response scenario Logout from current profile
   When For Ipo rejection and approval of response scenario Search for required Super User "IPO Super User"
   Then For Ipo rejection and approval of response scenario Logging in as Supervisor user and verifying "IPO Super User"
  @RegForIpo 
  Scenario: Reject the Service item response with IPO Super User user and with proper comments
   Then For Ipo rejection and approval of response scenario Select required service item with Super user to reject
   Then For Ipo rejection and approval of response scenario Reject the selected service request
   Then For Ipo rejection and approval of response scenario Verify the rejected service request status
  @RegForIpo
  Scenario: Log out from IPO Super User user profile and log in with IPO CSR
   Then For Ipo rejection and approval of response scenario Logout from supervisor profile
   When For Ipo rejection and approval of response scenario Search for required Internal User which created the response "IPO CSR"
   Then For Ipo rejection and approval of response scenario Logging in as Internal user and verifying User which created the response "IPO CSR"  
  @RegForIpo
  Scenario: Re-submitting the Service Item response for Supervisor approval and log out
   Then For Ipo rejection and approval of response scenario Edit pre-created response and resubmit
   Then For Ipo rejection and approval of response scenario Logout from response editor's profile
  @RegForIpo
  Scenario: Log in with Supervisor user and approve the service request response
   When For Ipo rejection and approval of response scenario Search for required Internal User Supervisor user "IPO Super User"
   Then For Ipo rejection and approval of response scenario Logging in as Internal user and verifying Supervisor user "IPO Super User"
   Then For Ipo rejection and approval of response scenario Select required service item with Supervisor user to approve
   Then For Ipo rejection and approval of response scenario Approve the selected service request
   Then For Ipo rejection and approval of response scenario Validate approved request response status changed to sent
   Then Stop Report Generation for current scenario For Ipo rejection and approval of response scenario
   Then Close the browser For Ipo rejection and approval of response scenario