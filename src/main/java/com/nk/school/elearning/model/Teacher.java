package com.nk.school.elearning.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Teacher extends User{
	

	@OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
	private List<Feedback> feedback;
}
