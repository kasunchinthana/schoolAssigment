package com.nk.school.elearning.dto;


import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class AssignmentDto {
	
	AssignmentSummaryDto summary;
	
	AssignmentDetailDto detail;
	
	List<QuestionDto> questions;
	
}
