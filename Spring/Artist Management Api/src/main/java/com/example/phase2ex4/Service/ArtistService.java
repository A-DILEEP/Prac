package com.example.phase2ex4.Service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.phase2ex4.Model.Artist;
import com.example.phase2ex4.Repository.ArtistRepository;
import com.example.phase2ex4.dto.ArtistRequestDto;
import com.example.phase2ex4.dto.ArtistResponseDto;
import com.example.phase2ex4.mapper.ArtistMapper;


@Service
public class ArtistService {
	private final ArtistRepository artistRepository;
	 
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }
 
    public ArtistResponseDto createArtist(ArtistRequestDto requestDto) {
        Artist artist = ArtistMapper.toEntity(requestDto);
        Artist saved = artistRepository.save(artist);
        return ArtistMapper.toResponseDto(saved);
    }
 
    public List<ArtistResponseDto> getAllArtists() {
        return artistRepository.findAll()
                .stream()
                .sorted((a, b) -> a.getId().compareTo(b.getId()))
                .map(ArtistMapper::toResponseDto)
                .toList();
    }
 
    public Optional<ArtistResponseDto> getArtistById(Long id) {
        return artistRepository.findById(id)
                .map(ArtistMapper::toResponseDto);
    }
 
    public boolean deleteArtist(Long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

