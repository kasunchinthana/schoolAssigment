package com.nk.school.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nk.school.elearning.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
