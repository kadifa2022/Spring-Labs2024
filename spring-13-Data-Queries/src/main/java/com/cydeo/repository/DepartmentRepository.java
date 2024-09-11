package com.cydeo.repository;

import com.cydeo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    //Display all departments in the Furniture Department
    Optional<Department> findByDepartment(String department);

    //Find all departments in the Health Division
}
