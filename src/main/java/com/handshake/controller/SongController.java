package com.handshake.controller;

import com.handshake.service.SongDownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    
    private final SongDownloadService songDownloadService;
    
    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadSong(@PathVariable String fileName) {
        try {
            InputStream songStream = songDownloadService.downloadSong(fileName);
            
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(songStream));
                    
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/stream/{fileName}")
    public ResponseEntity<InputStreamResource> streamSong(@PathVariable String fileName) {
        try {
            InputStream songStream = songDownloadService.downloadSong(fileName);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(songStream));
                    
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}