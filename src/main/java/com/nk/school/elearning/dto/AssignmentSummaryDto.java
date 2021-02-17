package com.nk.school.elearning.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AssignmentSummaryDto {

	private Integer assingnmentId;
	private String name;
	private String status;
	private LocalDateTime devDate;
	private Integer totalMarks;
	private Integer cutoffMarks;
	private Integer duration;
	private String description;
	private String courseCode;
	private String courseName;
	private Integer teacherId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
