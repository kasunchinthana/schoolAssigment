package com.nk.school.elearning.dto;


import java.util.List;

import com.nk.school.elearning.model.Choice;

import lombok.Data;

@Data
public class QuestionDto {
	
	private Integer questionId;
	private String question;
	private String type;
	private String correctAnswer;
	private String hint;
	private Integer assingnmentId;	
	private List<ChoiceDto> choices; 

}
