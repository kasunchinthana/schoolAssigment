package com.nk.school.elearning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nk.school.elearning.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	@Query(value = "SELECT first_name,last_name,mobile_no FROM user u WHERE u.status = 1", nativeQuery = true)
	List<Teacher> findAllActiveTeachers();
}
