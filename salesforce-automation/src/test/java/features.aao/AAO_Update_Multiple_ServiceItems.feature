Feature:Update Multiple Service Items that allows for the bulk update of AAO and AFB Service Items.ISO-13924

@AAOUpdateMultipleSi
Scenario:Registered user is logged in as AAO SuperUser and creates  service item

Given For Update Multiple Service Items scenario set the initial conditions
When For Update Multiple Service Items scenario  Registered user is logged in as "AAO SuperUser"
Then For Update Multiple Service Items scenario Superuser creates an application with Filetype as "Receipt Number"
And For Update Multiple Service Items scenario clicks to create "AAO Service Item" from application detail
And For Update Multiple Service Items scenario creates serviceitem with the following data
 
|Item Type|Office|Officer Comments|Supervisor Comments|Supervisor|Editor|Contact|Account|Business|Attorney|Internal Contact|      
 |Appeal  | AAO|test officer comments|test supervisor comments|AAO SuperUser|AAO SuperUser|EDVARD EDOUARD|EDVARD EDOUARD|test business|test attorney|AAO SuperUser|
 @AAOUpdateMultipleSi
 Scenario:Create AFB Service item for the service item.
 
 Then For Update Multiple Service Items scenario create AFB service item
 And For Update Multiple Service Items scenario verify AAO Total Time Clock field is displayed and  verify the value is "0"
 
 @AAOUpdateMultipleSi
 Scenario:On the Update Multiple Service Items Page,search by filenumber and verify AAO and AFB service items show up in the search results
 
 And For Update Multiple Service Items scenario navigate to Update Multiple Service Items screen
 And For Update Multiple Service Items scenario input filenumber and search
 Then For Update Multiple Service Items scenario verify AAO and AFB items show up in the search results
 
 @AAOUpdateMultipleSi
 
 Scenario:Close the AFB Service Item and search by filenumber on the Update Multiple Service items page.Verify that only AAO service item is returned
 
 Then For Update Multiple Service Items scenario click on AAO Service item from the search results
 And For Update Multiple Service Items scenario close the AFB Service item
 And For Update Multiple Service Items scenario navigate to Update Multiple Service Items page
 And For Update Multiple Service Items scenario search by filenumber
 Then For Update Multiple Service Items scenario verify AFB Service Item is not returned and AAO Service Item is returned
 
 
 @AAOUpdateMultipleSi
 Scenario: Log out and Close the browser
Then log out for Current scneario AAO Update Multiple Service Items
And  Stop Report Generation for current scenario AAO Update Multiple Service Items
And close the browser for current scenario AAO Update Multiple Service Items
 
 
  
