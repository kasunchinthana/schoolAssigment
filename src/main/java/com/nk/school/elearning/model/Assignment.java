package com.nk.school.elearning.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nk.school.elearning.model.audit.Auditable;
import lombok.Data;

@Entity
@Data
public class Assignment extends Auditable implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "assingnment_id")
	private Integer assingnmentId;
	private Integer teacherId;
	private Integer courseId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;	
	private String name;
	private String status;
	@JsonFormat(pattern = "YYYY-MM-dd")
	private LocalDateTime devDate;
	private Integer totalMarks;
	private Integer cutoffMarks;
	private Integer duration;
	private String description;
	private Integer grade;
	
	
	@OneToMany(mappedBy = "assignment",cascade = CascadeType.ALL)
	private List<Question> question;

}
