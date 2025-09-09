package com.surocksang.controller;

import com.surocksang.dto.ChartDto;
import com.surocksang.dto.MusicChartResponse;
import com.surocksang.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/charts")
@RequiredArgsConstructor
public class ChartController {
    
    private final ChartService chartService;
    
    @GetMapping("/music/{musicId}")
    public ResponseEntity<MusicChartResponse> getMusicWithCharts(@PathVariable Long musicId) {
        try {
            MusicChartResponse response = chartService.getMusicWithCharts(musicId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/music/{musicId}/charts-only")
    public ResponseEntity<List<ChartDto>> getChartsByMusicId(@PathVariable Long musicId) {
        try {
            List<ChartDto> charts = chartService.getChartsByMusicId(musicId);
            return ResponseEntity.ok(charts);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}