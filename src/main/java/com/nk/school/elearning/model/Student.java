package com.nk.school.elearning.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Student extends User{
	
	
	private Integer grade;
		

}
