package com.mbn.accessmanagement.repositories;

import com.mbn.accessmanagement.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
