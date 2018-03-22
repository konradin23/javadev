package com.example.javadev.repository;

import com.example.javadev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    User findByEmail(String email);
    User findByUserId(int userId);

    @Query(nativeQuery = true, value="SELECT user_id FROM user s WHERE s.email = :email")
    int findStudentsIdByEmail(@Param("email") String email);

    @Query(nativeQuery = true, value="SELECT s.user_id FROM user_role s WHERE s.role_id ='2'")
    List<Integer> findOnlyStudentIds();

    @Query(nativeQuery = true, value="SELECT s.user_id FROM user_role s WHERE s.role_id ='1'")
    List<String> findOnlyAdminIds();

    @Query(nativeQuery = true, value="SELECT s.email FROM user s WHERE s.user_id = :user_id")
    String findStudentsEmailByUserId(@Param("user_id") int userId);

    @Query(nativeQuery = true, value="SELECT concat(s.firstname, ' ', s.lastname) FROM user s WHERE s.user_id = :user_id")
    String findStudentsNameByUserId(@Param("user_id") int userId);

    @Query(nativeQuery = true, value="SELECT s.lastname FROM user s WHERE s.email = :email")
    String findUserLastnameById(@Param("email") String email);

    @Query(nativeQuery = true, value="SELECT s.firstname FROM user s WHERE s.email = :email")
    String findStudentsFirstNameByEmail(@Param("email") String email);

    User findUserByUserId(int userId);








}
