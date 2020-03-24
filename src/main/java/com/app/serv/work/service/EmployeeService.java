package com.app.serv.work.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.serv.work.model.Employee;

public interface EmployeeService extends JpaRepository<Employee, Integer>{
}
