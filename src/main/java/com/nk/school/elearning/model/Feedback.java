package com.nk.school.elearning.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.nk.school.elearning.model.audit.Auditable;

import lombok.Data;

@Entity
@Data
public class Feedback extends Auditable implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer feedbackId;
	private Integer studentId;
	private Integer assignmentId;
	
	private String feedback;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id" ) 
	private Teacher teacher;
}
