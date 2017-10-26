package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Job;

import java.util.List;

public interface JobService {
    public List<Job> getAll();
    public void addJob(Job job);
    public void removeJob(Job job);
    public  Job getJob(long id);
}
