Feature: Verifying the AAO End to End scenario for Response Approval process for Quality Percentgae set to 0%
 @AAOAutoApproveResponse
  Scenario: Registered user is logged in as AAO SuperUser and creates a service Item.Set QC percentage of AAO Officer to 0.
  
Given For Auto Response Approval Scenario set the initial conditions
When For Auto Response Approval Scenario  Registered user is logged in as "AAO SuperUser"
Then For Auto Response Approval Scenario Superuser creates an application with Filetype as "Receipt Number"
And For Auto Response Approval Scenario clicks to create "AAO Service Item" from application detail
And For Auto Response Approval Scenario creates serviceitem with the following data
 |Item Type|Office|Officer Comments|Supervisor Comments|Supervisor|Editor|Contact|Account|Business|Attorney|Internal Contact|      
 |Appeal  | AAO|test officer comments|test supervisor comments|AAO SuperUser|AAO SuperUser|EDVARD EDOUARD|EDVARD EDOUARD|test business|test attorney|AAO SuperUser|
 
 And For Auto Response Approval Scenario sets qa percentage of officer to "0"
 
  @AAOAutoApproveResponse
 Scenario: Log out as super user and login as AAO Officer.Create a new response for the service item created in the previous scenario.
 
 When For Auto Response Approval Scenario user is logged in as "AAO Officer"
 Then For Auto Response Approval Scenario create a new response for the service item
 And For Auto Response Approval Scenario Verify Status as "Under Review"
 And For Auto Response Approval Scenario Verify Owner as "AAO File Clerks"
 And For Auto Response Approval Scenario Verify "1" Response version is created
 
   @AAOAutoApproveResponse
   
   Scenario:Logout as AAO Supervisor and login as AAO File Clerk.Approve the response
   
   When For Auto Response Approval Scenario user signed in as "AAO File Clerk" 
  Then For Auto Response Approval Scenario access the service item created in the previous scenario and edit response submitted by AAO Officer
  And For Auto Response Approval Scenario Approve the submitted Response
  And For Auto Response Approval Scenario Verify status is updated to "Approved"
  And For Auto Response Approval Scenario Verify  Owner is updated to "AAO Officer"
   And For Auto Response Approval Scenario Verify new Response Event is created with decision as "Approve" and owner as "AAO File Clerk"
   
  
  
  @AAOAutoApproveResponse

Scenario: Log out and Close the browser
Then For Auto Response Approval Scenario log out 
And For Auto Response Approval Scenario Stop Report Generation for current scenario AAO End To End
And For Auto Response Approval Scenario close the browser

  
  