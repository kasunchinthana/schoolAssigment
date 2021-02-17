package com.nk.school.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nk.school.elearning.dto.AnswerDto;
import com.nk.school.elearning.dto.StudentDto;
import com.nk.school.elearning.dto.SubmissionDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;
import com.nk.school.elearning.service.StudentService;



@RestController
@RequestMapping("/elearning-service/v1")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/students")
	public ResponseEntity<ResponsePack<StudentDto>> createStudent(@RequestBody RequestWrapper<StudentDto> request) {
		ResponsePack<StudentDto> responseStudentDto = null;
		try {
			responseStudentDto = studentService.createStudent(request);
			if (responseStudentDto != null) {
				return new ResponseEntity<ResponsePack<StudentDto>>(responseStudentDto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<StudentDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<StudentDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/students/{studentId}")
	public ResponseEntity<ResponsePack<StudentDto>> updateStudent(@PathVariable("studentId") Integer studentId,
			@RequestBody RequestWrapper<StudentDto> payload) {
		ResponsePack<StudentDto> updatetStudent = null;
		try {
			updatetStudent = studentService.updateStudent(studentId, payload);
			if (updatetStudent != null) {
				return new ResponseEntity<ResponsePack<StudentDto>>(updatetStudent, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<StudentDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<StudentDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	//student submit the assignment
	@PostMapping("/students/{studentId}/assignment/{assignmentId}")
	public ResponseEntity<ResponsePack<SubmissionDto>> createSubmission(@PathVariable("studentId") Integer studentId,
			@PathVariable("assignmentId") Integer assignmentId,
			@RequestBody RequestWrapper<SubmissionDto> request) {
		ResponsePack<SubmissionDto> responseSubmissionDto = null;
		try {
			responseSubmissionDto = studentService.createSubmission(studentId,assignmentId,request);
			if (responseSubmissionDto != null) {
				return new ResponseEntity<ResponsePack<SubmissionDto>>(responseSubmissionDto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<SubmissionDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<SubmissionDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//student submit the answers
	@PostMapping("/submission/{submissionId}/question/{questionId}")
	public ResponseEntity<ResponsePack<AnswerDto>> createAnswer(@PathVariable("submissionId") Integer submissionId,
			@PathVariable("questionId") Integer questionId,
			@RequestBody RequestWrapper<AnswerDto> request) {
		ResponsePack<AnswerDto> responseAnswerDto = null;
		try {
			responseAnswerDto = studentService.createAnswer(submissionId,questionId,request);
			if (responseAnswerDto != null) {
				return new ResponseEntity<ResponsePack<AnswerDto>>(responseAnswerDto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<AnswerDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<AnswerDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//student update the answers
	@PutMapping("/submission/{submissionId}/question/{questionId}")
	public ResponseEntity<ResponsePack<AnswerDto>> updateAnswer(@PathVariable("submissionId") Integer submissionId,
			@PathVariable("questionId") Integer questionId,
			@RequestBody RequestWrapper<AnswerDto> request) {
		ResponsePack<AnswerDto> responseAnswerDto = null;
		try {
			responseAnswerDto = studentService.updateAnswer(submissionId,questionId,request);
			if (responseAnswerDto != null) {
				return new ResponseEntity<ResponsePack<AnswerDto>>(responseAnswerDto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<AnswerDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<AnswerDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
