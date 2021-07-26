Feature: Account Creation Feature
  Verify if Privacy Staff user is able to create account

  Scenario: Create Internal and External Account
    Given User is logged in with "Privacy Staff"
    When Create Internal Account and Contact
    And Create External Account and Contact
    Then Close the reporter
    Then Close the browser
    
    
    
    
    
    
    