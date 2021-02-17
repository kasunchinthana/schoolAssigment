package com.nk.school.elearning.transformer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.school.elearning.dto.AssignmentDto;
import com.nk.school.elearning.dto.AssignmentSummaryDto;
import com.nk.school.elearning.dto.QuestionDto;
import com.nk.school.elearning.model.Assignment;
import com.nk.school.elearning.model.Course;
import com.nk.school.elearning.model.Question;


@Service
public class AssignmentDetailsTransformer {
	
	@Autowired
	QuestionDetailsTransformer questionDetailsTransformer;
	
	public AssignmentDto transform(Assignment assignment) {
		
		if(assignment != null) {
			AssignmentDto assignmentDto = new AssignmentDto();
			AssignmentSummaryDto assignmentSummaryDto = new AssignmentSummaryDto();
			assignmentSummaryDto.setAssingnmentId(assignment.getAssingnmentId());
			assignmentSummaryDto.setName(assignment.getName());
			assignmentSummaryDto.setCutoffMarks(assignment.getCutoffMarks());			
			assignmentSummaryDto.setDescription(assignment.getDescription());
			assignmentSummaryDto.setDevDate(assignment.getDevDate());
			assignmentSummaryDto.setDuration(assignment.getDuration());
			assignmentSummaryDto.setStatus(assignment.getStatus());
			assignmentSummaryDto.setTotalMarks(assignment.getTotalMarks());
			
			assignmentDto.setSummary(assignmentSummaryDto);
			return assignmentDto;
		} else {
			return null;
		}
		
		
	}

	public Assignment transformToDao(AssignmentDto assignmentDto) {
		
		Assignment assignment = new Assignment();
		
		assignment.setAssingnmentId(assignmentDto.getSummary().getAssingnmentId());
		assignment.setCutoffMarks(assignmentDto.getSummary().getCutoffMarks());
		assignment.setDescription(assignmentDto.getSummary().getDescription());
		assignment.setDevDate(assignmentDto.getSummary().getDevDate());
		assignment.setDuration(assignmentDto.getSummary().getDuration());
		assignment.setName(assignmentDto.getSummary().getName());
		assignment.setStatus(assignmentDto.getSummary().getStatus());
		assignment.setTotalMarks(assignmentDto.getSummary().getTotalMarks());
		
		Course course = new Course();	
		course.setCourseCode(assignmentDto.getSummary().getCourseCode());
		course.setCourseName(assignmentDto.getSummary().getCourseName());
		//get course from db and set the id here
		//assignment.setCourseId(courseId);(subject);
		if(null != assignmentDto.getQuestions()) {
			List<Question> questionList = new ArrayList<Question>();
		//Question question = null;
		for (QuestionDto questionDto : assignmentDto.getQuestions()) {
			Question  question = questionDetailsTransformer.transformToDao(questionDto);
			
			questionList.add(question);			
		}
		assignment.setQuestion(questionList);
		}
		if(null != assignmentDto.getSummary().getTeacherId()) {
			List<Question> questionList = new ArrayList<Question>();
		}
		return assignment;
		
	}
	
}
