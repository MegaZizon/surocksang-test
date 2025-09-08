package com.handshake.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "music")
@Getter
@Setter
@NoArgsConstructor
public class Music {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false, length = 50)
    private String singer;
    
    @Column(nullable = false)
    private LocalTime songTime;
    
    @Column(length = 63)
    private String albumImageUrl;
    
    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chart> charts;
}