Feature: Verifying the AAO End to End scenario for Monthly Goals Flow for officers with title AO.

 @AAOGoals
  Scenario: Registered user is logged in as AAO SuperUser and creates a service Item.
  
#Given For Monthly Goals Flow  set the initial conditions
When For Monthly Goals Flow  Registered user is logged in as "AAO SuperUser"
Then For Monthly Goals Flow open Monthly Goals in a separate tab  for "AAO File Clerk"
And For Monthly Goals Flow set  Title for "AAO File Clerk" as "AO" and Form weight for "I-130" as "3.00"
And For Monthly Goals Flow update Title for "AAO Officer" as "AO"
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
 And For Monthly Goals Flow Close Monthly Goals tab
 
@AAOGoals

Scenario:Select an officer for triage completed by.Verify that the officer gets 1 point credit for the current month

Then For Monthly Goals Flow Open Monthly Goals tab in separate tab
And For Monthly Goals Flow Select "Andrew Bennett" for triage completed by

And For Monthly Goals Flow verify that  triage officer "Andrew Bennett" gets "1" point for current month
 And For Monthly Goals Flow Close Monthly Goals tab
 
 @AAOGoals

Scenario: Change the triage completed on date.Verify that the points are updated for that month

Then For Monthly Goals Flow Open Monthly Goals tab in separate tab
Then For Monthly Goals Flow Change the triage completed on date
And For Monthly Goals flow  Verify that previous points "1" are updated for "Andrew Bennett" according to the triage completed date.
 And For Monthly Goals Flow Close Monthly Goals tab
 
  
@AAOGoals

Scenario:Change the officer for triage completed by and verify that the updated officer gets 1 point credit and the points are 0 for the existing officer

Then For Monthly Goals Flow Open Monthly Goals tab in separate tab
And For Monthly Goals Flow change the officer for triage completed by to "Khara Tapley"

And For Monthly Goals Flow points are "0" for the existing triage officer "Andrew Bennett"
And For Monthly Goals Flow verify that the new triage officer "Khara Tapley" gets "1" point 
 And For Monthly Goals Flow Close Monthly Goals tab
  


@AAOGoals

Scenario:Select an officer for BCU checks Completed By.Verify that the officer gets 1 point credit for that month

Then For Monthly Goals Flow Open Monthly Goals tab in separate tab
Then For Monthly Goals Flow Select officer for BCU Checks Completed by to "Annie Tissdale"
And For Monthly Goals Flow verify that  "Annie Tissdale" gets "1" point for current month
 And For Monthly Goals Flow Close Monthly Goals tab
 
 
 @AAOGoals

Scenario: Change the BCU Checks  completed on date.Verify that the points are updated for that month
Then For Monthly Goals Flow Open Monthly Goals tab in separate tab
Then For Monthly Goals Flow Change the BCU Checks completed on date
And For Monthly Goals flow  Verify that previous points "1" are updated for "Annie Tissdale" according to the bcu completed date.
And For Monthly Goals Flow Close Monthly Goals tab

@AAOGoals

Scenario:Change the officer for BCU Checks completed by and verify that the updated officer gets 1 point credit and the points are 0 for the existing officer
Then For Monthly Goals Flow Open Monthly Goals tab in separate tab
Then For Monthly Goals Flow change the officer for bcu checks completed by to "Ben Laszewski"
 
And For Monthly Goals Flow verify points are "0" for "Annie Tissdale"
And For Monthly Goals Flow verify that new bcu offficer "Ben Laszewski" gets "1" point
And For Monthly Goals Flow Close Monthly Goals tab




@AAOGoals

Scenario: Select none for Triage Completed By and BCU Checks Completed By.Verify that points are 0 for the existing triage and bcu officers
Then For Monthly Goals Flow Open Monthly Goals tab in separate tab
Then For Monthly Goals Flow select "None" for Triage Completed By and select "None" for BCU Checks Completed By

And For Monthly Goals flow  Verify that points are "0" for current bcu officer "Ben Laszewski"
And For Monthly Goals flow  Verify that points are "0" for current triage officer "Khara Tapley"
And For Monthly Goals Flow Close Monthly Goals tab



@AAOGoals

Scenario:Change the record type of Service Item from AAO Closed to AAO Service Item.Verify that the points are taken away for the contact and the actual becomes 0
Then For Monthly Goals Flow Open Monthly Goals tab in separate tab
Then For Monthly Goals Flow change the record type to "AAO Service Item"
And For Monthly Goals Flow verify that actual is updated  for "AAO Officer" to "0"

@AAOGoals

Scenario:Login as any officer who is not a supervisor.Navigate to Monthly Goals and verify that there is only one record and there are no other records and fields are non editable

Then For Monthly Goals Flow login as "AAO File Clerk"
And For Monthly Goals navigate to monthly goals
And For Monthly Goals Flow Verify there is only one monthly goals record for "AAO File Clerk"
And For Monthly Goals Flow verify that production expectations  are non editable
And Verify that Personal Goals and Annual Goal is editable


@AAOGoals

Scenario: Log out and Close the browser
Then For Monthly Goals Flow log out 
And For Monthly Goals Flow  Stop Report Generation for current scenario AAO End To End
And For Monthly Goals Flow close the browser


 