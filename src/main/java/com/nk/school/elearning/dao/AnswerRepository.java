package com.nk.school.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nk.school.elearning.model.Answer;
import com.nk.school.elearning.model.Submission;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	
	Answer findBySubmissionAndQuestionId(Submission submission,Integer questionId);
	

}
