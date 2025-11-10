package springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import springboot.model.WatchlistMovie;

public interface WatchlistMovieService {
 List<WatchlistMovie> getAllWatchlistMovies();
 void saveWatchlistMovie(WatchlistMovie watchlistMovie);
 WatchlistMovie getWatchlistMovieById(long watchlistMovie_id);
 void deleteWatchlistMovieById(long watchlistMovie_id);
 Page<WatchlistMovie> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}