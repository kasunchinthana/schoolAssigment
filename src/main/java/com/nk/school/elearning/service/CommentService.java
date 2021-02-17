package com.nk.school.elearning.service;

import com.nk.school.elearning.dto.CommentDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;

public interface CommentService {

	ResponsePack<CommentDto> createComment(Integer teacherId, Integer feedbackId, Integer questionId,
			RequestWrapper<CommentDto> request) throws Exception;

	ResponsePack<CommentDto> updateComment(Integer teacherId, Integer feedbackId, Integer questionId,
			RequestWrapper<CommentDto> request) throws Exception;

}
