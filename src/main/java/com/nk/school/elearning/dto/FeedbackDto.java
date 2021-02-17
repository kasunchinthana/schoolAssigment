package com.nk.school.elearning.dto;

import lombok.Data;

@Data
public class FeedbackDto {
	
	private Integer feedbackId;
	private Integer studentId;
	private Integer assignmentId;
	private String feedback;

}
