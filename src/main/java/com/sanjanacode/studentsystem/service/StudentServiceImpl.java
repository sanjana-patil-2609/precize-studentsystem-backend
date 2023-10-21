package com.sanjanacode.studentsystem.service;

import com.sanjanacode.studentsystem.model.Student;
import com.sanjanacode.studentsystem.model.StudentRequestByName;
import com.sanjanacode.studentsystem.model.StudentRequest;
import com.sanjanacode.studentsystem.model.StudentUpdateRequest;
import com.sanjanacode.studentsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public void saveStudent(StudentRequest studentRequest) {
        if (studentRepository.findByName(studentRequest.getName()) != null) {
            throw new IllegalStateException("Student Already Exists");
        }
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setAddress(studentRequest.getAddress());
        student.setCity(studentRequest.getCity());
        student.setCountry(studentRequest.getCountry());
        student.setPincode(studentRequest.getPincode());
        student.setSatScore(studentRequest.getSatScore());
        student.setPassed(getIfStudentPassed(studentRequest.getSatScore()));
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(StudentRequestByName studentRequestByName) {
        Student student = studentRepository.findByName(studentRequestByName.getName());

        if (student != null)
            studentRepository.delete(student);
        else
            throw new IllegalArgumentException("Student Not Exists");
    }

    @Override
    public void updateStudentScore(StudentUpdateRequest studentUpdateRequest) {
        Student student = studentRepository.findByName(studentUpdateRequest.getName());
        if (student != null) {
            student.setSatScore(studentUpdateRequest.getSatScore());
            student.setPassed(getIfStudentPassed(studentUpdateRequest.getSatScore()));
            studentRepository.save(student);
        }
        else
            throw new IllegalArgumentException("Student Not Exists");
    }

    @Override
    public int getRank(String name) {
//        String name = studentRequestByName.getName().replaceAll("\\+", " ");
        List<Student> studentList = getAllStudents();
        studentList.sort(new SortByScore());
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getName().equals(name))
                return i + 1;
        }

        throw new IllegalArgumentException("Student Not Exists");
    }

    private boolean getIfStudentPassed(int marks) {
        return marks > 30;
    }
}

class SortByScore implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return b.getSatScore() - a.getSatScore();
    }
}
