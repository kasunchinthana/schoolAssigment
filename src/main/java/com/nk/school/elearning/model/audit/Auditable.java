package com.nk.school.elearning.model.audit;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

	@CreatedBy
	private String created_by = "kasunSystem";
	
	@LastModifiedBy
    private String lastModifiedBy = "nuwanSystem";
	
	@CreatedDate
	@Column(nullable = false,updatable = false)
	private LocalDateTime created_at=  LocalDateTime.now();
	
	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updated_at = LocalDateTime.now();
}
