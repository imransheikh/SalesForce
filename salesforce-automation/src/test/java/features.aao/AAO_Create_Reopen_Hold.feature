
Feature: Verifying the AAO End to End scenario for Service Item Creation,Creating a hold and removing the hold and reopening the service item
 @AAOEndToEnd
  Scenario: Registered user is logged in as AAO File Intake Clerk and Creates a service Item.
Given set the initial conditions
When Registered user is logged in as "AAO Intake File Clerk"
 And user creates an application with Filetype as "Receipt Number"
And clicks to create "AAO Service Item" from application detail
 Then verify Receipt number,Responsible Party and AAO Recd Date are AutoPopulated
 When user clicks save without filling the required fields
 Then verify error messages are displayed
 And user creates Service Item with the following data and saves Service Item
 |Item Type|Office|Officer Comments|Supervisor Comments|Supervisor|Editor|Contact|Account|Business|Attorney|Internal Contact|       
 |Appeal  | AAO|test officer comments|test supervisor comments|AAO SuperUser|AAO SuperUser|EDVARD EDOUARD|AAO - Administrative Appeals Office|test business|test attorney|    |
    
 
 @AAOEndToEnd
 Scenario: Verify the fields are autopopulated on the Service Item Detail Page.Update the Aao Recd Date on the Service Item Detail page and verify the corresponding fields are updated as required.
  Then verify the underlying benefit field displays underlying benefit value "I-102"
  And verify the ReceiptNumber,FileNumber are AutoPopulated
  And verify the AAO Received Date is autopopulated  and ARC Start Date is autopopulated 
  And verify status is "Pending" and Milestone is "Mail Room"
 And verify the tracking record is created with start milestone with the following data
 |StartMileStone|EndMileStone|StartArc|EndArc|DaysWorked|
 |Mail Room     |            |        |      |         0 |
   
 When user updates the AAO Recd Date to two days before today
 Then verify ARC start date is updated as two days before today
 And Verify AAo clock as "2"
And Verify Adjudication clock as "2"
 And Verify left bookend tracking record is updated with following data
 |StartMileStone|EndMileStone|StartArc|EndArc|DaysWorked|
 |Mail Room      |           |        |       |2|
Then verify start date is updated
   
  
 @AAOEndToEnd
 Scenario:Add a DOL hold,G-28 holds and verify the status is on hold.
  
 When user adds a Hold as "AFB National Security"
 Then verify error message is displayed and user is unable to add hold
 Then add a Dol Hold as type "DOL"
 And verify the callupdate is autopopulated and cannot be edited
 Then add a hold as type"DOL"
 And verify that you receive an error message
 Then  add another hold as type "G-28"
 And verify the status of si is updated with "On Hold"
 
 
 @AAOEndToEnd
 Scenario:Login as AAO Supervisor and remove DOL hold.Login as AAO File Clerk and remove G-28 hold
 When user logsin as "AAO Supervisor"
 And navigate to service item in the previous step
 Then remove the "DOL" hold
 And verify status updated to as "On Hold"
 Then login as "AAO Intake File Clerk"
 And go to the service item
 And update the final decision to "Admin Closed" and verify error message displayed
 
 Then update the  responsible party to "Issued Decisions - Queue"
 And verify an error message is displayed for responsible party
 And verify error is displayed for Final Decision
 Then  delete the "G-28" hold
 And Verify the  status is "Pending" 
 Then update Final Decision to "Admin Closed"
 And verify status as "Decision Recorded" and Milestone as "Decision Recorded"
 And verify the tracking record with the following data
|StartMileStone|
|Decision Recorded|
And verify service history is updated with "Decision Recorded" by "AAO Intake File Clerk"
 
 
 @AAOEndToEnd
 Scenario: Login as AAO Supervisor .Update final decision to Issued Decisisons queue and verify error message is displayed.
 
 When user is logged in as "AAO Supervisor"
 And search for the service item
And Verify new  status as "Decision Recorded" 
Then  responsible party is updated to "Issued Decisions - Queue"

Then verify error message is displayed

@AAOEndToEnd

Scenario: Login as AAO  Back-End File Clerk.Access the SI and update responsible party to Issued Decisions - Queue.Verify status is updated to Issued

When user logs in as "AAO Back-end File Clerk"
Then access the si item in the previous step
And update responsible party to "Issued Decisions - Queue"
Then verify status and milestone is updated to "Issued"
And verify tracking record is updated with the following data
|EndMileStone|Bookend|
|Issued      |Right|
And verify the service history is updated with "Issued" by "AAO Back-end File Clerk"
@AAOEndToEnd

Scenario: Log in as AAO intake file clerk.Access the si and reopen the si.Verify new service item number is generated
When user signsin as "AAO Intake File Clerk"
Then accessing the service item in the previous step
When user adds some text "testing" to Service Item Group Field
Then verify that text "testing" is added to the Service Item Group Field
And verify the label "Egregious Public Safety (EPS)"  in Adjudication Related List
And Reopen the service item
And verify the following
|AAOType|Motion|Status|ResponsibleParty|FinalDecision|
|Motion|Service Motion to Reopen|Pending|Mail Room - Queue|--None--|
When user updates AAO service Item Type to "Appeal"
Then verify error is displayed
Then update AAO Service item type to "Motion" and Motion type to "Service Motion to Reopen"
And verify error is displayed and record is not saved
When user updates responsible party to "Pre-Screen - Queue"
Then verify new Si is generated
And old SI is displayed as link in the parent serviceitem field

@AAOEndToEnd

Scenario:Create a service item as super user.Verify SuperUser,Branch Chief,FileClerk and Supervisor are able to add  and delete Multiple Filings Hold.

When  user  logged in as "AAO SuperUser"
Then   Superuser creates an application with Filetype as "Receipt Number"
And Superuser clicks to create "AAO Service Item" from application detail
And Superuser  creates serviceitem with the following data
|Item Type|Office|Officer Comments|Supervisor Comments|Supervisor|Editor|Contact|Account|Business|Attorney|Internal Contact|      
 |Appeal  | AAO|test officer comments|test supervisor comments|AAO SuperUser|AAO SuperUser|EDVARD EDOUARD|EDVARD EDOUARD|test business|test attorney|AAO SuperUser|
Then verify Superuser is able to create and delete "Multiple Filings" Hold
And  Verify following users are able to create and delete Multiple Filings hold
 
 |User|
 
 |AAO File Clerk|
  |AAO Supervisor|
 |AAO Employment Division Chief|
 

Then log out for current scenario AAO End To End
And  Stop Report Generation for current scenario AAO End To End
And close the browser for current scenario AAO End To End

 




 
 
 
 
 
  
   
   
    
    
      
    

  