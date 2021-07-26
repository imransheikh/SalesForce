package com.salesforcetest.shared;

public class ExpectedErrorMessageList {

	public static final String SCENARIO_2_1_3_ERROR_MSG_1 = "";
	
	//Error message when try to change status from In Progress to unassigned - 3.4.1
	public static final String SCENARIO_3_4_1_ERROR_MSG = "The Status cannot be changed to \"Unassigned\".";
	
	public static final String SCENARIO_2_5_INCIDENT_REASON_ERROR_MSG = "Please populate the Reason field to indicate why the Incident was deemed a Non-Incident.";
	
	public static final String SCENARIO_2_5_INCIDENT_CLOSED_ERROR_MSG = "The Status cannot be \"Closed\" if the Type is \"Suspected Incident\".";
	
	public static final String SCENARIO_5_2_SORN_CLOSED_ERROR_MSG = "Please ensure that the OCC Approval Date, DHS Approval Date, and Federal Register Publish Date are popluated.";
	
	public static final String SCENARIO_14_3_PRIVACY_CLOSED_ERROR_MSG = "Please ensure that the OCC Approval Date, DHS Approval Date, and Federal Register Publish Date are populated.";
	
	public static final String SCENARIO_15_1_PTA_CLOSED_ERROR_MSG = "Please populate the Coverage Recommendation and Expiration Date before changing the Status to Closed.";
	
	public static final String SCENARIO_15_2_PIA_CLOSED_ERROR_MSG = "Please ensure that the OCC Approval Date and DHS Approval Date are populated.";
	
	
}
