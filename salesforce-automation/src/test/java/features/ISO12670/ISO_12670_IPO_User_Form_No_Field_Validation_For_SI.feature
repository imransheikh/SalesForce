Feature: Existing user record refresh and create a new service item with new response and Approve the service response with super user
  Verify the approval process with super user for a service response
  @ISO12670FormNoBlank
  Scenario: Log in with IPO CSR user to refresh the application data
  	Given For Ipo ISO12670 scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
    When For Ipo ISO12670 scenario Search for required Internal User "IPO CSR"
    Then For Ipo ISO12670 scenario Logging in as Internal user and verifying "IPO CSR"
  
  @ISO12670FormNoBlank
  Scenario: Open an existing Service Item
    Then For ISO12670 scenario open any existing SI
  
  @ISO12670FormNoBlank
  Scenario: For non duplicate service item user can't save blank Form No
  	Then For ISO12670 scenario Verify while saving an SI with Form No as blank, user is getting an error
  
  @ISO12670FormNoBlank
  Scenario: Mark as duplicate
   Then For Ipo ISO12670 scenario Mark the selected item as Duplicate
   
  @ISO12670FormNoBlank
  Scenario: Verify user able to save Blank Form No for Duplicate SI
   Then For Ipo ISO12670 scenario Verify user successfully set blank form no for an duplicate SI
  
  @ISO12670FormNoBlank
  Scenario: Ending the script and close the browser
   Then Stop Report Generation for current scenario For ISO12670 scenario
   Then Close the browser For ISO12670 scenario