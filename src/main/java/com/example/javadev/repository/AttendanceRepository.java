package com.example.javadev.repository;

import com.example.javadev.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    @Query(nativeQuery = true, value="SELECT * FROM attendance a WHERE a.user_id = :user_id")
    List<Attendance> findAllAttendanceByStudent(@Param("user_id") int userId);

    @Query(nativeQuery = true, value="SELECT * FROM attendance a WHERE a.user_id = :user_id and a.lecture_id=:lecture_id")
    Attendance findAttendanceByUserIdAndLectureId(@Param("user_id") int userId, @Param("lecture_id") int lectureId);

}
