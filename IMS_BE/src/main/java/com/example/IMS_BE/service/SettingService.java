package com.example.IMS_BE.service;

import com.example.IMS_BE.entity.Setting;

import java.util.List;
import java.util.Optional;

public interface SettingService {
    List<Setting> getAllSettings();

    Setting save(Setting entity);

    List<Setting> saveAll(List<Setting> entities);

    Optional<Setting> findById(Long aLong);

    List<Setting> findAll();

    List<Setting> findAllById(List<Long> longs);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);

    void delete(Setting entity);

    void deleteAll(List<Setting> entities);

    void deleteAll();

    List<Setting> findAllByType1(String type);
    Setting updateSetting(Long id, Setting updatedSetting);

    Setting getSettingById(Long id);

    Setting saveSetting(Setting setting);

    void deleteSetting(Long id);

    List<String> getAllTypeOptions();

    void deleteSettingById(Long id);

    public List<String> getAllTypes();

    public void deleteSelectedSettings(List<Long> ids);

}
