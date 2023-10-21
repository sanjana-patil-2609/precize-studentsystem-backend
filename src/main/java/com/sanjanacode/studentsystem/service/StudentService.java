package com.sanjanacode.studentsystem.service;

import com.sanjanacode.studentsystem.model.Student;
import com.sanjanacode.studentsystem.model.StudentRequestByName;
import com.sanjanacode.studentsystem.model.StudentRequest;
import com.sanjanacode.studentsystem.model.StudentUpdateRequest;

import java.util.List;

public interface StudentService {
    void saveStudent(StudentRequest studentRequest);
    List<Student> getAllStudents();
    void deleteStudent(StudentRequestByName studentDeleteRequest);
    void updateStudentScore(StudentUpdateRequest studentUpdateRequest);
    int getRank(String name);
}
