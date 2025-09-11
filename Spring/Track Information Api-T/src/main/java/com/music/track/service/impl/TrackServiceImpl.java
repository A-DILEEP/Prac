package com.music.track.service.impl;

import com.music.track.dto.TrackRequest;
import com.music.track.model.Track;
import com.music.track.repository.TrackRepository;
import com.music.track.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class TrackServiceImpl implements TrackService {
    private final TrackRepository trackRepository;

    
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track createTrack(TrackRequest trackRequest) {
    	Track t=new Track();
    	t.setAlbumName(trackRequest.albumName());
    	t.setPlayCount(trackRequest.playCount());
    	t.setReleaseDate(trackRequest.releaseDate());
    	t.setTitle(trackRequest.title());
        return trackRepository.save(t);
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public void deleteTrack(Long trackId) {
    	trackRepository.deleteById(trackId);

    }

    @Override
    public Track getTrackByTitle(String title)  {
        return trackRepository.findByTitle(title);
        }

}
