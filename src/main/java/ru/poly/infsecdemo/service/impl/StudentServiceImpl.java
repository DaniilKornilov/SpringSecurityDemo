package ru.poly.infsecdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.poly.infsecdemo.entity.Student;
import ru.poly.infsecdemo.exception.StudentNotFoundException;
import ru.poly.infsecdemo.repository.StudentRepository;
import ru.poly.infsecdemo.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private static final String STUDENT_NOT_FOUND_MESSAGE = "Student not found! Student id: ";

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new StudentNotFoundException(STUDENT_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
}
