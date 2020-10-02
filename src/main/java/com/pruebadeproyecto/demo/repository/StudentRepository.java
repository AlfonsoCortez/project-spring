package com.pruebadeproyecto.demo.repository;

import java.util.List;

import com.pruebadeproyecto.demo.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String>{
    
    // public List<Student> findByAttend(boolean attendance);
    // public List<Student> findByNameContaining(String name);
}
