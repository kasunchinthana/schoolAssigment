package com.nk.school.elearning.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nk.school.elearning.model.Course;


public interface CourseRepository extends JpaRepository<Course, Integer>{

	List<Course> findByCourseCode(String courseCode);

}
