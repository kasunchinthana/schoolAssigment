package com.nk.school.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nk.school.elearning.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	Comment findByFeedbackIdAndQuestionId(Integer feedbackId,Integer questionId);
}
