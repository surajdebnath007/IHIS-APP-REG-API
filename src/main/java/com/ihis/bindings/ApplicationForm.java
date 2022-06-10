package com.ihis.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ApplicationForm {

	private String fullName;

	private String emailId;

	private long mobileNo;

	private String gender;

	private LocalDate dob;

	private long ssn;

}
