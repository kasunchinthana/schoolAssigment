package com.nk.school.elearning.service.impl;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.school.elearning.dao.AnswerRepository;
import com.nk.school.elearning.dao.StudentRepository;
import com.nk.school.elearning.dao.SubmissionRepository;
import com.nk.school.elearning.dto.AnswerDto;
import com.nk.school.elearning.dto.StudentDto;
import com.nk.school.elearning.dto.SubmissionDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;
import com.nk.school.elearning.mapping.response.Status;
import com.nk.school.elearning.model.Answer;
import com.nk.school.elearning.model.Assignment;
import com.nk.school.elearning.model.Question;
import com.nk.school.elearning.model.Student;
import com.nk.school.elearning.model.Submission;
import com.nk.school.elearning.service.StudentService;
import com.nk.school.elearning.transformer.AnswerDetailsTransformer;
import com.nk.school.elearning.transformer.StudentDetailsTransformer;
import com.nk.school.elearning.transformer.SubmissionDetailsTransformer;
import com.nk.school.elearning.util.Constants;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentDetailsTransformer studentDetailsTransformer;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SubmissionDetailsTransformer submissionDetailsTransformer;
	
	@Autowired
	SubmissionRepository submissionRepository;
	
	@Autowired
	AnswerDetailsTransformer answerDetailsTransformer;
	
	@Autowired
	AnswerRepository answerRepository;

	@Override
	public ResponsePack<StudentDto> createStudent(RequestWrapper<StudentDto> request) throws Exception {
		ResponsePack<StudentDto> res = new ResponsePack<StudentDto>();
		Status status = new Status();
		Student savedStudent = null;
		StudentDto studentDto =  new StudentDto();
		try {
			if (request.getPayload() != null){
				Student studentDb = studentDetailsTransformer.transformToDao(request.getPayload());	
				savedStudent = studentRepository.save(studentDb);
				studentDto = studentDetailsTransformer.transform(savedStudent);
				
			} else {
				status.setCode(Constants.NOTFOUND);
				res.setStatus(status);
			}
			
			if (savedStudent.getUserId() != null) {
				status.setCode(Constants.CREATED);
				res.setStatus(status);
				res.setData(studentDto);
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
	public ResponsePack<StudentDto> updateStudent(Integer studentId, RequestWrapper<StudentDto> studentDto)
			throws Exception {
		ResponsePack<StudentDto> res = new ResponsePack<StudentDto>();
		Status status = new Status();
		Student savedStudent = null;
		StudentDto studentSavedDto =  new StudentDto();
		try {
			Student studentFromDB= null;
			Optional<Student> student = studentRepository.findById(studentId);
			if (student.isPresent()) {
				studentFromDB = student.get();
				 // get payload assignment obj here
				Student studentfromFront = studentDetailsTransformer.transformToDao(studentDto.getPayload()); 
				
				if(studentfromFront.getFirstName()!= null ) {
					studentFromDB.setFirstName(studentfromFront.getFirstName());
				}
				if(studentfromFront.getLastName()!= null ) {
					studentFromDB.setLastName(studentfromFront.getLastName());
				}
				if(studentfromFront.getUsername()!=null) {
					studentFromDB.setUsername(studentfromFront.getUsername());
				}
//				if(studentfromFront.getGrade().getGradeName() != null ) {
//					studentfromFront.getGrade().setGradeName(studentfromFront.getGrade().getGradeName());
//					//studentFromDB.setGrade(studentfromFront.getGrade());
//				}								
				savedStudent = studentRepository.save(studentFromDB);
				studentSavedDto = studentDetailsTransformer.transform(savedStudent);
				status.setCode(Constants.CREATED);
				res.setStatus(status);
				res.setData(studentSavedDto);
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

	@Override
	public ResponsePack<SubmissionDto> createSubmission(Integer studentId,Integer assignmentId,RequestWrapper<SubmissionDto> request) throws Exception {
		ResponsePack<SubmissionDto> res = new ResponsePack<SubmissionDto>();
		Status status = new Status();
		Submission savedSubmission = null;
		SubmissionDto submissionDto =  new SubmissionDto();
		try {
			if (request.getPayload() != null){
				Submission submissionDb = submissionDetailsTransformer.transformToDao(studentId,assignmentId,request.getPayload());	
				savedSubmission = submissionRepository.save(submissionDb);
				submissionDto = submissionDetailsTransformer.transform(savedSubmission);
				
			} else {
				status.setCode(Constants.NOTFOUND);
				res.setStatus(status);
			}
			
			if (savedSubmission.getSubmissionId() != null) {
				status.setCode(Constants.CREATED);
				res.setStatus(status);
				res.setData(submissionDto);
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
	public ResponsePack<AnswerDto> createAnswer(Integer submissionId, Integer questionId,
			RequestWrapper<AnswerDto> request) throws Exception {
		ResponsePack<AnswerDto> res = new ResponsePack<AnswerDto>();
		Status status = new Status();
		Answer savedAnswer = null;
		AnswerDto answerDto =  new AnswerDto();
		try {
			if (request.getPayload() != null){
				Optional<Submission> submission = submissionRepository.findById(submissionId);
				if(submission.isPresent()) {					
					Answer answerDb = answerDetailsTransformer.transformToDao(submission.get(),questionId,request.getPayload());	
					savedAnswer = answerRepository.save(answerDb);
					answerDto = answerDetailsTransformer.transform(savedAnswer);
					//questionRepository.saveAll(questionListDb);
				}else {
					status.setCode(Constants.NOTFOUND);
					res.setStatus(status);
				}
				
				
			} else {
				status.setCode(Constants.NOTFOUND);
				res.setStatus(status);
			}
			
			if (savedAnswer.getAnswerId() != null) {
				status.setCode(Constants.CREATED);
				res.setStatus(status);
				res.setData(answerDto);
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
	public ResponsePack<AnswerDto> updateAnswer(Integer submissionId, Integer questionId,
			RequestWrapper<AnswerDto> request) throws Exception {
		ResponsePack<AnswerDto> res = new ResponsePack<AnswerDto>();
		Status status = new Status();
		Answer savedAnswer = null;
		AnswerDto answerDto =  new AnswerDto();
		try {
			if (request.getPayload() != null){
				Optional<Submission> submission = submissionRepository.findById(submissionId);
				Answer answer = answerRepository.findBySubmissionAndQuestionId(submission.get(),questionId);
				if(answer != null) {					
					//Answer answerDb = answerDetailsTransformer.transformToDao(answer,questionId,request.getPayload());
					if(null != request.getPayload().getAnswer()) {
						answer.setAnswer(request.getPayload().getAnswer());
					}
					savedAnswer = answerRepository.save(answer);
					answerDto = answerDetailsTransformer.transform(savedAnswer);
					//questionRepository.saveAll(questionListDb);
				}else {
					status.setCode(Constants.NOTFOUND);
					res.setStatus(status);
				}
				
				
			} else {
				status.setCode(Constants.NOTFOUND);
				res.setStatus(status);
			}
			
			if (savedAnswer.getAnswerId() != null) {
				status.setCode(Constants.CREATED);
				res.setStatus(status);
				res.setData(answerDto);
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
