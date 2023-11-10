package com.example.IMS_BE.service.impl;

import com.example.IMS_BE.entity.Setting;
import com.example.IMS_BE.repository.SettingRepository;
import com.example.IMS_BE.service.SettingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    SettingRepository settingRepository;
    @Override
    public List<Setting> getAllSettings() {
        return (List<Setting>) settingRepository.findAll();
    }

    @Override
    public Setting save(Setting entity) {
        return settingRepository.save(entity);
    }

    @Override
    public List<Setting> saveAll(List<Setting> entities) {
        return (List<Setting>)settingRepository.saveAll(entities);
    }

    @Override
    public Optional<Setting> findById(Long aLong) {
        return settingRepository.findById(aLong);
    }

    @Override
    public List<Setting> findAll() {
        return (List<Setting>)settingRepository.findAll();
    }

    @Override
    public List<Setting> findAllByType1(String type){
        return (List<Setting>)settingRepository.findByType(type);
    }
    @Override
    public Setting updateSetting(Long id, Setting updatedSetting) {
        Setting existingSetting = settingRepository.findById(id).orElse(null);
        if (existingSetting != null) {
            existingSetting.setName(updatedSetting.getName());
            existingSetting.setType(updatedSetting.getType());
            return settingRepository.save(existingSetting);
        }
        return null;
    }

    public List<String> getAllTypes() {
        return settingRepository.findAllTypes();
    }
    @Transactional
    public void deleteSelectedSettings(List<Long> ids) {
        settingRepository.deleteAllByIdIn(ids);
    }


    @Override
    public Setting getSettingById(Long id) {
        return settingRepository.findById(id).orElse(null);
    }
    @Override
    public Setting saveSetting(Setting setting) {
        return settingRepository.save(setting);
    }
    @Override
    public void deleteSetting(Long id) {
        settingRepository.deleteById(id);
    }

    @Override
    public List<String> getAllTypeOptions() {
        // Thực hiện truy vấn để lấy danh sách tùy chọn "Type" từ cơ sở dữ liệu
        return settingRepository.findAllTypes();
    }

    @Override
    public void deleteSettingById(Long id) {
        settingRepository.deleteById(id);
    }


    @Override
    public List<Setting> findAllById(List<Long> longs) {
        return (List<Setting>)settingRepository.findAllById(longs);
    }

    @Override
    public boolean existsById(Long aLong) {
        return settingRepository.existsById(aLong);
    }

    @Override
    public long count() {
        return settingRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        settingRepository.deleteById(aLong);
    }

    @Override
    public void delete(Setting entity) {
        settingRepository.delete(entity);
    }

    @Override
    public void deleteAll(List<Setting> entities) {
        settingRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        settingRepository.deleteAll();
    }
}
