package com.rhythmradar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    
    private final RhythmRadarApp app;

    @Autowired
    public ArtistController(RhythmRadarApp app) {
        this.app = app;
    }

    @GetMapping("/{artistId}/name")
    public String getArtistName(@PathVariable String artistId) {
        return app.getArtistName(artistId);
    }

    @GetMapping("/{artistId}/monthly-listeners")
    public int getMonthlyListeners(@PathVariable String artistId) {
        return app.getMonthlyListeners(artistId);
    }

    @GetMapping("/{artistId}/songs")
    public List<String> getSongs(@PathVariable String artistId) {
        return app.getSongs(artistId);
    }

    @GetMapping("/{artistId}/streaming-data")
    public Map<String, Object> getStreamingData(@PathVariable String artistId) {
        return app.getStreamingData(artistId);
    }

    @GetMapping("/{artistId}/social-media-data")
    public Map<String, Object> getSocialMediaData(@PathVariable String artistId) {
        return app.getSocialMediaData(artistId);
    }
}

