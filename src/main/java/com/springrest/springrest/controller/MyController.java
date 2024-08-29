package com.springrest.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.services.CourseService;

// import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	// public String home(){
	// return "I'm at home";
	// }
	@Autowired
	private CourseService courseService;
	@GetMapping("/courses")
	public List<Course> getCourses() {
//		this.CourseService.
		return this.courseService.getCourses();
	}
	@GetMapping("/courses/{courseId}")
	public Course getCourse(@PathVariable String courseId) {
		return this.courseService.getCourse(Long.parseLong(courseId));
	}
	@PostMapping("courses")
	public Course postCourse(@RequestBody Course course) {
		return this.courseService.addCourse(course);
	}
	@PutMapping("courses")
	public Course updateCourse(@RequestBody Course course) {
		return this.courseService.changeCourse(course); 
	}
	@DeleteMapping("courses/{courseId}")
	public ResponseEntity<String> removeCourse(@PathVariable String courseId) {
		try {
			long id = Long.parseLong(courseId); 
			boolean isRemoved = this.courseService.deleteCourse(id);
			if(isRemoved) {
				return ResponseEntity.ok("Course removed successfully");
			}else {
				return ResponseEntity.status(404).body("Course not found!!");
			}
		}catch(NumberFormatException e){
			return ResponseEntity.status(400).body("Invalid course ID");
		}catch(Exception e){
			return ResponseEntity.status(500).body("Internal server erro");
		}
	}
	
}
 