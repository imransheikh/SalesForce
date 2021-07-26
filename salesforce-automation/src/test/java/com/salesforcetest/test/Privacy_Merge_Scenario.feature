Feature: Verifying the Merge scenario for Privacy
  Verifying the Merge scenario for Privacy App with three new service item
  @PrivacyMerge
  Scenario: Log in and verify the default profile and Log in as Privacy Staff Two
  	Given Launching the app and logging in as Privacy Staff with Task for merge scenario
  	Then Internal User logs in as Privacy Staff 2 for Privacy
  @PrivacyMerge
  Scenario: Fetch three New status Service Item
    Then Identify the 3 new Service Item for Privacy merge scenario
  @PrivacyMerge
  Scenario: Create task for 1st two Service item
    Then Upload file and create task for first Service item
    Then Upload file and create task for second Service item
  @PrivacyMerge
  Scenario: Merge and validate the status
    Then Merge the first two si to the third one and validate the SI change to Archive
  @PrivacyMerge
  Scenario: Reply to service item related mail and verify the status
   Then Privacy Stop Report Generation for Merge validation
   Then Privacy Close the browser for Merge Validation