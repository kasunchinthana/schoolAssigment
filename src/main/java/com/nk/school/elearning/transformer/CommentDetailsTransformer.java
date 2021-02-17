package com.nk.school.elearning.transformer;

import org.springframework.stereotype.Service;

import com.nk.school.elearning.dto.AnswerDto;
import com.nk.school.elearning.dto.CommentDto;
import com.nk.school.elearning.model.Answer;
import com.nk.school.elearning.model.Comment;

@Service
public class CommentDetailsTransformer {

	public Comment transformToDao(Integer teacherId, Integer feedbackId, Integer questionId, CommentDto payload) {
		Comment comment = new Comment();
		comment.setFeedbackId(feedbackId);
		comment.setQuestionId(questionId);
		comment.setComment(payload.getComment());
		return comment;
	}

	public CommentDto transform(Comment savedComment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setCommentId(savedComment.getCommentId());
		commentDto.setComment(savedComment.getComment());
		commentDto.setFeedbackId(savedComment.getFeedbackId());
		commentDto.setQuestionId(savedComment.getQuestionId());
		return commentDto;
	}

}
