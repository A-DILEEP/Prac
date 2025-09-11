package com.music.track.service.impl;

import com.music.track.dto.TrackRequest;
import com.music.track.model.Track;
import com.music.track.repository.TrackRepository;
import com.music.track.service.TrackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {
	
	@Autowired
	private TrackRepository repo;
    @Override
    public Track createTrack(TrackRequest trackRequest) {
    	Track t=new Track();
    	t.setAlbumName(trackRequest.albumName());
    	t.setPlayCount(trackRequest.playCount());
    	t.setReleaseDate(trackRequest.releaseDate());
    	t.setTitle(trackRequest.title());
        return repo.save(t);
    }
    @Override
    public List<Track> getAllTracks() {
        return repo.findAll();
    }

    @Override
    public void deleteTrack(Long trackId) {
    	repo.deleteById(trackId);
    }

    @Override
    public List<Track> sortedTracks() {
        return repo.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }
}
