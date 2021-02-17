package com.nk.school.elearning.transformer;

import org.springframework.stereotype.Service;

import com.nk.school.elearning.dto.TeacherDto;
import com.nk.school.elearning.dto.TeacherSummaryDto;
import com.nk.school.elearning.model.Teacher;

@Service
public class TeacherDetailsTransformer {

	public Teacher transformToDao(TeacherDto teacherDto) {
		Teacher teacher = new Teacher();
		teacher.setFirstName(teacherDto.getSummary().getFirstName());
		teacher.setLastName(teacherDto.getSummary().getLastName());
		teacher.setUsername(teacherDto.getSummary().getUserName());
//		Grade grade = new Grade();
//		grade.setGradeId(studentDto.getSummary().getGrade());
//		student.setGrade(grade);
		return teacher;
	}

	public TeacherDto transform(Teacher savedTeacher) {
		TeacherDto teacherDto = new TeacherDto();
		TeacherSummaryDto teacherSummaryDto = new TeacherSummaryDto();
		teacherSummaryDto.setTeacherId(savedTeacher.getUserId());
		teacherSummaryDto.setFirstName(savedTeacher.getFirstName());
		teacherSummaryDto.setLastName(savedTeacher.getLastName());
		//studentSummaryDto.setGrade(student.getGrade().getGradeId());
		teacherSummaryDto.setUserName(savedTeacher.getUsername());
		teacherDto.setSummary(teacherSummaryDto);
		return teacherDto;
	}

}
