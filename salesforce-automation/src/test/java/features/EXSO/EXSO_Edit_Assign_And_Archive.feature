Feature: Verifying POD user properties
  Validate the report link and summary section is available in the POD user created Service Item
  @EXSOeditAssi
  Scenario: Log in and verify the default profile
  	Given Launching the EXSO app and logging in as EXSO Service Item Manager for Edit Assiment validation
  @EXSOeditAssi
  Scenario: Log in as internal user
    Then Internal User log in as EXSO Service Item Manager for EXSO
    Then Open any automation script created Service Item
  @EXSOeditAssi
  Scenario: Edit the assignment and edited the comments
    Then Edit the service item and validate the edited comments are populated in the assignment details
  @EXSOeditAssi
  Scenario: Archive the assignment
    Then Make an assignment as Archive
  @EXSOeditAssi
  Scenario: Reply to service item related mail and verify the status
   Then EXSO Stop Report Generation for scenario Edit and Archive validation
   Then EXSO Close the browser for Edit and Archive validation