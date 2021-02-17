package com.nk.school.elearning.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.nk.school.elearning.model.audit.Auditable;

import lombok.Data;

@Entity
@Data
public class Submission extends Auditable implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer submissionId;
	
	private Integer studentId;
	private Integer assignmentId;
	
	@OneToMany(mappedBy = "submission",cascade = CascadeType.ALL)
	private List<Answer> answer;

}
