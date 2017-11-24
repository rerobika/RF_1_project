package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.Job;
import io.github.rerobika.rf1.repository.JobRepository;
import io.github.rerobika.rf1.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobRepository jobRepository;
    @Override
    public List<Job> getAll() {
        return null;
    }

    @Override
    public void addJob(Job job) {
        jobRepository.save(job);

    }

    @Override
    public void removeJob(Job job) {

    }

    @Override
    public Job getJob(long id) {
        return null;
    }

    @Override
    public Job getJobByName(String name) {
        return jobRepository.findByName(name);
    }
}
