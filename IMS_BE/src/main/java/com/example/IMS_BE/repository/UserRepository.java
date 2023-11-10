package com.example.IMS_BE.repository;

import com.example.IMS_BE.entity.Setting;
import com.example.IMS_BE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query( value = "SELECT setting.name \n" +
            "FROM user\n" +
            "JOIN setting ON user.role_id = setting_id\n" +
            "where phone = ?1" , nativeQuery = true)
    String findRolesByPhone(String phone);

    @Query( value = "SELECT setting.name\n" +
            "            FROM user\n" +
            "            JOIN setting ON user.role_id = setting_id\n" +
            "            where email = ?1 " , nativeQuery = true)
    String findRolesByEmail(String email);

    User findById(int id);

    User findByPhoneAndAndEmail(String phone,String email);

    Optional<User> findUserByUsername(String username);

    User findByPhone(String phone);

    User findByEmail(String email);


    List<User> findAllByRole(Setting setting);
    User findByUsername(String username);
    List<User> findByRoleId(@Param("role_id")Long role_id);
}

