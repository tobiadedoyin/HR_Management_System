package com.progrd.HR_MANAGEMENT_SYSTEM.controller;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Deduction;
import com.progrd.HR_MANAGEMENT_SYSTEM.exception.Exception;
import com.progrd.HR_MANAGEMENT_SYSTEM.service.DeductionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DeductionController {

        private final DeductionService deductionService;

        public DeductionController(DeductionService deductionService) {
            this.deductionService = deductionService;
        }

        @GetMapping("deduction")
        public List<Deduction> getAllDeductions() {
            return deductionService.getAllDeductions();
        }

        @GetMapping("deduction/{deductionId}")
        public Deduction getDeductionById(@PathVariable Long deductionId) {
            return deductionService.getDeductionById(deductionId);
        }

        @PostMapping("deduction/calculate")
        public ResponseEntity<Map<String, String>> calculateDeduction(
                @RequestParam Long employeeId,
                @RequestParam String startDate,
                @RequestParam String endDate) {
            try {
                LocalDate start = LocalDate.parse(startDate);
                LocalDate end = LocalDate.parse(endDate);
                return deductionService.calculateDeduction(employeeId, start, end);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error calculating deduction"));
            }
        }

        @PostMapping
        public Deduction createDeduction(@RequestBody Deduction deduction) {
            return deductionService.createDeduction(deduction);
        }

        @DeleteMapping("/{deductionId}")
        public void deleteDeduction(@PathVariable Long deductionId) {
            deductionService.deleteDeduction(deductionId);
        }
    }

