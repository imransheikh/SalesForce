Feature: creating a response with search template
  On an existing OIDP service item, creating a response with search template
  @OIDPSearchTemplate
  Scenario: Log in and verify the default profile
  	Given OIDP Search Response Template Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
  @OIDPSearchTemplate
  Scenario: Log in with Analyst user
    When OIDP Search Response Template Search for required Internal User "OIDP Analyst"
    Then OIDP Search Response Template Logging in as Internal user and verifying "OIDP Analyst"
  @OIDPSearchTemplate
  Scenario: Open an Exting service item
  	Then OIDP Search Response Template Click and open any random Service Item
  @OIDPSearchTemplate
  Scenario: Creating a new response with search template functionality
  	Then OIDP Search Response Template Select Response Template with Search functionality
  @OIDPSearchTemplate
  Scenario: Close the browser and save
   Then Stop Report Generation for current scenario For OIDP Search Response Template
   Then Close the browser For OIDP Search Response Template