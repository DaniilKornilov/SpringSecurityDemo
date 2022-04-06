package ru.poly.infsecdemo.service;

import ru.poly.infsecdemo.entity.Student;

import java.util.List;

public interface StudentService {
    Student getStudentById(long id);

    List<Student> getStudents();
}
