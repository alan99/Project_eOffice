package com.alan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alan.dao.CourseRepo;
import com.alan.model.*;

@RestController
public class CourseController {
	@Autowired
	private CourseRepo courseRepo;
	
	@GetMapping("/courses")
	List<Course> findAll(){
		return courseRepo.findAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/courses")
	Course newCourse(@RequestBody Course course) {
		return courseRepo.save(course);
	}
	
	@GetMapping("/courses/{cId}")
//	public Optional<Course> searchById(@PathVariable Long cId){
//		return courseRepo.findById(cId);
//	}
	public String findById(@PathVariable Long cId){
		Course course = courseRepo.findById(cId).orElse(null);
		if (course == null)
			return "There is no course with ID " + cId;
		else
			return "Course " + cId + " is " + course.getcName();
	}
	
//	@GetMapping("/courses/{cName}")
//	public Optional<Course> findByName(@PathVariable String cName){
//		return courseRepo.findAll()
//	}
	
	@GetMapping("/course")
	public Course findByName(@RequestParam(value = "name") String name) {
		return courseRepo.findBycName(name);
	}
	
	@PutMapping("/courses/{cId}")
	public String updateCourse(@RequestBody Course course, @PathVariable Long cId) {
		Course newC = new Course(course.getcName());
		newC.setcId(cId);
		courseRepo.save(newC);
		return "The class " + cId + " is updated to the name as " + newC.getcName();
	}
	
	@DeleteMapping("/courses/{cId}")
	public String deleteCourseById(@PathVariable Long cId) {
		Course course = courseRepo.findById(cId).orElse(null);
		if (course == null)
			return "There is no class to be removed.";
		else {
			String cName = course.getcName();
			courseRepo.deleteById(cId);
			return "The course " + cName + " is removed";
		}
	}
}
