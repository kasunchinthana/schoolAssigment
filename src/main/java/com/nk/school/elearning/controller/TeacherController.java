package com.nk.school.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nk.school.elearning.dto.AssignmentDto;
import com.nk.school.elearning.dto.CommentDto;
import com.nk.school.elearning.dto.FeedbackDto;
import com.nk.school.elearning.dto.SubmissionDto;
import com.nk.school.elearning.dto.TeacherDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;
import com.nk.school.elearning.service.CommentService;
import com.nk.school.elearning.service.FeedbackService;
import com.nk.school.elearning.service.TeacherService;

@RestController
@RequestMapping("/elearning-service/v1")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/teacher")
	public ResponseEntity<ResponsePack<TeacherDto>> createTeacher(@RequestBody RequestWrapper<TeacherDto> request) {
		ResponsePack<TeacherDto> responseTeacherDto = null;
		try {
			responseTeacherDto = teacherService.createTeacher(request);
			if (responseTeacherDto != null) {
				return new ResponseEntity<ResponsePack<TeacherDto>>(responseTeacherDto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<TeacherDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<TeacherDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/teacher/{teacherId}")
	public ResponseEntity<ResponsePack<TeacherDto>> updateTeacher(@PathVariable("teacherId") Integer teacherId,
			@RequestBody RequestWrapper<TeacherDto> payload) {
		ResponsePack<TeacherDto> updatedTeacher = null;
		try {
			updatedTeacher = teacherService.updateTeacher(teacherId, payload);
			if (updatedTeacher != null) {
				return new ResponseEntity<ResponsePack<TeacherDto>>(updatedTeacher, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<TeacherDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<TeacherDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//teacher gives feedback
	@PostMapping("/teacher/{teacherId}/students/{studentId}/assignment/{assignmentId}")
	public ResponseEntity<ResponsePack<FeedbackDto>> createFeedback(
			@PathVariable("teacherId") Integer teacherId,
			@PathVariable("studentId") Integer studentId,
			@PathVariable("assignmentId") Integer assignmentId,
			@RequestBody RequestWrapper<FeedbackDto> request) {
		ResponsePack<FeedbackDto> responseFeedbackDto = null;
		try {
			responseFeedbackDto = feedbackService.createFeedback(teacherId,studentId,assignmentId,request);
			if (responseFeedbackDto != null) {
				return new ResponseEntity<ResponsePack<FeedbackDto>>(responseFeedbackDto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<FeedbackDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<FeedbackDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//teacher update feedback
	@PutMapping("/teacher/{teacherId}/students/{studentId}/assignment/{assignmentId}")
	public ResponseEntity<ResponsePack<FeedbackDto>> updateFeedback(
			@PathVariable("teacherId") Integer teacherId,
			@PathVariable("studentId") Integer studentId,
			@PathVariable("assignmentId") Integer assignmentId,
			@RequestBody RequestWrapper<FeedbackDto> request) {
		ResponsePack<FeedbackDto> responseFeedbackDto = null;
		try {
			responseFeedbackDto = feedbackService.updateFeedback(teacherId,studentId,assignmentId,request);
			if (responseFeedbackDto != null) {
				return new ResponseEntity<ResponsePack<FeedbackDto>>(responseFeedbackDto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<FeedbackDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<FeedbackDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//teacher get feedbacks by assignment id and student id
	@GetMapping("/teacher/{teacherId}/students/{studentId}/assignments/{id}")
	public ResponseEntity<ResponsePack<FeedbackDto>> getAssignmentsById(
			@PathVariable("studentId") Integer studentId,
			@PathVariable("assignmentId") Integer assignmentId) {
		ResponsePack<FeedbackDto> feedbackDto = new ResponsePack<>();
		// ResponsePack<AssignmentDto> assignmentDtoLlist = new ResponsePack<>();
		try {
			feedbackDto = feedbackService.getFeedbackByStudentIdAndAssignmentId(studentId,assignmentId);
			if (feedbackDto != null) {
				return new ResponseEntity<ResponsePack<FeedbackDto>>(feedbackDto, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<ResponsePack<FeedbackDto>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	//teacher gives comment for question
	@PostMapping("/teacher/{teacherId}/feedback/{feedbackId}/question/{questionId}")
	public ResponseEntity<ResponsePack<CommentDto>> createComment(
			@PathVariable("teacherId") Integer teacherId,
			@PathVariable("feedbackId") Integer feedbackId,
			@PathVariable("questionId") Integer questionId,
			@RequestBody RequestWrapper<CommentDto> request) {
		ResponsePack<CommentDto> responseFeedbackDto = null;
		try {
			responseFeedbackDto = commentService.createComment(teacherId,feedbackId,questionId,request);
			if (responseFeedbackDto != null) {
				return new ResponseEntity<ResponsePack<CommentDto>>(responseFeedbackDto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<CommentDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<CommentDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//teacher can update the given comments for question
	@PutMapping("/teacher/{teacherId}/feedback/{feedbackId}/question/{questionId}")
	public ResponseEntity<ResponsePack<CommentDto>> updateComment(
			@PathVariable("teacherId") Integer teacherId,
			@PathVariable("feedbackId") Integer feedbackId,
			@PathVariable("questionId") Integer questionId,
			@RequestBody RequestWrapper<CommentDto> request) {
		ResponsePack<CommentDto> responseFeedbackDto = null;
		try {
			responseFeedbackDto = commentService.updateComment(teacherId,feedbackId,questionId,request);
			if (responseFeedbackDto != null) {
				return new ResponseEntity<ResponsePack<CommentDto>>(responseFeedbackDto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<CommentDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<CommentDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/teacher/All")
	public ResponseEntity<ResponsePack<TeacherDto>> getAllTeacher() {
		ResponsePack<TeacherDto> responseTeacherDto = null;
		try {
			responseTeacherDto = teacherService.getAllTeachers();
			if (responseTeacherDto != null) {
				return new ResponseEntity<ResponsePack<TeacherDto>>(responseTeacherDto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponsePack<TeacherDto>>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ResponsePack<TeacherDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
