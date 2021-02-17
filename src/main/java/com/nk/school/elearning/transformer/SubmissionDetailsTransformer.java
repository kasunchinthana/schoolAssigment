package com.nk.school.elearning.transformer;

import org.springframework.stereotype.Service;

import com.nk.school.elearning.dto.SubmissionDto;
import com.nk.school.elearning.model.Submission;

@Service
public class SubmissionDetailsTransformer {

	public Submission transformToDao(Integer studentId,Integer assignmentId,SubmissionDto payload) {
		
		Submission submission = new Submission();
		submission.setAssignmentId(assignmentId);
		submission.setStudentId(studentId);
//		Grade grade = new Grade();
//		grade.setGradeId(studentDto.getSummary().getGrade());
//		student.setGrade(grade);
		return submission;
	}

	public SubmissionDto transform(Submission savedSubmission) {
		
		SubmissionDto submissionDto = new SubmissionDto();
		submissionDto.setAssignmentId(savedSubmission.getAssignmentId());
		submissionDto.setStudetntId(savedSubmission.getStudentId());
		submissionDto.setSubmissionId(savedSubmission.getSubmissionId());
		return submissionDto;
	}

}
