package springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import springboot.model.Movie;

public interface MovieService {
 List<Movie> getAllMovies();
 void saveMovie(Movie movie);
 Movie getMovieById(long movie_id);
 void deleteMovieById(long movie_id);
 Page<Movie> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}