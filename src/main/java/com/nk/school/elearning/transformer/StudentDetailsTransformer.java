package com.nk.school.elearning.transformer;

import org.springframework.stereotype.Service;

import com.nk.school.elearning.dto.StudentDto;
import com.nk.school.elearning.dto.StudentSummaryDto;
import com.nk.school.elearning.model.Student;

@Service
public class StudentDetailsTransformer {

	public StudentDto transform(Student student) {
		
		StudentDto studentDto = new StudentDto();
		StudentSummaryDto studentSummaryDto = new StudentSummaryDto();
		studentSummaryDto.setStudentId(student.getUserId());
		studentSummaryDto.setFirstName(student.getFirstName());
		studentSummaryDto.setLastName(student.getLastName());
		//studentSummaryDto.setGrade(student.getGrade().getGradeId());
		studentSummaryDto.setUserName(student.getUsername());
		studentDto.setSummary(studentSummaryDto);
		return studentDto;
		
	}

	public Student transformToDao(StudentDto studentDto) {
		
		Student student = new Student();
		student.setFirstName(studentDto.getSummary().getFirstName());
		student.setLastName(studentDto.getSummary().getLastName());
		student.setUsername(studentDto.getSummary().getUserName());
//		Grade grade = new Grade();
//		grade.setGradeId(studentDto.getSummary().getGrade());
//		student.setGrade(grade);
		return student;
	}
}
