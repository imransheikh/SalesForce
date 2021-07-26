Feature: Verify the New Response has response recipient is selected by default
  Verify the New Response has response recipient is selected by default
  @ISO12648DefaultRecipient
  Scenario: Log in with IPO CSR user to refresh the application data
  	Given For Ipo ISO12648 scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
    When For Ipo ISO12648 scenario Search for required Internal User "IPO CSR"
    Then For Ipo ISO12648 scenario Logging in as Internal user and verifying "IPO CSR"
  
  @ISO12648DefaultRecipient
  Scenario: Open an existing Service Item
    Then For ISO12648 scenario open any existing SI
  
  @ISO12648DefaultRecipient
  Scenario: Validate the new response has a response recipient selected by default
   Then For ISO12648 scenario Validate the new response has a response recipient selected by default
  
  @ISO12648DefaultRecipient
  Scenario: Ending the script and close the browser
   Then Stop Report Generation for current scenario For ISO12648 scenario
   Then Close the browser For ISO12648 scenario