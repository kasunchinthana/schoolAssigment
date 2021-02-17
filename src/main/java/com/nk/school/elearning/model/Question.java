package com.nk.school.elearning.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.nk.school.elearning.model.audit.Auditable;

import lombok.Data;

@Entity
@Data
public class Question extends Auditable implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer questionId;
	private String correctAnswer;
	private String question;
	private String questionType;
	private String hint;
	private String type;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="assingnment_id" ) 
	private Assignment assignment;
	
	@OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
	private List<Choice> choice;

}
