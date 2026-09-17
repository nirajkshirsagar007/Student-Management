package com.example.student_management.service;

import com.example.student_management.entity.Student;
import com.example.student_management.exception.StudentNotFoundException;
import com.example.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException(id));
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent){
        Student existingStudent = getStudentById(id);

        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setName(updatedStudent.getName());

        return studentRepository.save(existingStudent);
    }
    public void deleteStudent(Long id) {
        Student existingStudent = getStudentById(id);
        studentRepository.delete(existingStudent);
    }
}
