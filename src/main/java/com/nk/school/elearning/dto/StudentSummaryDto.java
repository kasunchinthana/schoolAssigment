package com.nk.school.elearning.dto;

import lombok.Data;

@Data
public class StudentSummaryDto {
	
	private Integer studentId;
	private String firstName;
	private String lastName;
	private Integer grade;
	private String userName;

}
