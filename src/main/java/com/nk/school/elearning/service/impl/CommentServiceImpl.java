package com.nk.school.elearning.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.school.elearning.dao.CommentRepository;
import com.nk.school.elearning.dto.CommentDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;
import com.nk.school.elearning.mapping.response.Status;
import com.nk.school.elearning.model.Comment;
import com.nk.school.elearning.service.CommentService;
import com.nk.school.elearning.transformer.CommentDetailsTransformer;
import com.nk.school.elearning.util.Constants;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	CommentDetailsTransformer commentDetailsTransformer;

	@Override
	public ResponsePack<CommentDto> createComment(Integer teacherId, Integer feedbackId, Integer questionId,
			RequestWrapper<CommentDto> request) throws Exception {
		ResponsePack<CommentDto> res = new ResponsePack<CommentDto>();
		Status status = new Status();
		Comment savedComment = null;
		CommentDto commentDto =  new CommentDto();
		try {
			if (request.getPayload() != null){
				
				Comment commentDb = commentDetailsTransformer.transformToDao(teacherId,feedbackId,questionId,
						request.getPayload());	
				savedComment = commentRepository.save(commentDb);
				commentDto = commentDetailsTransformer.transform(savedComment);
				
			} else {
				status.setCode(Constants.NOTFOUND);
				res.setStatus(status);
			}
			
			if (savedComment.getCommentId() != null) {
				status.setCode(Constants.CREATED);
				res.setStatus(status);
				res.setData(commentDto);
				return res;
			}else {
				status.setCode(Constants.ERROR);
				res.setStatus(status);
				return res;
			}
		}catch(Exception ex) {
			throw ex;
		}
	}

	@Override
	public ResponsePack<CommentDto> updateComment(Integer teacherId, Integer feedbackId, Integer questionId,
			RequestWrapper<CommentDto> request) throws Exception {
		ResponsePack<CommentDto> res = new ResponsePack<CommentDto>();
		Status status = new Status();
		Comment savedComment = null;
		CommentDto commentDto =  new CommentDto();
		try {
			if (request.getPayload() != null){
				
				Comment commentfrmDB = commentRepository.findByFeedbackIdAndQuestionId(feedbackId,questionId);
				if(commentfrmDB != null) {
					
					commentfrmDB.setComment(request.getPayload().getComment());
				}
				
//				Comment commentDb = commentDetailsTransformer.transformToDao(teacherId,feedbackId,questionId,
//						request.getPayload());	
				
				savedComment = commentRepository.save(commentfrmDB);
				commentDto = commentDetailsTransformer.transform(savedComment);
				
			} else {
				status.setCode(Constants.NOTFOUND);
				res.setStatus(status);
			}
			
			if (savedComment.getCommentId() != null) {
				status.setCode(Constants.CREATED);
				res.setStatus(status);
				res.setData(commentDto);
				return res;
			}else {
				status.setCode(Constants.ERROR);
				res.setStatus(status);
				return res;
			}
		}catch(Exception ex) {
			throw ex;
		}
	}

}
