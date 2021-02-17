package com.nk.school.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nk.school.elearning.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
	
	Feedback findByStudentIdAndAssignmentId(Integer studentId,Integer assignmentId);
	
}
