package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.model.WatchlistMovie;

@Repository
public interface WatchlistMovieRepository extends JpaRepository<WatchlistMovie, Long>{

}