package com.nk.school.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nk.school.elearning.model.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

}
