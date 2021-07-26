Feature: Verify the Article dropdown is present and Add History checkbox is unchecked
  Verify the Article dropdown is present and Add History checkbox is unchecked
  @ISO13881_14883
  Scenario: Log in with IPO CSR user to refresh the application data
  	Given For Ipo ISO13881-14883 scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
    When For Ipo ISO13881_14883 scenario Search for required Internal User "IPO CSR"
    Then For Ipo ISO13881_14883 scenario Logging in as Internal user and verifying "IPO CSR"
  
  @ISO13881_14883
  Scenario: Verify article dropdown is present or not
    Then For ISO13881_14883 scenario Verify article dropdown is available or not
  
  @ISO13881_14883
  Scenario: Validate Add history checkbox is not check while creating new response
   Then For ISO13881_14883 scenario Validate Add history checkbox is not check while creating new response
  
  @ISO13881_14883
  Scenario: Ending the script and close the browser
   Then Stop Report Generation for current scenario For ISO13881_14883 scenario
   Then Close the browser For ISO13881_14883 scenario