Feature: Verifying POD user properties
  Validate the report link and summary section is available in the POD user created Service Item
  @EXSOpoduserprpt
  Scenario: Log in and verify the default profile
  	Given Launch EXSO app for D1 and log in as Admin for EXSO Assignment scenario validation
  @EXSOpoduserprpt
  Scenario: Validate properties
    Then Validate the report and Summary tile is available in the Service item of POD user

  @EXSOpoduserprpt
  Scenario: Reply to service item related mail and verify the status
   Then EXSO POD User Stop Report Generation for current scenario EXSO Assignment End to End Scenario
   Then EXSO POD User Close the browser Email auto approval process