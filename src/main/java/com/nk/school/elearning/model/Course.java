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
public class Course extends Auditable implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseId;
	private String courseName;
	private String courseCode;
	

}
