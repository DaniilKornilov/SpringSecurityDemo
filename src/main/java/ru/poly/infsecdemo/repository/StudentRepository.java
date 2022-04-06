package ru.poly.infsecdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.poly.infsecdemo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
