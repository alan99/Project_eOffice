package com.alan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alan.model.Course;

public interface CourseRepo extends JpaRepository<Course, Long>{

	Course findBycName(String cName);
}
