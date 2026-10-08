package com.freelance_platform.service;

import com.freelance_platform.entity.Student;
import com.freelance_platform.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // GET ALL STUDENTS
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // GET STUDENT BY ID
    public Student getStudentById(Integer id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found with id: " + id
                        )
                );
    }

    // CREATE STUDENT
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // UPDATE STUDENT
    public Student updateStudent(Integer id, Student student) {

        Student existingStudent =
                studentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student not found with id: " + id
                                )
                        );

        existingStudent.setCollegeName(
                student.getCollegeName()
        );

        existingStudent.setCourse(
                student.getCourse()
        );

        existingStudent.setBio(
                student.getBio()
        );

        existingStudent.setProfilePicture(
                student.getProfilePicture()
        );

        existingStudent.setEducation(
                student.getEducation()
        );

        existingStudent.setLocation(
                student.getLocation()
        );

        return studentRepository.save(existingStudent);
    }

    // DELETE STUDENT
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}