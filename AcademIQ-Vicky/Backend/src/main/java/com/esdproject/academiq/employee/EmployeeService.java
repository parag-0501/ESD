package com.esdproject.academiq.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private final EmployeeSalaryRepository employeeSalaryRepository;
    private final EmployeeRepository employeeRepository;

    public Optional<List<EmployeeSalary>> getSalaryDetails(Integer facultyId) {
        // Extract faculty ID from token
        // Fetch salary details for the faculty
        System.out.println("************************************************");
        Optional<Employee> employeeOptional = employeeRepository.findById(facultyId);
        System.out.println("---------------------------------------------------");
        Optional<List<EmployeeSalary>> employeeSalaries = Optional.empty();
        System.out.println("#####################################################");
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            // Fetching salary details for the employee
            try {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                employeeSalaries = employeeSalaryRepository.findByEmployee(employee);
                System.out.println("=================================================");
                return employeeSalaries;
            }
            catch(Exception ex) {
                System.out.println("************************************************");
                ex.printStackTrace();
            }

        }
//        System.out.println(optionalSalary);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        return employeeSalaries;
    }

//    public Optional<List<EmployeeSalary>> getSalaryHistory(Integer facultyId) {
//        // Extract faculty ID from token
//        // Fetch salary history for the faculty
//        return employeeSalaryRepository.findAllByEmployeeId(facultyId);
//    }
}
