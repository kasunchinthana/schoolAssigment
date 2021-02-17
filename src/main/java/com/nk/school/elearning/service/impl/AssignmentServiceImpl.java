package com.nk.school.elearning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nk.school.elearning.dao.AssignmentRepository;
import com.nk.school.elearning.dao.CourseRepository;
import com.nk.school.elearning.dto.AssignmentDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;
import com.nk.school.elearning.mapping.response.Status;
import com.nk.school.elearning.model.Assignment;
import com.nk.school.elearning.model.Course;
import com.nk.school.elearning.model.Question;
import com.nk.school.elearning.service.AssignmentService;
import com.nk.school.elearning.transformer.AssignmentDetailsTransformer;
import com.nk.school.elearning.util.Constants;

@Service
public class AssignmentServiceImpl implements AssignmentService {
	
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Autowired
	private AssignmentDetailsTransformer assignmentDetailsTransformer;
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public ResponsePack<AssignmentDto> createAssignment(RequestWrapper<AssignmentDto> assignment) throws Exception {
		ResponsePack<AssignmentDto> res = new ResponsePack<AssignmentDto>();
		Status status = new Status();
		Assignment savedAssignment = null;
		try {
			if (assignment.getPayload() != null) {
				Assignment assignmentDb = assignmentDetailsTransformer.transformToDao(assignment.getPayload());
				if (null != assignment.getPayload().getSummary().getCourseCode()) {
					// assignmentFromDB.getSubject().setId(id);
					List<Course> courseList = courseRepository.findByCourseCode(assignment.getPayload().getSummary().getCourseCode());
							
					if (courseList.size() > 0) {
						Course course = courseList.get(0);
						assignmentDb.setCourseId(course.getCourseId());
					} else {
						status.setCode(Constants.NOTFOUND);
						res.setStatus(status);
					}

				} else {
					status.setCode(Constants.NOTFOUND);
					res.setStatus(status);
				}
				savedAssignment = assignmentRepository.save(assignmentDb);
			} else {
				status.setCode(Constants.NOTFOUND);
				res.setStatus(status);
			}

			if (savedAssignment.getAssingnmentId() != null) {
				status.setCode(Constants.CREATED);
				res.setStatus(status);
				return res;
			} else {
				status.setCode(Constants.ERROR);
				res.setStatus(status);
				return res;
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ResponsePack<AssignmentDto> getAssignmentsById(Integer id) throws Exception {
		ResponsePack<AssignmentDto> res = new ResponsePack<AssignmentDto>();
		try {
			Assignment assignmentFromDB = null;
			Optional<Assignment> assignment = assignmentRepository.findById(id);
			if (assignment.isPresent()) {
				assignmentFromDB = assignment.get();
			} else {
				assignmentFromDB = null;
			}
			AssignmentDto assignmentDto = assignmentDetailsTransformer.transform(assignmentFromDB);
			//List<AssignmentDto> dtoList = new ArrayList<AssignmentDto>();
			//dtoList.add(assignmentDto);
			res.setData(assignmentDto);

			return res;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public ResponsePack<AssignmentDto> deleteAssignmentById(Integer id) throws Exception {
		ResponsePack<AssignmentDto> res = new ResponsePack<AssignmentDto>();
		Status status = new Status();
		try {
			assignmentRepository.deleteById(id);
			status.setCode(200);
			status.setMessage("OK");
			res.setStatus(status);
		} catch (Exception ex) {
			throw ex;
		}
		return res;
	}

	@Override
	public ResponsePack<AssignmentDto> updateAssignment(Integer id, RequestWrapper<AssignmentDto> assignmentDto)
			throws Exception {
		ResponsePack<AssignmentDto> res = new ResponsePack<AssignmentDto>();
		Status status = new Status();
		Assignment savedAssignment = null;
		try {
			Assignment assignmentFromDB = null;
			Optional<Assignment> assignment = assignmentRepository.findById(id);
			if (assignment.isPresent()) {
				assignmentFromDB = assignment.get();
				// get payload assignment obj here
				Assignment assignmentfromFront = assignmentDetailsTransformer
						.transformToDao(assignmentDto.getPayload());

				if (assignmentfromFront.getCutoffMarks() != null) {
					assignmentFromDB.setCutoffMarks(assignmentfromFront.getCutoffMarks());
				}
				if (assignmentfromFront.getDescription() != null) {
					assignmentFromDB.setDescription(assignmentfromFront.getDescription());
				}
				if (assignmentfromFront.getDevDate() != null) {
					assignmentFromDB.setDevDate(assignmentfromFront.getDevDate());
				}
				if (assignmentfromFront.getDuration() != null) {
					assignmentFromDB.setDuration(assignmentfromFront.getDuration());
				}
				if (assignmentfromFront.getName() != null) {
					assignmentFromDB.setName(assignmentfromFront.getName());
				}
				if (assignmentfromFront.getStatus() != null) {
					assignmentFromDB.setStatus(assignmentfromFront.getStatus());
				}
				if (assignmentfromFront.getTotalMarks() != null) {
					assignmentFromDB.setTotalMarks(assignmentfromFront.getTotalMarks());
				}
				
				if (null != assignmentDto.getPayload().getSummary().getCourseCode()) {
					// assignmentFromDB.getSubject().setId(id);
					List<Course> courseList = courseRepository.findByCourseCode(assignmentDto.getPayload().getSummary().getCourseCode());
					if (courseList.size() > 0) {
						Course course = courseList.get(0);
						assignmentFromDB.setCourseId(course.getCourseId());;
						assignmentFromDB.setQuestion(assignmentfromFront.getQuestion());

						for (Question question : assignmentFromDB.getQuestion()) {
							question.setAssignment(assignmentFromDB);
						}

						savedAssignment = assignmentRepository.save(assignmentFromDB);
						status.setCode(Constants.CREATED);
						res.setStatus(status);
						return res;
					} else {
						status.setCode(Constants.NOTFOUND);
						res.setStatus(status);
						// throw exception here
						// throw
					}

				} else {

					status.setCode(Constants.NOTFOUND);
					res.setStatus(status);
					// throw exception here
				}

			} else {
				status.setCode(Constants.NOTFOUND);
				res.setStatus(status);
			}

		} catch (Exception ex) {
			throw ex;
		}
		return res;
	}
	

}
