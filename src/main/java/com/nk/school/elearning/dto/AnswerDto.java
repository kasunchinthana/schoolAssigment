package com.nk.school.elearning.dto;

import lombok.Data;

@Data
public class AnswerDto {
	private Integer answerId;
	private Integer questionId;
	private String answer;
	private Integer studentId;
	private Integer assignmentId;
}
