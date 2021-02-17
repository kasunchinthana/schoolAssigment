package com.nk.school.elearning.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.school.elearning.dao.AssignmentRepository;
import com.nk.school.elearning.dao.QuestionRepository;
import com.nk.school.elearning.dto.QuestionDto;
import com.nk.school.elearning.dto.QuestionListDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;
import com.nk.school.elearning.mapping.response.Status;
import com.nk.school.elearning.model.Assignment;
import com.nk.school.elearning.model.Question;
import com.nk.school.elearning.service.QuestionService;
import com.nk.school.elearning.transformer.QuestionDetailsTransformer;
import com.nk.school.elearning.util.Constants;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionDetailsTransformer questionDetailsTransformer;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AssignmentRepository assignmentRepository;

	@Override
	public ResponsePack<QuestionDto> createQuestion(Integer id,QuestionListDto questionList) {
		ResponsePack<QuestionDto> res = new ResponsePack<QuestionDto>();
		Status status = new Status();
		Assignment savedQuestion = null;
		try {
			if (questionList.getQuestions().size()>0){
				Optional<Assignment> assignment = assignmentRepository.findById(id);
				if(assignment.isPresent()) {
					List<Question> questionListDb = questionDetailsTransformer.transformToDaoList(assignment.get(),questionList);	
					//savedQuestion = questionRepository.save(questionDb);
					//assignment.get().setQuestion(questionListDb)
					Assignment updatedAssignment = assignment.get();
					updatedAssignment.setQuestion(questionListDb);
					savedQuestion = assignmentRepository.save(updatedAssignment);
					//questionRepository.saveAll(questionListDb);
				}else {
					status.setCode(Constants.NOTFOUND);
					res.setStatus(status);
				}
				
			} else {
				status.setCode(Constants.NOTFOUND);
				res.setStatus(status);
			}
			
			if (savedQuestion.getAssingnmentId() != null) {
				status.setCode(Constants.CREATED);
				res.setStatus(status);
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
	public ResponsePack<QuestionDto> updateQuestion(Integer id, RequestWrapper<QuestionDto> questionDto,Integer questionId) {
		ResponsePack<QuestionDto> res = new ResponsePack<QuestionDto>();
		Status status = new Status();
		Question savedQuestion = null;
		try {
			Question questionFromDB= null;
			Optional<Question> question = questionRepository.findById(questionId);
			if (question.isPresent()) {
				questionFromDB = question.get();
				 // get payload assignment obj here
				Optional<Assignment> assignment = assignmentRepository.findById(id);
				Question questionfromFront = questionDetailsTransformer.transformToDao(questionDto.getPayload()); 
				
				if(questionfromFront.getCorrectAnswer()!= null ) {
					questionFromDB.setCorrectAnswer(questionfromFront.getCorrectAnswer());
				}
				if(questionfromFront.getHint()!= null ) {
					questionFromDB.setHint(questionfromFront.getHint());
				}
				if(questionfromFront.getQuestion()!= null ) {
					
					if( questionfromFront.getChoice() != null) {
					//	questionfromFront.getChoice().stream().forEach();
					//	questionFromDB.getChoice().get(index)
					}
					
					questionFromDB.setQuestion(questionfromFront.getQuestion());
				}
				if(questionfromFront.getType()!= null ) {
					questionFromDB.setType(questionfromFront.getType());
				}		
				
				savedQuestion = questionRepository.save(questionFromDB);
				status.setCode(Constants.CREATED);
				res.setStatus(status);
				return res;
			} else {
				status.setCode(Constants.NOTFOUND);
				res.setStatus(status);
			}
			
			
		}catch(Exception ex) {
			throw ex;
		}
		return res;
	}

}
