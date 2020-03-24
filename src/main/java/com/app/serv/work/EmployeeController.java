package com.app.serv.work;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.serv.work.model.Employee;
import com.app.serv.work.service.EmployeeService;
import com.app.serv.work.service.ExcelWriter;
import com.app.serv.work.service.TestServImpl;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Select, Insert, Delete, Update Operations for an Employee
    
    

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    Employee getEmployee(@PathVariable Integer id){
        return  employeeService.findById(id).get();
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    String addEmployee(@RequestBody Employee employee){
        Employee savedEmployee = employeeService.save(employee);
        return "SUCCESS";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    Employee updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = employeeService.save(employee);
        return updatedEmployee;
    }
    
    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    String excelEmployee(){
    	TestServImpl testsrvimpl = new TestServImpl();
    	testsrvimpl.generateReport();
        return "success";
    }

    @RequestMapping(value = "/excel2", method = RequestMethod.GET)
    String excelEmployee2(){
    	ExcelWriter testsrvimpl = new ExcelWriter();
    	testsrvimpl.doExcel();
        return "success";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.DELETE)
    Map<String, String> deleteEmployee(@RequestParam Integer id){
        Map<String, String> status = new HashMap<>();
        Optional<Employee> employee = employeeService.findById(id);
        if(employee.isPresent()) {
            employeeService.delete(employee.get());
            status.put("Status", "Employee deleted successfully");
        }
        else {
            status.put("Status", "Employee not exist");
        }
        return status;
    }

    // Select, Insert, Delete for List of Employees

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    List<Employee> getAllEmployee(){
        return employeeService.findAll();
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    String addAllEmployees(@RequestBody List<Employee> employeeList){
        employeeService.saveAll(employeeList);
        return "SUCCESS";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.DELETE)
    String addAllEmployees(){
        employeeService.deleteAll();
        return "SUCCESS";
    }
}
