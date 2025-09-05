package com.music.track.service.impl;

import com.music.track.dto.TrackRequest;
import com.music.track.model.Track;
import com.music.track.repository.TrackRepository;
import com.music.track.service.TrackService;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.music.track.model.Track;
import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {
	private TrackRepository trackRepository;
	public TrackServiceImpl(TrackRepository trackRepository) {
		this.trackRepository=trackRepository;
	}
    @Override
    public Track createTrack(TrackRequest trackRequest) {
    	Track trak=new Track();
    	trak.setAlbumName(trackRequest.albumName());
    	trak.setTitle(trackRequest.title());
    	trak.setReleaseDate(trackRequest.releaseDate());
    	trak.setPlayCount(trackRequest.playCount());
    	trackRepository.save(trak);
        return trak; 
    }
    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public void deleteTrack(Long trackId) {
    	Optional<Track> track=trackRepository.findById(trackId);
    		trackRepository.delete(track.get());
    }

    @Override
    public List<Track> sortedTracks() {
        return trackRepository.findAll(Sort.by(Sort.Direction.ASC,"title"));
    }
}
