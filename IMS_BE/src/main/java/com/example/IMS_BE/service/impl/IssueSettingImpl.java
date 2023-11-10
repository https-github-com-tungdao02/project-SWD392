package com.example.IMS_BE.service.impl;


import com.example.IMS_BE.entity.IssueSetting;
import com.example.IMS_BE.entity.Project;
import com.example.IMS_BE.repository.IIssueSettingRepository;
import com.example.IMS_BE.service.IssueSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class IssueSettingImpl implements IssueSettingService {

    @Autowired
    IIssueSettingRepository issueSettingRepository;

    @Override
    public List<IssueSetting> getAllIssueSetting() {
        return issueSettingRepository.findAll();
    }

    @Override
    public void addIssueSetting(IssueSetting issueSetting) {

    }

    @Override
    public void saveIssueSetting(IssueSetting issueSetting) {
        issueSettingRepository.save(issueSetting);
    }

    @Override
    public void deleteIssueSettingById(Long id) {
        issueSettingRepository.deleteById(id);
    }

    @Override
    public void updateIssueSetting(IssueSetting issueSetting) {

    }

    @Override
    public Optional<IssueSetting> findIssueSettingById(Long id) {
        return issueSettingRepository.findById(id);
    }

//    @Override
//    public List<IssueSetting> getIsssueSettingByTypeAndProject( Project project,String type) {
//       // return issueSettingRepository.findAllByProjectAndType(project,type);
//       return null;
//    }

    @Override
    public List<IssueSetting> getIsssueSettingByTypeAndProject(Long projectid, String type) {
        return issueSettingRepository.findAllByProjectAndType(projectid,type);
    }


}
