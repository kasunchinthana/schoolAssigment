package com.nk.school.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nk.school.elearning.model.Assignment;


public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

}
