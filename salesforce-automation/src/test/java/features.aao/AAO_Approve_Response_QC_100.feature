 Feature: Verifying the AAO End to End scenario for Response Approval process for Quality Percentage set to 100%
 @AAOApproveResponse
  Scenario: Registered user is logged in as AAO SuperUser and creates a service Item.Set QC percentage of AAO Officer to 100
  
Given For Response Approval Scenario set the initial conditions
When For Response Approval Scenario  Registered user is logged in as "AAO SuperUser"
Then For Response Approval Scenario Superuser creates an application with Filetype as "Receipt Number"
And For Response Approval Scenario clicks to create "AAO Service Item" from application detail
And For Response Approval Scenario creates serviceitem with the following data
|Item Type|Office|Officer Comments|Supervisor Comments|Supervisor|Editor|Contact|Account|Business|Attorney|Internal Contact|      
 |Appeal  | AAO|test officer comments|test supervisor comments|AAO SuperUser|AAO SuperUser|EDVARD EDOUARD|EDVARD EDOUARD|test business|test attorney|AAO SuperUser|
 And For Response Approval Scenario sets qa percentage of officer to "100"
 
  @AAOApproveResponse
 Scenario: Log out as super user and login as AAO Officer.Create a new response for the service item created in the previous scenario.
 
 When For Response Approval Scenario user is logged in as "AAO Officer"
 Then For Response Approval Scenario create a new response for the service item
 And For Response Approval Scenario Verify Status as "Under Review"
 And For Response Approval Scenario Verify Owner as "AAO Supervisors"
 
 Then For Response Approval Scenario create a new response of type "Internal"

 And For Response Approval Scenario Verify "1" Response version is created
 
 @AAOApproveResponse
 
 Scenario:Log out as AAO Officer and login as AAO Supervisor.Reject the response
 
  When For Response Approval Scenario user logs in as "AAO Supervisor"
  Then For Response Approval Scenario access the service item created in the previous scenario and edit response
  And For Response Approval Scenario Reject Response
  And For Response Approval Scenario Verify updated status as "Edits Required"
  And For Response Approval Scenario Verify updated Owner as "AAO Officer"
  And For Response Approval Scenario Verify Response Event is created with decision as "Reject" and owner as "AAO Supervisor"
  
  @AAOApproveResponse
  
  Scenario:Log out as AAO Supervisor and login as AAO Officer.Resubmit the response
  
  When For Response Approval Scenario user signs in as "AAO Officer"
  Then For Response Approval Scenario access the service item  in the previous scenario and edit response
  And For Response Approval Scenario resubmit the response
  And For Response Approval Scenario Verify  new status is updated as "Under Review"
  And For Response Approval Scenario Verify  new Owner is updated  as "AAO Supervisors"
  And For Response Approval Scenario Verify "2" Response versions are created
  
  @AAOApproveResponse
  Scenario:Logout as AAO Officer and login as AAO Supervisor.Approve the response
  
   When For Response Approval Scenario user logs in as "AAO Supervisor" again
  Then For Response Approval Scenario access the service item created in the previous scenario and edit resubmitted response
  And For Response Approval Scenario Approve Response
  And For Response Approval Scenario Verify changed status as "Under Review"
  And For Response Approval Scenario Verify changed Owner as "AAO File Clerks"
   And For Response Approval Scenario Verify another Response Event is created with decision as "Approve" and owner as "AAO Supervisor"
   
   @AAOApproveResponse
   
   Scenario:Logout as AAO Supervisor and login as AAO File Clerk.Approve the response
   
   When For Response Approval Scenario user signed in as "AAO File Clerk" 
  Then For Response Approval Scenario access the service item created in the previous scenario and edit resubmitted response by AAO Supervisor
  And For Response Approval Scenario Approve the resubmitted Response
  And For Response Approval Scenario Verify status is updated to "Approved"
  And For Response Approval Scenario Verify  Owner is updated to "AAO Officer"
   And For Response Approval Scenario Verify new Response Event is created with decision as "Approve" and owner as "AAO File Clerk"
  
  @AAOApproveResponse

Scenario: Log out and Close the browser
Then For Response Approval Scenario log out 
And For Response Approval Scenario Stop Report Generation for current scenario AAO End To End
And For Response Approval Scenario close the browser

  
  
 
 
 