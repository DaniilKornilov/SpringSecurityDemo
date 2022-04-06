package ru.poly.infsecdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.poly.infsecdemo.entity.Student;
import ru.poly.infsecdemo.service.StudentService;

import java.util.List;

@RestController
@RequestMapping(path = "infsec-api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{id}")
    public Student getStudentById(@PathVariable("id") long id) {
        return studentService.getStudentById(id);
    }
}
