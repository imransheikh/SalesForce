Feature: Verifying the owner change previledges for one HD Supervisor, HD ISOA and HD ISO VSC
  Verifying the previledges for different users
  @ISO12556
  Scenario: Log in and verify the default profile
  	Given For ISO12556 scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
  @ISO12556
  Scenario: Log in with ONE HD SUPERVISOR and Set QC percentage to 100%
    Then For ISO12556 scenario set QC Percentage to "100"
  @ISO12556
  Scenario: Search with Customer and Refresh the customer record
  	When For ISO12556 response scenario Search for customer "A200453283"
    Then For ISO12556 scenario Refresh the customer data and wait for Refresh successful message
  @ISO12556
  Scenario: Open the customer record details
  	Then For ISO12556 scenario Open the application record details from Refresh User page "WAC1690258857"
  
  @ISO12556
  Scenario: Create new service item by providing below input data
   Then For ISO12556 scenario Click on create new service item "WAC1690258857"
   And For ISO12556 scenario create new service item Provide all new mandatory data
   | Org Name    | Email 						 | Sender Type   | Subject 		| Description      | Form Type                                      | Category    | Kind                  | Comments   | Item Origin | Queue     | DateTime         |
   | Test Org12  | zabid@acumensolutions.com 	 | ASC 			 | Test subject | Test Description | I-765 Application for Employment Authorization | Case Status | Inappropriate Contact | Creating SI| Email       | VAWA_UPFD | 10/4/2018 9:44 AM|
  @ISO12556
  Scenario: Fetch any existing open service item as ONE HD SUPERVISOR
  	Then For ISO12556 scenario open any existing SI
  @ISO12556
  Scenario: Verification of ONE HD SUPERVISOR user can change the Service item owner
  	Then For ISO12556 scenario Change the owner to VAWA_DACADutyDesk and then change to VAWA_ISOA
  @ISO12556
  Scenario: Verification of ISO Queue Assignment selection for HD ISOA user
   Then For ISO12556 scenario ISO Queue Assignment selection for HD ISOA user
  @ISO12556	
  Scenario: Logging in as HD ISOA
   Then For ISO12556 scenario Logging is as HD ISOA
   @ISO12556	
  Scenario: Finding the correct service item by marking other one as duplicate
   Then For ISO12556 scenario Mark existing open items as duplicate and open the new SI by assign a new service item
  @ISO12556
  Scenario: Verify the selected service item has the owner as HD ISOA
   Then For ISO12556 scenario Open the selected SI and Verify the current Owner is HD ISOA
  @ISO12556
  Scenario: Verify ISOA user can change the SI owner
   Then For ISO12556 scenario Verify as HD ISOA owner can be changed to VAWA_I918inquiriesNSC, HD Correspondence Queue and VAWA_DACADutyDesk
   @ISO12556
  Scenario: Log in as HD ISO VSC and verify the ISO user can't change the SI owner'
   Then For ISO12556 scenario Logging is as HD ISO VSC
   Then For ISO12556 scenario Select any random open SI and verify as HD ISO VSC, user can't change the owner to VAWA_DACADutyDesk
   Then Stop Report Generation for current scenario For Iso12556 scenario
   Then Close the browser For Iso12556 scenario