package com.jobportal.backend.service;

import com.jobportal.backend.model.Application;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ApplicationService {
    private final List<Application> applications = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public Application submit(Application application) {
        application.setId(counter.getAndIncrement());
        applications.add(application);
        return application;
    }

    public List<Application> all() {
        return applications;
    }
}
