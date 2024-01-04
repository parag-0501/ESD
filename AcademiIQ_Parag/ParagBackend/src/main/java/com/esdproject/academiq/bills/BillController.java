package com.esdproject.academiq.bills;

import com.esdproject.academiq.stdpay.Stdpay;
import com.esdproject.academiq.stdpay.StdpayRepository;
import com.esdproject.academiq.students.Students;
import com.esdproject.academiq.students.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BillController {

    private final BillsRepository billRepository;
    private final StdpayRepository stdpayRepository;
    private final StudentsRepository studentsRepository;

    @GetMapping("/student/{studentId}/bills/pending")
    public List<Bills> getStudentBills(@PathVariable() Integer studentId) {
//        List<Bills> bills = new ArrayList<>();
//        return bills;
        Optional<Students> students = studentsRepository.findById(studentId);
        if(students.isEmpty()) {
            throw new RuntimeException(" Student Not Found");
        }
        return billRepository.findBillsByStudentAndStatus(students.get(), "Pending");
    }

    @PostMapping("/student/pay-bills")
    public ResponseEntity<String> payStudentBills(@RequestBody PayStudentBillsRequest billIds) {
        List<Bills> billsToPay = new ArrayList<>();

        for(int i = 0; i < billIds.getBillIds().size(); i++) {
            Optional<Bills> bill = billRepository.findById(billIds.getBillIds().get(i));

            if(bill.isEmpty()) continue;

            billsToPay.add(bill.get());
        }

        for (Bills bill : billsToPay) {
            bill.setStatus("Done");
        }

        List<Stdpay> stdpays = billsToPay.stream()
                .map(bill -> Stdpay.builder().student(bill.getStudent()).bill_id(bill).date(new Date()).build())
                .collect(Collectors.toList());

        stdpayRepository.saveAll(stdpays);
        billRepository.saveAll(billsToPay);

        return ResponseEntity.ok("Bills paid successfully and status updated to 'Done'");
    }
}

