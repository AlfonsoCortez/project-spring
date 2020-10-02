package com.pruebadeproyecto.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.pruebadeproyecto.demo.model.Student;
import com.pruebadeproyecto.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class StudentController {
    
    @Autowired
    StudentRepository studentRepository;


    @GetMapping("")
    public String things(){
      return "hola, mundo";
      
    }
    // @PostMapping("/student")
    // public ResponseEntity<?> anything(@RequestBody Student student) {
    //   try {
    //     return ResponseEntity.status(HttpStatus.CREATED).body(studentRepository.insert(student));
    //   } catch (Exception e) {
    //     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); 
    //   }
    // }
    //Methods 
    @PostMapping("/student")
    public ResponseEntity<Student> createTutorial(@RequestBody Student student) {
        try {
            Student _student = studentRepository.save(new Student(student.getName(), student.getLastname(), false));
            return new ResponseEntity<>(_student, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // @GetMapping("/students")
    // public ResponseEntity<List<Student>> getAllTutorials(@RequestParam(required = false) String name) {
    //   try {
    //     List<Student> students = new ArrayList<Student>();
  
    //     if (name == null){
    //         studentRepository.findAll().forEach(students::add);
    //     }else{
    //         studentRepository.findByNameContaining(name).forEach(students::add);
    //     }
  
    //     if (students.isEmpty()) {
    //       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     }
  
    //     return new ResponseEntity<>(students, HttpStatus.OK);
    //   } catch (Exception e) {
    //     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //   }
    // }
  

}
