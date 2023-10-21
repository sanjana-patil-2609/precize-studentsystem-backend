package com.sanjanacode.studentsystem.controller;

import com.sanjanacode.studentsystem.model.Student;
import com.sanjanacode.studentsystem.model.StudentRequestByName;
import com.sanjanacode.studentsystem.model.StudentRequest;
import com.sanjanacode.studentsystem.model.StudentUpdateRequest;
import com.sanjanacode.studentsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String add(@RequestBody StudentRequest studentRequest) {
        try {
            studentService.saveStudent(studentRequest);
            return "New Student Added";
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @DeleteMapping("/delete")
    public String deleteByName(@RequestBody StudentRequestByName studentRequestByName) {
        try {
            studentService.deleteStudent(studentRequestByName);
            return "Student Deleted Successfully";
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @PutMapping("/update")
    public String updateByName(@RequestBody StudentUpdateRequest studentUpdateRequest) {
        try {
            studentService.updateStudentScore(studentUpdateRequest);
            return "Score Updated Successfully";
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @GetMapping("/getRank")
    public int getRankByName(@RequestParam String name) {
        try {
            return studentService.getRank(name);
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}