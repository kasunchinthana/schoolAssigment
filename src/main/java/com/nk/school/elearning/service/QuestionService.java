package com.nk.school.elearning.service;

import com.nk.school.elearning.dto.QuestionDto;
import com.nk.school.elearning.dto.QuestionListDto;
import com.nk.school.elearning.mapping.response.RequestWrapper;
import com.nk.school.elearning.mapping.response.ResponsePack;

public interface QuestionService {

	ResponsePack<QuestionDto> createQuestion(Integer id,QuestionListDto payload);

	ResponsePack<QuestionDto> updateQuestion(Integer id, RequestWrapper<QuestionDto> payload,Integer questionId);

}
