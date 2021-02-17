package com.nk.school.elearning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nk.school.elearning.dao.TeacherRepository;
import com.nk.school.elearning.dto.FeedbackDto;
import com.nk.school.elearning.dto.SubmissionDto;
import com.nk.school.elearning.dto.TeacherDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;
import com.nk.school.elearning.mapping.response.Status;
import com.nk.school.elearning.model.Feedback;
import com.nk.school.elearning.model.Teacher;
import com.nk.school.elearning.service.TeacherService;
import com.nk.school.elearning.transformer.TeacherDetailsTransformer;
import com.nk.school.elearning.util.Constants;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	TeacherDetailsTransformer teacherDetailsTransformer;
	
	
	
	@Override
	public ResponsePack<TeacherDto> createTeacher(RequestWrapper<TeacherDto> request) throws Exception {
		ResponsePack<TeacherDto> res = new ResponsePack<TeacherDto>();
		Status status = new Status();
		Teacher savedTeacher = null;
		TeacherDto teacherDto =  new TeacherDto();
		try {
			if (request.getPayload() != null){
				Teacher teacherDb = teacherDetailsTransformer.transformToDao(request.getPayload());	
				savedTeacher = teacherRepository.save(teacherDb);
				teacherDto = teacherDetailsTransformer.transform(savedTeacher);
				
			} else {
				status.setCode(Constants.NOTFOUND);
				res.setStatus(status);
			}
			
			if (savedTeacher.getUserId() != null) {
				status.setCode(Constants.CREATED);
				res.setStatus(status);
				res.setData(teacherDto);
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
	public ResponsePack<TeacherDto> updateTeacher(Integer teacherId, RequestWrapper<TeacherDto> request)
			throws Exception {
		ResponsePack<TeacherDto> res = new ResponsePack<TeacherDto>();
		Status status = new Status();
		Teacher savedTeacher = null;
		TeacherDto teacherSavedDto =  new TeacherDto();
		try {
			Teacher teacherFromDB= null;
			Optional<Teacher> teacher = teacherRepository.findById(teacherId);
			if (teacher.isPresent()) {
				teacherFromDB = teacher.get();
				 // get payload assignment obj here
				Teacher teacherfromFront = teacherDetailsTransformer.transformToDao(request.getPayload()); 
				
				if(teacherfromFront.getFirstName()!= null ) {
					teacherFromDB.setFirstName(teacherfromFront.getFirstName());
				}
				if(teacherfromFront.getLastName()!= null ) {
					teacherFromDB.setLastName(teacherfromFront.getLastName());
				}
				if(teacherfromFront.getUsername()!=null) {
					teacherFromDB.setUsername(teacherfromFront.getUsername());
				}
//				if(studentfromFront.getGrade().getGradeName() != null ) {
//					studentfromFront.getGrade().setGradeName(studentfromFront.getGrade().getGradeName());
//					//studentFromDB.setGrade(studentfromFront.getGrade());
//				}								
				savedTeacher = teacherRepository.save(teacherFromDB);
				teacherSavedDto = teacherDetailsTransformer.transform(savedTeacher);
				status.setCode(Constants.CREATED);
				res.setStatus(status);
				res.setData(teacherSavedDto);
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
	public ResponsePack<TeacherDto> getAllTeachers() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
