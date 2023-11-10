package com.example.IMS_BE.service;

import com.example.IMS_BE.entity.Issue;
import com.example.IMS_BE.entity.User;
import com.example.IMS_BE.utils.PieChartData;

import java.util.List;
import java.util.Optional;


public interface IssueService {
    public List<Issue> getAllIssue();
    public List<Issue> getAllIssueByAssignee(User assignee);
    public void addIssue(Issue issue);
    public void deleteIssue(Issue issue);
    public void updateIssue(Issue issue);
    public Issue getIssueById(int id);
    public PieChartData generateClassIssueStatistics(Long assigneeId) ;
    public Long countIssueByStudent(Long id);
    public PieChartData countIssueMilestoneByStudentAndProject(Long id,Long projectId);
    public PieChartData countIssueWorkProcessByStudentAndProject(Long id,Long projectId);
    public PieChartData countIssueStatusByStudentAndProject(Long id,Long projectId);
    public PieChartData countIssueTypeByStudentAndProject(Long id,Long projectId);
    public List<Issue> countByAssinger(Long assignerId);
    public List<Issue> countByAssingee(Long assignerId);
}
