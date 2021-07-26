Feature: Verifying OIDP service links got added or not
  On an existing OIDP service item whether user can add a service item links
  @OIDPISO12664
  Scenario: Log in and verify the default profile
  	Given OIDP ISO12664 scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
  @OIDPISO12664
  Scenario: Log in with OIDP SUPERVISOR and Set QC percentage to 100%
    When OIDP ISO12664 scenario Search for required Internal User "OIDP Analyst"
    Then OIDP ISO12664 scenario Logging in as Internal user and verifying "OIDP Analyst"
  @OIDPISO12664
  Scenario: Open an Exting service item
  	Then OIDP ISO12664 scenario Open an existing service item
  @OIDPISO12664
  Scenario: Creating and verifying new service link
  	Then OIDP ISO12664 scenario Creating a new Service LINK with contact name as "Zaim Abid" and role as "Congressional Liaison"
  @OIDPISO12664
  Scenario: Reply to service item related mail and verify the status
   Then Stop Report Generation for current scenario For OIDP ISO12664 scenario
   Then Close the browser For OIDP ISO12664 scenario