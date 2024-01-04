package com.esdproject.academiq;

import com.esdproject.academiq.auth.AuthenticationService;
import com.esdproject.academiq.auth.RegisterRequest;
import com.esdproject.academiq.department.Department;
import com.esdproject.academiq.department.DepartmentRepository;
import com.esdproject.academiq.employee.Employee;
import com.esdproject.academiq.employee.EmployeeRepository;
import com.esdproject.academiq.employee.EmployeeSalary;
import com.esdproject.academiq.employee.EmployeeSalaryRepository;
import com.esdproject.academiq.user.Role;
import com.esdproject.academiq.user.User;
import com.esdproject.academiq.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AcademIQApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademIQApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			UserRepository userRepository,
			DepartmentRepository departmentRepository,
			EmployeeSalaryRepository employeeSalaryRepository,
			EmployeeRepository employeeRepository,
			AuthenticationService authService
	) {

		return args -> {

			try {

				//Create departments
				Department department1 = Department.builder().name("CSE").capacity(50).build();
				Department department2 = Department.builder().name("ESE").capacity(30).build();

				departmentRepository.save(department1);
				departmentRepository.save(department2);

				// Create users
				var user1 = RegisterRequest.builder()
						.firstname("Parag")
						.lastname("Sharma")
						.email("parag@gmail.com")
						.password("1234")
						.role(Role.EMPLOYEE).build();

				var user2 = RegisterRequest.builder()
						.firstname("Jacob")
						.lastname("Mathew")
						.email("jacob@gmail.com")
						.password("1234")
						.role(Role.EMPLOYEE).build();

				authService.register(user1);
				authService.register(user2);
				User user3 = userRepository.findById(1).orElse(null);
				User user4 = userRepository.findById(2).orElse(null);
				if(user3 != null && user4 != null) {
					// Create employees and associate with departments and users
					Employee employee1 = Employee.builder().title("Professor").photographPath("/path/to/photo1.jpg").user(user3).department(department1).build();
//					employee1.setDepartment(department1);
					Employee employee2 = Employee.builder().title("Assistant Professor").photographPath("/path/to/photo2.jpg").user(user4).department(department2).build();
//					employee2.setDepartment(department2);

					employeeRepository.save(employee1);
					employeeRepository.save(employee2);

					// Example employeeSalary creation
					EmployeeSalary salary1 = EmployeeSalary.builder().paymentDate(new Date()).amount(BigDecimal.valueOf(5000))
							.description("Monthly salary").employee(employee1).build();
					EmployeeSalary salary2 = EmployeeSalary.builder().paymentDate(new Date()).amount(BigDecimal.valueOf(6000))
							.description("Monthly salary").employee(employee2).build();

					// Save employee salaries
					employeeSalaryRepository.save(salary1);
					employeeSalaryRepository.save(salary2);
				}

				// Save employees
				// (Remember to save employeeSalary entries if needed)


			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		};
	}
}
