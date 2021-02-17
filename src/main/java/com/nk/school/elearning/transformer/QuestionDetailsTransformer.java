package com.nk.school.elearning.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nk.school.elearning.dto.ChoiceDto;
import com.nk.school.elearning.dto.QuestionDto;
import com.nk.school.elearning.dto.QuestionListDto;
import com.nk.school.elearning.model.Assignment;
import com.nk.school.elearning.model.Choice;
import com.nk.school.elearning.model.Question;

@Service
public class QuestionDetailsTransformer {

	public QuestionDto transform(Question question) {

		QuestionDto questionDto = new QuestionDto();

		questionDto.setQuestionId(question.getQuestionId());
		questionDto.setCorrectAnswer(question.getCorrectAnswer());
		questionDto.setHint(question.getHint());
		questionDto.setQuestion(question.getQuestion());
		questionDto.setType(question.getType());
		// questionDto.setSummary(questionSummaryDto);
		return questionDto;

	}

	public List<Question> transformToDaoList(Assignment assignment, QuestionListDto questionDtoList) {

		List<Question> questionList = new ArrayList<Question>();
		for (QuestionDto questionDto : questionDtoList.getQuestions()) {
			Question question = new Question();
			question.setCorrectAnswer(questionDto.getCorrectAnswer());
			question.setHint(questionDto.getHint());
			question.setQuestion(questionDto.getQuestion());
			question.setQuestionId(questionDto.getQuestionId());
			question.setQuestionType(questionDto.getType());
			
			List<Choice> choiceList = new ArrayList<Choice>();
			if(null != questionDto.getChoices() && questionDto.getChoices().size()>0) {
				for (ChoiceDto choiceDto : questionDto.getChoices()) {
				Choice choice = new Choice();
				choice.setAnswer(choiceDto.getAnswer());
				choice.setQuestion(question);
				choiceList.add(choice);
				}
				question.setChoice(choiceList);
			}
			
			questionList.add(question);
			question.setAssignment(assignment);
		}

		return questionList;
	}

	public Question transformToDao(QuestionDto questionDto) {

		Question question = new Question();
		question.setCorrectAnswer(questionDto.getCorrectAnswer());
		question.setHint(questionDto.getHint());
		question.setQuestion(questionDto.getQuestion());
		question.setQuestionId(questionDto.getQuestionId());
		question.setQuestionType(questionDto.getType());

		List<Choice> choiceList = new ArrayList<Choice>();
		if(null != questionDto.getChoices() && questionDto.getChoices().size()>0) {
			for (ChoiceDto choiceDto : questionDto.getChoices()) {
			Choice choice = new Choice();
			choice.setAnswer(choiceDto.getAnswer());
			choice.setQuestion(question);
			choiceList.add(choice);
			}
			question.setChoice(choiceList);
		}
		
		return question;
	}

}
