package springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "watchlistMovies")
public class WatchlistMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long watchlistMovie_id;

    @Column(name = "watchlistMovie_name")
    private String watchlistMovieName;
    
    public long getWatchlistMovie_id() {
        return watchlistMovie_id;
    }
    public void setWatchlistMovie_id(long watchlistMovie_id) {
        this.watchlistMovie_id = watchlistMovie_id;
    }
    public String getWatchlistMovieName() {
        return watchlistMovieName;
    }
    public void setWatchlistMovieName(String watchlistMovieName) {
        this.watchlistMovieName = watchlistMovieName;
    }
    
}