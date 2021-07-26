Feature: Verifying OIDP new service response should not have any Customer Enquery and Attachment Section is just under the Response Recipient
  OIDP new service response should not have any Customer Enquery and Attachment Section is just under the Response Recipient
  @ISO12559_ISO12560
  Scenario: Log in and verify the default profile
  	Given OIDP ISO12559_ISO12560 scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
  @ISO12559_ISO12560
  Scenario: Log in with OIDP Analyst
    When OIDP ISO12559_ISO12560 scenario Search for required Internal User "OIDP Analyst"
    Then OIDP ISO12559_ISO12560 scenario Logging in as Internal user and verifying "OIDP Analyst"
  @ISO12559_ISO12560
  Scenario: Open an Exting service item
  	Then OIDP ISO12559_ISO12560 scenario Opening an Existing Service Item with OIDP analyst USer
  @ISO12559_ISO12560
  Scenario: Validating OIDP new service response should not have any Customer Enquery and Attachment Section is just under the Response Recipient
  	Then OIDP ISO12559_ISO12560 scenario Validation of new response creation page
  @ISO12559_ISO12560
  Scenario: Reply to service item related mail and verify the status
   Then Stop Report Generation for current scenario For OIDP ISO12559_ISO12560 response scenario
   Then Close the browser For OIDP ISO12559_ISO12560 response scenario