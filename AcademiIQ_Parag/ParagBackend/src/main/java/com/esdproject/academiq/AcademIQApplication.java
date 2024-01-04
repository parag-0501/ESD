package com.esdproject.academiq;

import com.esdproject.academiq.auth.AuthenticationService;
import com.esdproject.academiq.auth.RegisterRequest;
import com.esdproject.academiq.bills.Bills;
import com.esdproject.academiq.bills.BillsRepository;
import com.esdproject.academiq.stdpay.Stdpay;
import com.esdproject.academiq.stdpay.StdpayRepository;
import com.esdproject.academiq.students.Students;
import com.esdproject.academiq.students.StudentsRepository;
import com.esdproject.academiq.user.Role;
import com.esdproject.academiq.user.User;
import com.esdproject.academiq.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AcademIQApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademIQApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			UserRepository userRepository,
			StudentsRepository studentsRepository,
			BillsRepository billsRepository,
			StdpayRepository stdpayRepository,
			AuthenticationService authService
	) {

		return args -> {

			try {

				// Create users
				var user1 = RegisterRequest.builder()
						.firstname("Parag")
						.lastname("Sharma")
						.email("parag@gmail.com")
						.password("1234")
						.role(Role.STUDENT).build();

				var user2 = RegisterRequest.builder()
						.firstname("Jacob")
						.lastname("Mathew")
						.email("jacob@gmail.com")
						.password("1234")
						.role(Role.STUDENT).build();

				authService.register(user1);
				authService.register(user2);
				User user3 = userRepository.findById(1).orElse(null);
				User user4 = userRepository.findById(2).orElse(null);

				if(user3 != null && user4 != null) {

					// Create Students and associate with users
					Students student1 = Students.builder().roll_no(101).user(user3).build();
					Students student2 = Students.builder().roll_no(102).user(user4).build();

					studentsRepository.save(student1);
					studentsRepository.save(student2);

					//Create Bills
					Bills bill1 = Bills.builder().student(student1).dsc("1st bill").amount(500.99).date(java.sql.Date.valueOf(LocalDate.parse("2023-11-01"))).deadline(java.sql.Date.valueOf(LocalDate.parse("2023-12-01"))).status("Done").build();
					Bills bill2 = Bills.builder().student(student1).dsc("2nd bill").amount(750.99).date(java.sql.Date.valueOf(LocalDate.parse("2023-11-01"))).deadline(java.sql.Date.valueOf(LocalDate.parse("2023-12-01"))).status("Pending").build();
					Bills bill3 = Bills.builder().student(student1).dsc("3rd bill").amount(850.99).date(java.sql.Date.valueOf(LocalDate.parse("2023-11-01"))).deadline(java.sql.Date.valueOf(LocalDate.parse("2023-12-01"))).status("Pending").build();
					Bills bill4 = Bills.builder().student(student1).dsc("4th bill").amount(950.99).date(java.sql.Date.valueOf(LocalDate.parse("2023-11-01"))).deadline(java.sql.Date.valueOf(LocalDate.parse("2023-12-01"))).status("Pending").build();
					Bills bill5 = Bills.builder().student(student1).dsc("5th bill").amount(150.99).date(java.sql.Date.valueOf(LocalDate.parse("2023-11-01"))).deadline(java.sql.Date.valueOf(LocalDate.parse("2023-12-01"))).status("Pending").build();

					Bills bill6 = Bills.builder().student(student2).dsc("6th bill").amount(500.99).date(java.sql.Date.valueOf(LocalDate.parse("2023-11-01"))).deadline(java.sql.Date.valueOf(LocalDate.parse("2023-12-01"))).status("Done").build();
					Bills bill7 = Bills.builder().student(student2).dsc("7th bill").amount(750.99).date(java.sql.Date.valueOf(LocalDate.parse("2023-11-01"))).deadline(java.sql.Date.valueOf(LocalDate.parse("2023-12-01"))).status("Pending").build();
					Bills bill8 = Bills.builder().student(student2).dsc("8th bill").amount(850.99).date(java.sql.Date.valueOf(LocalDate.parse("2023-11-01"))).deadline(java.sql.Date.valueOf(LocalDate.parse("2023-12-01"))).status("Pending").build();
					Bills bill9 = Bills.builder().student(student2).dsc("9th bill").amount(950.99).date(java.sql.Date.valueOf(LocalDate.parse("2023-11-01"))).deadline(java.sql.Date.valueOf(LocalDate.parse("2023-12-01"))).status("Pending").build();
					Bills bill10 = Bills.builder().student(student2).dsc("10th bill").amount(150.99).date(java.sql.Date.valueOf(LocalDate.parse("2023-11-01"))).deadline(java.sql.Date.valueOf(LocalDate.parse("2023-12-01"))).status("Pending").build();


					billsRepository.save(bill1);
					billsRepository.save(bill2);
					billsRepository.save(bill3);
					billsRepository.save(bill4);
					billsRepository.save(bill5);

					billsRepository.save(bill6);
					billsRepository.save(bill7);
					billsRepository.save(bill8);
					billsRepository.save(bill9);
					billsRepository.save(bill10);



					// Create Stdpay and associate with Students and Bills
					Stdpay stdpay1 = Stdpay.builder().student(student1).bill_id(bill1).date(java.sql.Date.valueOf(LocalDate.parse("2023-11-15"))).build();
					Stdpay stdpay2 = Stdpay.builder().student(student2).bill_id(bill6).date(java.sql.Date.valueOf(LocalDate.parse("2023-11-15"))).build();

					stdpayRepository.save(stdpay1);
					stdpayRepository.save(stdpay2);

				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		};
	}
}
