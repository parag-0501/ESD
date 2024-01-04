package com.esdproject.academiq.bills;

import com.esdproject.academiq.students.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillsRepository extends JpaRepository<Bills , Integer> {
    List<Bills> findBillsByStudentAndStatus(Students student, String status);
}
