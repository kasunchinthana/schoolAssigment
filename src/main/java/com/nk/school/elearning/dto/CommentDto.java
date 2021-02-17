package com.nk.school.elearning.dto;

import lombok.Data;

@Data
public class CommentDto {

	private Integer commentId;
	private Integer studentId;
	private Integer assignmentId;
	private Integer feedbackId;
	private Integer questionId;
	private String comment;
}
