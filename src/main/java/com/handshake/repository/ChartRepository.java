package com.handshake.repository;

import com.handshake.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChartRepository extends JpaRepository<Chart, Long> {
    
    List<Chart> findByMusicIdOrderBySequence(Long musicId);
    
    List<Chart> findByMusicId(Long musicId);
}