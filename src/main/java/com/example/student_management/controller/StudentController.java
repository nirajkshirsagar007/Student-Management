package com.example.student_management.controller;

import com.example.student_management.entity.Student;
import com.example.student_management.exception.StudentNotFoundException;
import com.example.student_management.repository.StudentRepository;
import com.example.student_management.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "${FRONTEND_URL:http://localhost:5173}") // Allows local dev by default, but can be overridden in Render
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    private StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student)
    {
        return studentService.createStudent(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestBody Student updatedStudent ){
        Student existingStudent = studentService.getStudentById(id);

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());

        return studentService.createStudent(existingStudent);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        Student existingStudent = studentService.getStudentById(id);
        studentService.deleteStudent(id);
        return"Student deleted successfully";
    }

}
