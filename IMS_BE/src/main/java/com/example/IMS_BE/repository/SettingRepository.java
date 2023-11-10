package com.example.IMS_BE.repository;

import java.util.*;
import com.example.IMS_BE.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {
    public List<Setting> findByType(String type);
    @Query("SELECT s.type FROM Setting s WHERE s.id = :id")

    Setting findBySettingId( @Param("id")Long id);

    @Query(value = "select DISTINCT type  from setting", nativeQuery = true)
    List<String> findAllTypes();
    void deleteAllByIdIn(List<Long> ids);
}
