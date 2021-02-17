package com.nk.school.elearning.service;

import com.nk.school.elearning.dto.AnswerDto;
import com.nk.school.elearning.dto.StudentDto;
import com.nk.school.elearning.dto.SubmissionDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;

public interface StudentService {

	ResponsePack<StudentDto> createStudent(RequestWrapper<StudentDto> request) throws Exception;

	ResponsePack<StudentDto> updateStudent(Integer studentId, RequestWrapper<StudentDto> payload) throws Exception;

	ResponsePack<SubmissionDto> createSubmission(Integer studentId,Integer assignmentId,RequestWrapper<SubmissionDto> request) throws Exception;

	ResponsePack<AnswerDto> createAnswer(Integer submissionId, Integer questionId, RequestWrapper<AnswerDto> request) throws Exception;

	ResponsePack<AnswerDto> updateAnswer(Integer submissionId, Integer questionId, RequestWrapper<AnswerDto> request) throws Exception;

}
