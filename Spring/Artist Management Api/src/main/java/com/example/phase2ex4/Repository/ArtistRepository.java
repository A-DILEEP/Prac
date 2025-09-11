package com.example.phase2ex4.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.phase2ex4.Model.Artist;
import java.util.*;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    
    
}
 
