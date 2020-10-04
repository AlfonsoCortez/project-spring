package com.pruebadeproyecto.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import com.pruebadeproyecto.demo.model.Student;
import com.pruebadeproyecto.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> allStudents(){
        List<Student> studentList = studentRepository.findAll();
        
        if(studentList.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        System.out.println(studentList);
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
        Optional<Student> studentData = studentRepository.findById(id);

        if (studentData.isPresent()) {
            return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            Student newStudent = new Student(student.getName(), student.getLastname(), student.getAttendance());
            studentRepository.save(newStudent);
            System.out.println(newStudent);
            
            return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathParam(value = "id") String id)  {

        Optional<Student> studentData = studentRepository.findById(id);

        if(studentData.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Student studentUpdated = new Student(id, student.getName(), student.getLastname(), student.getAttendance());
        studentRepository.save(studentUpdated);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathParam(value = "id") String id)  {
        if(id.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        studentRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}