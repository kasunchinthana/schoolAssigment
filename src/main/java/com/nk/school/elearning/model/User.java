package com.nk.school.elearning.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import com.nk.school.elearning.model.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Auditable implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String password;
	private String username;
	private String email;
	private Integer mobileNo;
	private LocalDate dob;
	private String firstName;
	private String lastName;
}
