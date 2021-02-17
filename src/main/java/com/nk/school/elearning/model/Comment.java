package com.nk.school.elearning.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.nk.school.elearning.model.audit.Auditable;

import lombok.Data;

@Entity
@Data
public class Comment extends Auditable implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	private Integer studentId;
	private Integer assignmentId;
	private Integer feedbackId;
	private Integer questionId;
	private String comment;
}
