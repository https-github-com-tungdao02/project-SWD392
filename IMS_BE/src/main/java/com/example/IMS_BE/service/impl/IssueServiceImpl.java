package com.example.IMS_BE.service.impl;

import com.example.IMS_BE.entity.Issue;
import com.example.IMS_BE.entity.User;
import com.example.IMS_BE.repository.*;
import com.example.IMS_BE.service.IssueService;
import com.example.IMS_BE.utils.PieChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    IClassesRepository classesRepository;
    @Autowired
    IMilestoneRepository milestoneRepository;
    @Autowired
    IIssueSettingRepository issueSettingRepository;

    @Override
    public List<Issue> getAllIssue() {
        return issueRepository.findAll() ;
    }

    @Override
    public List<Issue> getAllIssueByAssignee(User assignee) {

        return issueRepository.findAllByAssignee(assignee);
    }
    @Override
    public void addIssue(Issue issue) {
        issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Issue issue) {
        issueRepository.delete(issue);
    }

    @Override
    public void updateIssue(Issue issue) {
        issueRepository.save(issue);
    }

    @Override
    public Issue getIssueById(int id) {
        Optional<Issue> issue= issueRepository.findById((long)id);
        if(issue!=null){
            return  issue.get();
        }
        return null;
    }

    @Override
    public PieChartData generateClassIssueStatistics(Long assigneeId) {

        List<Object[]> statistics = issueRepository.getClassStatisticsByAssignee(assigneeId);

        List<String> labels = new ArrayList<>();
        List<Float> data = new ArrayList<>();

        for (Object[] stat : statistics) {
            long id=Long.valueOf(stat[0].toString());
            String name=classesRepository.findById(id).get().getName();
            labels.add(name);
            data.add(Float.parseFloat(stat[1].toString()));
        }

        return new PieChartData(labels, data);
    }

    @Override
    public Long countIssueByStudent(Long id) {
        return issueRepository.countIssuesByAssigneeId(id);
    }

    @Override
    public PieChartData countIssueMilestoneByStudentAndProject(Long id, Long projectId) {
        List<Object[]> statistics = issueRepository.countMilestonesPercentage(id,projectId);

        List<String> labels = new ArrayList<>();
        List<Float> data = new ArrayList<>();

        for (Object[] stat : statistics) {
            long uid=Long.valueOf(stat[0].toString());
            String name=milestoneRepository.findById(uid).get().getName();
            labels.add(name);
            data.add(Float.parseFloat(stat[1].toString()));
        }

        return new PieChartData(labels, data);

    }

    @Override
    public PieChartData countIssueWorkProcessByStudentAndProject(Long id, Long projectId) {
        List<Object[]> statistics = issueRepository.countByWorkProcessId(id,projectId);

        List<String> labels = new ArrayList<>();
        List<Float> data = new ArrayList<>();

        for (Object[] stat : statistics) {
            long uid=Long.valueOf(stat[0].toString());
            String name=issueSettingRepository.findById(uid).get().getName();
            labels.add(name);
            data.add(Float.parseFloat(stat[1].toString()));
        }

        return new PieChartData(labels, data);
    }

    @Override
    public PieChartData countIssueStatusByStudentAndProject(Long id, Long projectId) {
        List<Object[]> statistics = issueRepository.countByStatusId(id,projectId);

        List<String> labels = new ArrayList<>();
        List<Float> data = new ArrayList<>();

        for (Object[] stat : statistics) {
            long uid=Long.valueOf(stat[0].toString());
            String name=issueSettingRepository.findById(uid).get().getName();
            labels.add(name);
            data.add(Float.parseFloat(stat[1].toString()));
        }

        return new PieChartData(labels, data);
    }

    @Override
    public PieChartData countIssueTypeByStudentAndProject(Long id, Long projectId) {
        List<Object[]> statistics = issueRepository.countByTypeId(id,projectId);

        List<String> labels = new ArrayList<>();
        List<Float> data = new ArrayList<>();

        for (Object[] stat : statistics) {
            long uid=Long.valueOf(stat[0].toString());
            String name=issueSettingRepository.findById(uid).get().getName();
            labels.add(name);
            data.add(Float.parseFloat(stat[1].toString()));
        }

        return new PieChartData(labels, data);
    }

    @Override
    public List<Issue> countByAssinger(Long assignerId) {
        return issueRepository.findByAssignerId(assignerId);
    }

    @Override
    public List<Issue> countByAssingee(Long assignerId) {
        return issueRepository.findByAssigneeId(assignerId);
    }
}

