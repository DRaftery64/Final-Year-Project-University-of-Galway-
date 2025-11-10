package springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import springboot.model.WatchlistMovie;
import springboot.repository.WatchlistMovieRepository;

@Service
public class WatchlistMovieServiceImpl implements WatchlistMovieService {

    @Autowired
    private WatchlistMovieRepository watchlistMovieRepository;

    @Override
    public List < WatchlistMovie > getAllWatchlistMovies() {
        return watchlistMovieRepository.findAll();
        
    }

	@Override
	public void saveWatchlistMovie(WatchlistMovie watchlistMovie) {
		this.watchlistMovieRepository.save(watchlistMovie );
	}
	
	@Override
	public WatchlistMovie getWatchlistMovieById(long watchlistMovie_id) {
	    Optional < WatchlistMovie > optional = watchlistMovieRepository.findById(watchlistMovie_id);
	    WatchlistMovie watchlistMovie = null;
	    if (optional.isPresent()) {
	        watchlistMovie = optional.get();
	    } else {
	        throw new RuntimeException(" Item not found for id :: " + watchlistMovie_id);
	    }
	    return watchlistMovie;
	}
	@Override
	public void deleteWatchlistMovieById(long watchlistMovie_id) {
	   this.watchlistMovieRepository.deleteById(watchlistMovie_id);
	}
	@Override
	public Page<WatchlistMovie> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
	 Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
	  Sort.by(sortField).descending();
	 
	 Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
	 return this.watchlistMovieRepository.findAll(pageable);
	}

	
}