package com.nk.school.elearning.transformer;

import org.springframework.stereotype.Service;

import com.nk.school.elearning.dto.AnswerDto;
import com.nk.school.elearning.dto.SubmissionDto;
import com.nk.school.elearning.model.Answer;
import com.nk.school.elearning.model.Submission;

@Service
public class AnswerDetailsTransformer {

	public Answer transformToDao(Submission submission,Integer questionId, AnswerDto answerDto) {
		Answer answer = new Answer();
		answer.setAnswer(answerDto.getAnswer());
		answer.setQuestionId(questionId);
		answer.setSubmission(submission);
//		Grade grade = new Grade();
//		grade.setGradeId(studentDto.getSummary().getGrade());
//		student.setGrade(grade);
		return answer;
	}

	public AnswerDto transform(Answer savedAnswer) {
		AnswerDto answerDto = new AnswerDto();
		answerDto.setAnswer(savedAnswer.getAnswer());
		answerDto.setAnswerId(savedAnswer.getAnswerId());
		answerDto.setQuestionId(savedAnswer.getQuestionId());
		return answerDto;
	}	

}
