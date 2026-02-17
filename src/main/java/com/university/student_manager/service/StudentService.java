package com.university.student_manager.service;

import com.university.student_manager.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private Long nextID = 1L;

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Optional<Student> getStudentsByID(Long id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public Student createStudent(Student student) {
        student.setId(nextID++);
        students.add(student);
        return student;
    }

    public Student studentUpdate(Student student) {
        Student existingStudent = getStudentsByID(student.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAge(student.getAge());
        return existingStudent;
    }
    public void deleteStudent(Long id) {
        Student delStudent = getStudentsByID(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
                students.remove(delStudent);
    }
}
