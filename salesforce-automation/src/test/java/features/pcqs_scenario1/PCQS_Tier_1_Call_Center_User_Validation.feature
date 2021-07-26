Feature: Validate all expected feature is present in Application Receipt and Personal detail page and also validate different search criteria in cutomer search page
  Verify all expected feature is present in Application Receipt and Personal detail page with Tier 1 Call Center User
  @PCQSE2ETIER1CC
  Scenario: Log in to Saleforce application
  	Given For PCQS scenario Registered User is logged in with "zabid_cis@acumensolutions.com.uatg"
    When For PCQS scenario Search for required Internal User "Tier 1 Call Center User"
    Then For PCQS scenario Logging in as Internal user and verifying "Tier 1 Call Center User"
  @PCQSE2ETIER1CC
  Scenario: Search with Customer and Refresh the customer record
  	When For PCQS scenario Search for customer "A214018103"
    Then For PCQS scenario Add the customer data and wait for Customer tab opens successfully with name "PAULA TURANO" dob "1981-11-15" and source "CIS"
  @PCQSE2ETIER1CC
  Scenario: Validate customer page after refresh
  	Then For PCQS scenario To validate post refresh opened tab for "PAULA TURANO"
   @PCQSE2ETIER1CC
  Scenario: Open the customer record details from the auto openend page
  	Then For PCQS scenario Open application receipt page with given input "MSC1691282989"
   @PCQSE2ETIER1CC
  Scenario: Validate the application receipt page for all expected feature
  	Then For PCQS scenario Validate application receipt page
  	When For PCQS scenario Search for customer "A214018103"
  	Then For PCQS scenario Refresh the customer data and wait for Refresh successful message with name "PAULA TURANO" dob "1981-11-15" and source "CIS"
   @PCQSE2ETIER1CC
  Scenario: Search a customer details with customers first, last name and Date of birth and validate result page
  	Then For PCQS scenario Validate customer search with firstname "PAULA" lastname "TURANO" and dob "11151981"
   @PCQSE2ETIER1CC	
  Scenario: Delete the added customer with admin user
  	When For PCQS scenario Search for customer "A214018103"
  	Then For PCQS scenario Delete customer which was added
  	Then Stop Report Generation for current scenario For PCQS scenario
  	Then Close the browser For PCQS scenario
  """@PCQSE2ETIER1CC
  Scenario: Re-login with the user
  	When For PCQS scenario Search for required Internal User "Tier 1 Call Center User"
    Then For PCQS scenario Logging in as Internal user and verifying "Tier 1 Call Center User"
  @PCQSE2ETIER1CC
  Scenario: Search with Customer with ELIS Account Number
  	Then For PCQS scenario Search with ELIS Account number "077190469400"
    Then For PCQS scenario Add the customer data and wait for Customer tab opens successfully with name "LEYNA BATTA" dob "1981-05-12" and source "ELIS2"
  @PCQSE2ETIER1CC
  Scenario: Verify personal detail and application area of Elis account search result
  	Then For PCQS scenario Validate the customer personal detail page of Elis Account "LEYNA BATTA"
  	Then For PCQS scenario Validate the application area of Elis Account "IOE0945893030"
  @PCQSE2ETIER1CC
  Scenario: Search a customer details with customers first, last name and Date of birth and validate result page for ELIS acc
  	Then For PCQS scenario Validate customer search with firstname "LEYNA" lastname "BATTA" and dob "05121981"
   @PCQSE2ETIER1CC	
  Scenario: Delete the added ELIS customer with admin user
  	Then For PCQS scenario Search with ELIS Account number "077190469400"
  	Then For PCQS scenario Delete customer which was added
  	Then Stop Report Generation for current scenario For PCQS scenario
  	Then Close the browser For PCQS scenario"""