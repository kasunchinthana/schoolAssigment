package com.nk.school.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nk.school.elearning.dto.AssignmentDto;
import com.nk.school.elearning.dto.QuestionDto;
import com.nk.school.elearning.dto.QuestionListDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;
import com.nk.school.elearning.service.AssignmentService;
import com.nk.school.elearning.service.QuestionService;


@RestController
@RequestMapping("/elearning-service/v1")
public class AssignmentController {
	
	@Autowired
	private AssignmentService assignmentService;
	
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("/assignments")
	public ResponseEntity<ResponsePack<AssignmentDto>> createAssignments(
			@RequestBody RequestWrapper<AssignmentDto> payload) {
		ResponsePack<AssignmentDto> responseAssignmentDto = null;
		try {
			responseAssignmentDto = assignmentService.createAssignment(payload);
			if (responseAssignmentDto != null) {
				return new ResponseEntity<ResponsePack<AssignmentDto>>(responseAssignmentDto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<AssignmentDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<AssignmentDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/assignments/{id}")
	public ResponseEntity<ResponsePack<AssignmentDto>> getAssignmentsById(@PathVariable("id") Integer id) {
		ResponsePack<AssignmentDto> assignmentDto = new ResponsePack<>();
		// ResponsePack<AssignmentDto> assignmentDtoLlist = new ResponsePack<>();
		try {
			assignmentDto = assignmentService.getAssignmentsById(id);
			if (assignmentDto != null) {
				return new ResponseEntity<ResponsePack<AssignmentDto>>(assignmentDto, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<ResponsePack<AssignmentDto>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@DeleteMapping("/assignments/{id}")
	public ResponseEntity<ResponsePack<AssignmentDto>> deleteAssignmentById(@PathVariable("id") Integer id) {
		ResponsePack<AssignmentDto> assignmentDto = new ResponsePack<>();
		try {
			assignmentDto = assignmentService.deleteAssignmentById(id);
			if (assignmentDto != null) {
				return new ResponseEntity<ResponsePack<AssignmentDto>>(assignmentDto, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<ResponsePack<AssignmentDto>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/assignments/{id}")
	public ResponseEntity<ResponsePack<AssignmentDto>> updateAssignment(@PathVariable("id") Integer id,
			@RequestBody RequestWrapper<AssignmentDto> payload) {
		ResponsePack<AssignmentDto> updatetAssignment = null;
		try {
			updatetAssignment = assignmentService.updateAssignment(id, payload);
			if (updatetAssignment != null) {
				return new ResponseEntity<ResponsePack<AssignmentDto>>(updatetAssignment, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<AssignmentDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<AssignmentDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping("assignments/{id}/question")
	public ResponseEntity<ResponsePack<QuestionDto>> createQuestion(@PathVariable("id") Integer id,
			@RequestBody RequestWrapper<QuestionListDto> request) {
		ResponsePack<QuestionDto> responseQuestioDto = null;
		try {
			responseQuestioDto = questionService.createQuestion(id,request.getPayload());
			if (responseQuestioDto != null) {
				return new ResponseEntity<ResponsePack<QuestionDto>>(responseQuestioDto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<QuestionDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<QuestionDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("assignments/{id}/question/{questionId}")
	public ResponseEntity<ResponsePack<QuestionDto>> updateQuestion(@PathVariable("id") Integer id,
			@RequestBody RequestWrapper<QuestionDto> request,@PathVariable("questionId") Integer questionId) {
		ResponsePack<QuestionDto> updateQuestion = null;
		try {
			updateQuestion = questionService.updateQuestion(id, request,questionId);
			if (updateQuestion != null) {
				return new ResponseEntity<ResponsePack<QuestionDto>>(updateQuestion, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<QuestionDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			return new ResponseEntity<ResponsePack<QuestionDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
