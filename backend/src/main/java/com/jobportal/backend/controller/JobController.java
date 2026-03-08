package com.jobportal.backend.controller;

import com.jobportal.backend.model.Job;
import com.jobportal.backend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> allJobs() {
        return jobService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> jobById(@PathVariable Long id) {
        return jobService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Job not found")));
    }

    @PostMapping
    public ResponseEntity<Job> create(@Valid @RequestBody Job job) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.create(job));
    }
}
