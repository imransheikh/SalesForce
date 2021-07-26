Feature: Verifying POD user properties
  logging in as Paul Miller for assignment and status verification and AAO user Assignment verification
   @EXSOpaul
  Scenario: Log in as Paul Miller
  	Given Launching the app and logging in as Paul Miller for assignment and status verification and AAO user Assignment verification
  	Then Internal User logs in as PAUL MILLER
   @EXSOpaul
  Scenario: Create a Service Item and user should get an error while creating an assignment
    Then Create a Service Item with paul miller
    Then Validate the assignment creation and user should get an error
  @EXSOpaul   
  Scenario: Mark the override check box and check assignment is getting created or not and sub,it for approval
    Then Validate the assignment after override checkbox is marked
    Then Validate the assignment after override checkbox and user should be able to create assignment
 @EXSOpaul  
  Scenario: Log in as EXSO and Approve the Service item
    Then Log out and Log in as EXSO Manager and appriove the SI
    Then Log out and Log in as PAUL MILLER and create an Assignment and Grab the assignment number
  @EXSOpaul   
  Scenario: Validate 3 types of status
    Then Validate the Inactive, USPassbook is present in the SI status dropdown
  @EXSOpaul   
  Scenario: Validate the Assignment complition and check the promary user validation and mark the assignment completed
    Then Validate the Assignment properties and able to mark it as completed
  @EXSOpaul
  Scenario: Reply to service item related mail and verify the status
   Then Stop Report Generation for Paul Miller Scenario
   Then Close the browser for Paul Miller Scenario
  