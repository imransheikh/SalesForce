Feature: creating a response with search template
  On an existing OIDP service item, creating a response with search template
  @IPOSearchTemplate
  Scenario: Log in and verify the default profile
  	Given Response Search template selection process Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
  @IPOSearchTemplate
  Scenario: Log in with IPO User
    Then Response Search template selection process Verifying the current logged in user profile
    When Response Search template selection process Search for required Internal User "IPO CSR"
    Then Response Search template selection process Logging in as Internal user and verifying "IPO CSR"
  @IPOSearchTemplate
  Scenario: Open an Exting service item
  	Then Response Search template selection open any random Service Item "IPO CSR"
  @IPOSearchTemplate
  Scenario: Creating a new response with search template functionality
  	Then Response Search template selection Verify the new search functionality for template selection
  @IPOSearchTemplate
  Scenario: Close the browser and save
   Then Stop Report Generation for current scenario Response Search template selection
   Then Close the browser Response Search template selection