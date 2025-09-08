package com.handshake.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "daily")
@Getter
@Setter
@NoArgsConstructor
public class Daily {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Integer day;
    
    @Column
    private String sentence;
    
    @OneToMany(mappedBy = "daily", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DailyVocabulary> dailyVocabularies;
    
    @OneToMany(mappedBy = "daily", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserDailyVocabulary> userDailyVocabularies;
}