package com.music.track.service;

import com.music.track.dto.TrackRequest;
import com.music.track.model.Track;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface TrackService {
    Track createTrack(TrackRequest trackRequest);

    Optional<Track> getTracksByTitle(String title);
    List<Track> getAllTracks();

    void deleteTrack(Long trackId);
}
