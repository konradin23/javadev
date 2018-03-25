package com.example.javadev.repository;

import com.example.javadev.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    @Query(nativeQuery = true, value="SELECT s.lecture_id FROM user_lecture s WHERE s.user_id = :user_id")
    List<Integer> findAllLectureIdsAttendedByStudent(@Param("user_id") int userId);

    @Query(nativeQuery = true, value="SELECT s.lecture_id FROM lecture s")
    List<Integer> getAllLecturesId();

    @Query(nativeQuery = true, value="SELECT s.user_id FROM user_lecture s WHERE s.lecture_id = :lecture_id")
    List<Integer> findAllStudentsIdsWhoAttendedLecture(@Param("lecture_id") int lectureId);

}


