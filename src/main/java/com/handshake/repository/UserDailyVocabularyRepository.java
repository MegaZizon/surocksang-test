package com.handshake.repository;

import com.handshake.entity.UserDailyVocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDailyVocabularyRepository extends JpaRepository<UserDailyVocabulary, Long> {
    
    List<UserDailyVocabulary> findByUserId(Long userId);
    
    List<UserDailyVocabulary> findByUserIdAndCompleted(Long userId, Boolean completed);
    
    Optional<UserDailyVocabulary> findByUserIdAndDailyId(Long userId, Long dailyId);
    
    boolean existsByUserIdAndDailyId(Long userId, Long dailyId);
}