package com.salesforcetest.shared;

public class Reporter {
	private String email;

	private String userName;

	private String password;

	private String accountUrl;

	private Object type;

	private String reportingEmail;

	public Reporter(String email, String userName, String password, String accountUrl, Object type,
			String reportingEmail) {
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.accountUrl = accountUrl;
		this.type = type;
		this.reportingEmail = reportingEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountUrl() {
		return accountUrl;
	}

	public void setAccountUrl(String accountUrl) {
		this.accountUrl = accountUrl;
	}

	public Object getType() {
		return type;
	}

	public void setType(Object type) {
		this.type = type;
	}

	public String getReportingEmail() {
		return reportingEmail;
	}

	public void setReportingEmail(String reportingEmail) {
		this.reportingEmail = reportingEmail;
	}

}
