package com.nk.school.elearning.transformer;

import org.springframework.stereotype.Service;

import com.nk.school.elearning.dto.FeedbackDto;
import com.nk.school.elearning.dto.TeacherDto;
import com.nk.school.elearning.dto.TeacherSummaryDto;
import com.nk.school.elearning.model.Feedback;
import com.nk.school.elearning.model.Teacher;

@Service
public class FeedbackDetailsTransformer {

	
	public FeedbackDto transform(Feedback savedFeedback) {
	
		FeedbackDto feedbackDto = new FeedbackDto();
		feedbackDto.setAssignmentId(savedFeedback.getAssignmentId());
		feedbackDto.setFeedback(savedFeedback.getFeedback());
		feedbackDto.setFeedbackId(savedFeedback.getFeedbackId());
		feedbackDto.setStudentId(savedFeedback.getStudentId());
		return feedbackDto;
	}

	public Feedback transformToDao(Teacher teacher, Integer studentId, Integer assignmentId, FeedbackDto feedbackDto) {
		Feedback feedback = new Feedback();
		
		feedback.setAssignmentId(assignmentId);
		feedback.setFeedback(feedbackDto.getFeedback());
		feedback.setStudentId(studentId);
		feedback.setTeacher(teacher);

		return feedback;
	}

}
