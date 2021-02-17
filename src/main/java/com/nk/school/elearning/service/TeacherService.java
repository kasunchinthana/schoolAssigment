package com.nk.school.elearning.service;

import com.nk.school.elearning.dto.TeacherDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;

public interface TeacherService {

	ResponsePack<TeacherDto> createTeacher(RequestWrapper<TeacherDto> request) throws Exception;

	ResponsePack<TeacherDto> updateTeacher(Integer teacherId, RequestWrapper<TeacherDto> request) throws Exception;

	ResponsePack<TeacherDto> getAllTeachers() throws Exception;

}
