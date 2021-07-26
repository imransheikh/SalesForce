Feature: Validate all expected feature is present in Application Receipt and Personal detail page and also validate different search criteria in cutomer search page
  Verify all expected feature is present in Application Receipt and Personal detail page with CCC user
  @PCQSE2ECCCISOTESTU
  Scenario: Log in to Saleforce application
  	Given For PCQS scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
    When For PCQS scenario Search for required Internal User "CCC ISO Test User"
    Then For PCQS scenario Logging in as Internal user and verifying "CCC ISO Test User"
  @PCQSE2ECCCISOTESTU
  Scenario: Search with Customer and Refresh the customer record
  	When For PCQS scenario Search for customer "A214018103"
    Then For PCQS scenario Add the customer data and wait for Customer tab opens successfully with name "PAULA TURANO" dob "1981-11-15" and source "CIS"
  @PCQSE2ECCCISOTESTU
  Scenario: Validate customer page after refresh
  	Then For PCQS scenario To validate post refresh opened tab for "PAULA TURANO"
  @PCQSE2ECCCISOTESTU
  Scenario: Open the customer record details from the auto openend page
  	Then For PCQS scenario Open application receipt page with given input "MSC1691282989"
  @PCQSE2ECCCISOTESTU
  Scenario: Validate the application receipt page for all expected feature
  	Then For PCQS scenario Validate application receipt page
  	When For PCQS scenario Search for customer "A214018103"
  	Then For PCQS scenario Refresh the customer data and wait for Refresh successful message with name "PAULA TURANO" dob "1981-11-15" and source "CIS"
  @PCQSE2ECCCISOTESTU
  Scenario: Search a customer details with customers first, last name and Date of birth and validate result page
  	Then For PCQS scenario Validate customer search with firstname "PAULA" lastname "TURANO" and dob "11151981"
  @PCQSE2ECCCISOTESTU	
  Scenario: Delete the added customer with admin user
  	When For PCQS scenario Search for customer "A214018103"
  	Then For PCQS scenario Delete customer which was added
  	Then Stop Report Generation for current scenario For PCQS scenario
  	Then Close the browser For PCQS scenario
  #@PCQSE2ECCCISOTESTU	
  """Scenario: Re-login with the user
  	When For PCQS scenario Search for required Internal User "CCC ISO Test User"
    Then For PCQS scenario Logging in as Internal user and verifying "CCC ISO Test User"
  @PCQSE2ECCCISOTESTU
  Scenario: Search with Customer with ELIS Account Number
  	Then For PCQS scenario Search with ELIS Account number "077190469400"
    Then For PCQS scenario Add the customer data and wait for Customer tab opens successfully with name "LEYNA BATTA" dob "1981-05-12" and source "ELIS2"
  @PCQSE2ECCCISOTESTU
  Scenario: Verify personal detail and application area of Elis account search result
  	Then For PCQS scenario Validate the customer personal detail page of Elis Account "LEYNA BATTA"
  	Then For PCQS scenario Validate the application area of Elis Account "IOE0945893030"
  @PCQSE2ECCCISOTESTU
  Scenario: Search a customer details with customers first, last name and Date of birth and validate result page for ELIS acc
  	Then For PCQS scenario Validate customer search with firstname "LEYNA" lastname "BATTA" and dob "05121981"
   @PCQSE2ECCCISOTESTU
  Scenario: Delete the added ELIS customer with admin user
  	Then For PCQS scenario Search with ELIS Account number "077190469400"
  	Then For PCQS scenario Delete customer which was added
  	Then Stop Report Generation for current scenario For PCQS scenario
  	Then Close the browser For PCQS scenario"""