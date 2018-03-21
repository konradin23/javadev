package com.example.javadev.repository;

import com.example.javadev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    @Query(nativeQuery = true, value="SELECT s.email FROM user_roles s WHERE s.role ='ROLE_USER'")
    List<String> findOnlyStudentEmails();

    @Query(nativeQuery = true, value="SELECT s.email FROM user_roles s WHERE s.role ='ROLE_ADMIN'")
    List<String> findOnlyAdminEmails();

    @Query(nativeQuery = true, value="SELECT concat(s.firstname, ' ', s.lastname) FROM users s WHERE s.email = :email")
    String findStudentsNameByEmail(@Param("email") String email);





}
