Feature: EXSO user scenario1 with different kinds of Assignments with Multiple Pod User
  Verify EXSO Assignment creation for Pod user and validations
  @EXSOSCR01
  Scenario: Log into EXSO Org
  	Given Launch EXSO app and log in as Admin for EXSO Assignment scenario validation
  @EXSOSCR01	
  Scenario: Fetch setup url for STARS2 UATG
  	Then Fetching the incident URL from the Setup page
  @EXSOSCR01	
  Scenario: Create new SI with Rest Api from back end and fetch the service item number
  	Then Creating a Service Item from back end and Execute next steps in this SI
  	Then log in as EXSO internal user and accept it and Grab the newly created Servcie Item
  @EXSOSCR01
  Scenario: Open the newly created service item and upload an attachment and create a new assignment with Pod user
  	Then Open newly created Service Item and Create New Assignment and log out from it.
  @EXSOSCR01
  Scenario: Login as AAO Pod user and open the assignment and change the due date
  	Then Log in as AAO Pod User and change the due date to some future date and save it and log out
  @EXSOSCR01	
  Scenario: Approve the due date change request with EXSO and log out
  	Then Log in as EXSO user and Approve the changed due date and log out
  @EXSOSCR01	
  Scenario: Validate due date is updated to Assignment details page
  	Then Log in as As AAO Pod user and Validate the due date is changed or not
  @EXSOSCR01	
  Scenario: Verify user able to send mail by selecting multiple user from an Assignment and able to upload file
  	Then Verify user able to send mail by selecting multiple user from an Assignment and able to upload file to Assignment
  @EXSOSCR01	
  Scenario: Accept and Complete the assignment and verify all the required validation 
  	Then Accept the assignment and See the accepted by status has been changed to expected value
  	Then Complete the assignment and See the accepted by status has been changed to expected value
  @EXSOSCR01	
  Scenario: Create assignment for multiple user clerance type 
  	Then Log in as EXSO manager and validate the status and Round Number and create new Assignment with Clerance with two POD user and log out
  @EXSOSCR01
  Scenario: Accept clearance assignment as AAO Pod user and mark complete
  	Then Log in as 1st AAO POD user for Round 2 and send mail with attacments with one user
  	Then Accept and Complete the assignment and See the accepted by status has been changed to expected value
  @EXSOSCR01
  Scenario: Accept clearance assignment as PVY Pod user and mark complete
  	Then Log in as PVY pod user and Accept and Complete the assignment and See the accepted by status has been changed to expected value
  @EXSOSCR01
  Scenario: Log in with EXSO manager and Validate after above steps and create new authoring assignments and validate status and Round
  	Then Log in as EXSO user and validate the service item status is changed after Creating a new assignment and round count changed and newly autoring assignment is reflected in the SI
 
  @EXSOSCR01
  Scenario: Log in with EXSO manager and Validate the 2nd Authoring Assignment Round 2 value and accept and complete the assignment
  	Then Validate the 2nd Authoring Assignment Round 2 value and accept and complete the assignment.
  @EXSOSCR01
  Scenario: Create 2 more clearence assginments with PVY and AAO and complete the AAO Assignment with proper validation for Round 2
  	Then Create 2 more clearence assginments with PVY and AAO and complete the AAO Assignment with proper validation 
  @EXSOSCR01	
  Scenario: After that Complete the PVY assignment and validate the Round 2 for clerance Assignment and validate all the Assignment present in the parent SI section
  	Then Complete the PVY assignment and validate the Round 2 for clerance Assignment and validate all the Assignment present in the parent SI section.
  @EXSOSCR01
  Scenario: After that Validate the SI status after all assignments are completed and Create FYI record and Validate and Update the file type and Validate the update is reflected or not
  	Then Validate the SI status after all assignments are completed and Create FYI record and Validate and Update the file type and Validate the update is reflected or not.
  @EXSOSCR01
  Scenario: Generate the RCA and Close the SI and validate the status
  	Then Generate the RCA and Close the SI and validate the status.		
  	Then EXSO Stop Report Generation for current scenario EXSO Assignment End to End Scenario1
  	Then EXSO Close the browser Email auto approval process