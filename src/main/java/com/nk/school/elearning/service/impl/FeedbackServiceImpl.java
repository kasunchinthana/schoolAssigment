package com.nk.school.elearning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.school.elearning.dao.FeedbackRepository;
import com.nk.school.elearning.dao.TeacherRepository;
import com.nk.school.elearning.dto.AnswerDto;
import com.nk.school.elearning.dto.AssignmentDto;
import com.nk.school.elearning.dto.FeedbackDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;
import com.nk.school.elearning.mapping.response.Status;
import com.nk.school.elearning.model.Answer;
import com.nk.school.elearning.model.Assignment;
import com.nk.school.elearning.model.Feedback;
import com.nk.school.elearning.model.Submission;
import com.nk.school.elearning.model.Teacher;
import com.nk.school.elearning.service.FeedbackService;
import com.nk.school.elearning.transformer.FeedbackDetailsTransformer;
import com.nk.school.elearning.util.Constants;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	FeedbackDetailsTransformer feedbackDetailsTransformer;
	
	@Autowired
	TeacherRepository teacherRepository;

	@Override
	public ResponsePack<FeedbackDto> createFeedback(Integer teacherId, Integer studentId, Integer assignmentId,
			RequestWrapper<FeedbackDto> request) throws Exception {
		ResponsePack<FeedbackDto> res = new ResponsePack<FeedbackDto>();
		Status status = new Status();
		Feedback savedFeedback = null;
		FeedbackDto feedbackDto =  new FeedbackDto();
		try {
			if (request.getPayload() != null){
				
				Optional<Teacher> teacher = teacherRepository.findById(teacherId);
				Feedback feedbackDb = feedbackDetailsTransformer.transformToDao(teacher.get(),studentId,
						assignmentId,request.getPayload());	
				savedFeedback = feedbackRepository.save(feedbackDb);
				feedbackDto = feedbackDetailsTransformer.transform(savedFeedback);
				
			} else {
				status.setCode(Constants.NOTFOUND);
				res.setStatus(status);
			}
			
			if (savedFeedback.getFeedbackId() != null) {
				status.setCode(Constants.CREATED);
				res.setStatus(status);
				res.setData(feedbackDto);
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
	public ResponsePack<FeedbackDto> updateFeedback(Integer teacherId, Integer studentId, Integer assignmentId,
			RequestWrapper<FeedbackDto> request) {
		ResponsePack<FeedbackDto> res = new ResponsePack<FeedbackDto>();
		Status status = new Status();
		Feedback savedFeedback = null;
		FeedbackDto feedbackDto =  new FeedbackDto();
		try {
			if (request.getPayload() != null){
				Optional<Teacher> teacher = teacherRepository.findById(teacherId);
				Feedback feedbackDb = feedbackDetailsTransformer.transformToDao(teacher.get(),studentId,
						assignmentId,request.getPayload());
				if(feedbackDb != null) {					
					//Answer answerDb = answerDetailsTransformer.transformToDao(answer,questionId,request.getPayload());
					if(null != request.getPayload().getFeedback()) {
						feedbackDb.setFeedback(request.getPayload().getFeedback());
					}
					savedFeedback = feedbackRepository.save(feedbackDb);
					feedbackDto = feedbackDetailsTransformer.transform(savedFeedback);
					//questionRepository.saveAll(questionListDb);
				}else {
					status.setCode(Constants.NOTFOUND);
					res.setStatus(status);
				}
				
				
			} else {
				status.setCode(Constants.NOTFOUND);
				res.setStatus(status);
			}
			
			if (savedFeedback.getFeedbackId() != null) {
				status.setCode(Constants.CREATED);
				res.setStatus(status);
				res.setData(feedbackDto);
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
	public ResponsePack<FeedbackDto> getFeedbackByStudentIdAndAssignmentId(Integer studentId, Integer assignmentId) {
		ResponsePack<FeedbackDto> res = new ResponsePack<FeedbackDto>();
		try {
			Feedback feedbackFromDB = null;
			Feedback feedback = feedbackRepository.findByStudentIdAndAssignmentId(studentId, assignmentId);
			if (feedback != null) {
				feedbackFromDB = feedback;
			} else {
				feedbackFromDB = null;
			}
			FeedbackDto assignmentDto = feedbackDetailsTransformer.transform(feedbackFromDB);
			//List<AssignmentDto> dtoList = new ArrayList<AssignmentDto>();
			//dtoList.add(assignmentDto);
			res.setData(assignmentDto);

			return res;
		} catch (Exception ex) {
			throw ex;
		}
	}

}
