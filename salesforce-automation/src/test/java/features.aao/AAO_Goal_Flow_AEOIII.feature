Feature: Verifying the AAO End to End scenario for Monthly Goals Flow for officers with title AEO II.
 
 @AAOGoals
  Scenario: Registered user is logged in as AAO SuperUser and creates a service Item.
  
Given For Monthly Goals Flow  set the initial conditions
When For Monthly Goals Flow  Registered user is logged in as "AAO SuperUser"
Then For Monthly Goals Flow open Monthly Goals in a separate tab  for "AAO File Clerk"
And For Monthly Goals Flow set  Title for "AAO File Clerk" as "AEO II" and Form weight for "I-130" as "3.00"
And For Monthly Goals Flow update Title for "AAO Officer" as "AEO II"
And For Monthly Goals Flow Superuser creates an application with Filetype as "Receipt Number"
And For Monthly Goals Flow clicks to create "AAO Service Item" from application detail
And For Monthly Goals Flow creates serviceitem with the following data
|Item Type|Office|Officer Comments|Supervisor Comments|Supervisor|Editor|Contact|Account|Business|Attorney|Internal Contact|      
 |Appeal  | AAO|test officer comments|test supervisor comments|AAO SuperUser|AAO SuperUser|EDVARD EDOUARD|EDVARD EDOUARD|test business|test attorney||
 
 @AAOGoals
 Scenario: Add internal contact and issue the SI.Verify the aao completion weight is updated in the selected internal contact's monthly goals record.
 
Then For Monthly Goals Flow for the service item select internal contact as "AAO File Clerk" and final decision as "Admin Closed"
 Then For Monthly Goals Flow update Responsible Party to "Issued Decisions - Queue" and save
 
 And For Monthly Goals Flow verify the monthly goals record is updated for "AAO File Clerk"
 
 @AAOGoals

Scenario:Update Completion weight and verify it is updated

 Then update the completion weight to "5.00"
 And verify the weight is updated in the monthly goals record for "AAO File Clerk"
 
 
@AAOGoals
Scenario: Change the issued date and verify the actual is updated in the internal contact's goals record.

Then update the issued date
And Verify the actual is updated in the goals record for the contact "AAO File Clerk"

 @AAOGoals 
 Scenario:Change the internal contact and verify that the actual is updated for the new contact and points are taken away from the existing internal contact
 
 Then For Monthly Goals Flow change the internal contact to "AAO Officer"
 And For Monthly Goals Flow verify that the actual is updated to "0" for "AAO File Clerk"
 And For Monthly Goals Flow Verify the monthly goals actual  is updated for that month for "AAO Officer"
 


@AAOGoals

Scenario:Verify that the officer who completed triage geta 
@AAOGoals

Scenario:Change the record type of Service Item from AAO Closed to AAO Service Item.Verify that the points are taken away for the contact and the actual becomes 0

Then For Monthly Goals Flow change the record type to "AAO Service Item"
And For Monthly Goals Flow verify that actual is updated  for "AAO Officer" to "0"



@AAOGoals

Scenario: Log out and Close the browser
Then For Monthly Goals Flow log out 
And For Monthly Goals Flow  Stop Report Generation for current scenario AAO End To End
And For Monthly Goals Flow close the browser


 