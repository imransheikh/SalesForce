package com.salesforcetest.shared;

public class Constants {
	public static final String reporter_email = "zabid@acumensolutions.com";

	public static final String reporter_username = "zabid";

	public static final String reporter_password = "Zzz1988.";

	public static final String reporter_account_url = "https://www.gmail.com";

	public static final String attachment_url = "C:\\Users\\Zaim3\\eclipse-workspace\\Salesforce Automation\\files\\SORN.pdf";
	
	public static final String attachment_url_reply = "C:\\Users\\Zaim3\\eclipse-workspace\\Salesforce Automation\\files\\privacy-pia-uscis-09-a-cis-april-2017.pdf";

	// Incident properties

	public static final String incident_report_email = "pvy.incidents@gmail.com";

	public static final String incident_msg = "This is an incident";

	public static final String incident_subject = "New Incident Test " + System.currentTimeMillis();

	// Complience properties

	public static final String compliance_report_email = "pvy.compliance@gmail.com";

	public static final String compliance_msg = "This is an compliance report";

	public static final String compliance_subject = "New Compliance Test " + System.currentTimeMillis();

	// End

	public static final String salesforce_url = "https://uscis-2--uat.cs32.my.salesforce.com/";

	public static final String salesforce_username = "pstaff@test.com.uat";

	public static final String salesforce_password = "acumentest1";

	public static final String email_subject= "This is test email";

	public static boolean isWindows() {
		String os = System.getProperty("os.name");
		if (os.contains("Windows") || os.contains("WINDOWS") || os.contains("windows")) {
			return true;
		}
		return false;
	}

}
