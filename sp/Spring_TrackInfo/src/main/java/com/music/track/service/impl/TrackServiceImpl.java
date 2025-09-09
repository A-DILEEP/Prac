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
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track createTrack(TrackRequest trackRequest) {
    	Track track=new Track();
    	track.setAlbumName(trackRequest.albumName());
    	track.setTitle(trackRequest.title());
    	track.setReleaseDate(trackRequest.releaseDate());
    	track.setPlayCount(trackRequest.playCount());
    	trackRepository.save(track);
        return track;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public void deleteTrack(Long trackId) {
        Track track = trackRepository.findById(trackId)
                .orElseThrow(() -> new RuntimeException("Track not found with id: " + trackId));
        trackRepository.delete(track);
    }


    @Override
    public Optional getTracksByTitle(String title)  {
        return trackRepository.findByTitle(title);
    }

}
