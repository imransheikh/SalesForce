Feature: Verify only AAO Superuser can change AAO Closed Service item to Open Service item.ISO-14057

@AAOCloseToOpen

Scenario:Registered user is logged in as AAO SuperUser and creates  service item.Issue it

Given For AAO Close To Open Scenario set the initial conditions
When For AAO Close To Open scenario  Registered user is logged in as "AAO SuperUser"
Then For AAO Close To Open scenario Superuser creates an application with Filetype as "Receipt Number"
And For AAO Close To Open scenario clicks to create "AAO Service Item" from application detail
And For AAO Close To Open scenario creates serviceitem with the following data
 |Item Type|Officer Comments|Supervisor Comments|Supervisor|Editor|Contact|Account|Business|Attorney|Internal Contact|      
 |Appeal  | AAO|test officer comments|test supervisor comments|AAO SuperUser|AAO SuperUser|EDVARD EDOUARD|EDVARD EDOUARD|test business|test attorney|AAO SuperUser|
 
 
 
 Then For AAO Close To Open Scenario update Final Decision to "Admin Closed"
 And For AAO Close To Open Scenario update responsible party to "Issued Decisions - Queue"
Then For AAO Close To Open Scenario verify status and milestone is updated to "Issued"

@AAOCloseToOpen

Scenario:Change the record type of Service Item from AAO Closed Service Item to AAO Service Item.Make updates on service item edit page and Verify no error is produced.

Then For AAO Close To Open Scenario change the record type to "AAO Service Item"
And For AAO Close To Open Scenario  update status to "Pending" on the Service Item Edit Page
And For AAO Close To Open Scenario update Responsible Party to "Triage - Queue" on the Service Item Edit page and click save
Then For AAO Close To Open Scenario issue the Service item again by updating responsible party to "Issued Decisions - Queue"

@AAOCloseToOpen

Scenario:Change the record type from AAO Closed Service Item to File Request.Update status and save.Verify no error is produced.

Then For AAO Close To Open Scenario update the record type to "File Request"
And For AAO Close To Open Scenario  update the status to "Completed" on the Service Item Edit Page
And For AAO Close To Open Scenario verify status as "Completed"

@AAOCloseToOpen
Scenario:Logoff and login as AAO File Clerk.Create Service Item and issue it.

Then For AAO Close To Open Scenario Logoff and login as "AAO File Clerk"
Then For AAO Close To Open scenario File Clerk creates an application with Filetype as "Receipt Number"
And For AAO Close To Open scenario File Clerk clicks to create "AAO Service Item" from application detail
And For AAO Close To Open scenario File Clerk creates serviceitem with the following data
 |Item Type|Office|Officer Comments|Supervisor Comments|Supervisor|Editor|Contact|Account|Business|Attorney|Internal Contact|      
 |Appeal  | AAO|test officer comments|test supervisor comments|AAO SuperUser|AAO SuperUser|EDVARD EDOUARD|EDVARD EDOUARD|test business|test attorney||
 
 
 Then For AAO Close To Open Scenario FileClerk update Final Decision to "Admin Closed"
 And For AAO Close To Open Scenario  FileClerk update responsible party to "Issued Decisions - Queue"
Then For AAO Close To Open Scenario FileClerk verify status and milestone is updated to "Issued"

@AAOCloseToOpen

Scenario:Change the record type of Service Item from AAO Closed Service Item to AAO Service Item.Make updates on service item edit page and Verify  error is produced.

Then For AAO Close To Open Scenario FileClerk change the record type to "AAO Service Item"
And For AAO Close To Open Scenario FileClerk update status to "Pending" on the Service Item Edit Page
And For AAO Close To Open Scenario FileClerk update Responsible Party to "Triage - Queue" on the Service Item Edit page and click save
Then For AAO Close To Open Scenario error message is displayed and go to the service item detail page by clicking cancel

@AAOCloseToOpen

Scenario:Change the record type from AAO Closed Service Item to File Request.Update status and save.Verify error is produced.

Then For AAO Close To Open Scenario FileClerk update the record type to "File Request"
And For AAO Close To Open Scenario FileClerk update the status to "Completed" on the Service Item Edit Page
And For AAO Close To Open Scenario error message is displayed

@AAOCloseToOpen
Scenario: Log out and Close the browser
Then For AAO Close To Open Scenario log out 
And For Auto Close To Open Scenario Stop Report Generation for current scenario AAO End To End
And For Auto Close To Open Scenario close the browser



