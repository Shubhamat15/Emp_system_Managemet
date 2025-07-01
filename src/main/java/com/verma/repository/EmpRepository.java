package com.verma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verma.entity.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer>{

}
