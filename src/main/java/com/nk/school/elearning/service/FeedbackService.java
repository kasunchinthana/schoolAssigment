package com.nk.school.elearning.service;

import com.nk.school.elearning.dto.FeedbackDto;
import com.nk.school.elearning.dto.SubmissionDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;

public interface FeedbackService {

	ResponsePack<FeedbackDto> createFeedback(Integer teacherId, Integer studentId, Integer assignmentId,
			RequestWrapper<FeedbackDto> request) throws Exception;

	ResponsePack<FeedbackDto> updateFeedback(Integer teacherId, Integer studentId, Integer assignmentId,
			RequestWrapper<FeedbackDto> request);

	ResponsePack<FeedbackDto> getFeedbackByStudentIdAndAssignmentId(Integer studentId, Integer assignmentId);

}
