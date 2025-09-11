package com.example.phase2ex4.mapper;

import com.example.phase2ex4.Model.Artist;
import com.example.phase2ex4.dto.ArtistRequestDto;
import com.example.phase2ex4.dto.ArtistResponseDto;

public class ArtistMapper {
	

	public static Artist toEntity(ArtistRequestDto dto) {
        Artist artist = new Artist();
        artist.setFirstname(dto.getFirstName());
        artist.setLastname(dto.getLastName());
        return artist;
    }
 
    public static ArtistResponseDto toResponseDto(Artist artist) {
        return new ArtistResponseDto(artist.getId(), artist.getLastname(), artist.getFirstname());
    }
}

