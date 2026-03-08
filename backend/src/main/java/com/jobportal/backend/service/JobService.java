package com.jobportal.backend.service;

import com.jobportal.backend.model.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class JobService {
    private final List<Job> jobs = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public JobService() {
        jobs.add(new Job(counter.getAndIncrement(), "Java Backend Developer", "Acme Corp", "Remote", "Build and maintain REST APIs."));
        jobs.add(new Job(counter.getAndIncrement(), "Frontend Developer", "Nova Tech", "Bangalore", "Develop responsive UI and integrate APIs."));
    }

    public List<Job> findAll() {
        return jobs;
    }

    public Optional<Job> findById(Long id) {
        return jobs.stream().filter(job -> job.getId().equals(id)).findFirst();
    }

    public Job create(Job job) {
        job.setId(counter.getAndIncrement());
        jobs.add(job);
        return job;
    }
}
