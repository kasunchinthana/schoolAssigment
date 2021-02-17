package com.nk.school.elearning.service;

import com.nk.school.elearning.dto.AssignmentDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;

public interface AssignmentService {

	ResponsePack<AssignmentDto> createAssignment(RequestWrapper<AssignmentDto> payload) throws Exception;

	ResponsePack<AssignmentDto> getAssignmentsById(Integer id) throws Exception;

	ResponsePack<AssignmentDto> deleteAssignmentById(Integer id) throws Exception;

	ResponsePack<AssignmentDto> updateAssignment(Integer id, RequestWrapper<AssignmentDto> payload) throws Exception;

}
