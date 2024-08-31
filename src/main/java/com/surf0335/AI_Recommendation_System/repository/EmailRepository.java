package com.surf0335.AI_Recommendation_System.repository;

import com.surf0335.AI_Recommendation_System.model.Course;
import com.surf0335.AI_Recommendation_System.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email,String> {

    @Query(value = "select * from email where md5 = :md5",nativeQuery = true)
    List<Email> findDuplicateMail(@Param("md5") String md5);

}
