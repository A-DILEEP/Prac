package com.example.phase2ex4.Controller;

import java.util.*;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.phase2ex4.Service.ArtistService;
import com.example.phase2ex4.dto.ArtistRequestDto;
import com.example.phase2ex4.dto.ArtistResponseDto;


	 
	import java.util.List;
	 
	@RestController
	@RequestMapping("/v1/artists")
	public class ArtistController {
	 
	    private final ArtistService artistService;
	 
	    public ArtistController(ArtistService artistService) {
	        this.artistService = artistService;
	    }
	 
	    // Create new artist
	    @PostMapping
	    public ResponseEntity<ArtistResponseDto> createArtist(@RequestBody ArtistRequestDto artistDto) {
	        ArtistResponseDto savedArtist = artistService.createArtist(artistDto);
	        return ResponseEntity.status(201).body(savedArtist);
	    }
	 
	    // Get all artists
	    @GetMapping
	    public ResponseEntity<List<ArtistResponseDto>> getAllArtists() {
	        return ResponseEntity.ok(artistService.getAllArtists());
	    }
	 
	    // Get artist by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<ArtistResponseDto> getArtistById(@PathVariable Long id) {
	        return artistService.getArtistById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.ok(null)); // requirement: return null if not found
	    }
	 
	    // Delete artist
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
	        if (artistService.deleteArtist(id)) {
	            return ResponseEntity.noContent().build(); // 204
	        }
	        return ResponseEntity.notFound().build(); // 404 if not exists
	    }
	}
	 