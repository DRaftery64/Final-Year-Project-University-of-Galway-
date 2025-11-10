package springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import springboot.model.Movie;
import springboot.model.WatchlistMovie;
import springboot.service.MovieService;
import springboot.service.WatchlistMovieService;

@Controller
public class MainController {

	@Autowired
	private MovieService movieService;
	@Autowired
	private WatchlistMovieService watchlistMovieService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		Page<Movie> page = movieService.findPaginated(1, 6, "dueDate", "asc");
		List<Movie> listMovies = page.getContent();
		model.addAttribute("listMovies", listMovies);
		
		Page<WatchlistMovie> watchlistMoviePage = watchlistMovieService.findPaginated(1, 6, "watchlistMovieName", "asc");
		List<WatchlistMovie> listWatchlistMovies = watchlistMoviePage.getContent();
		model.addAttribute("listWatchlistMovies", listWatchlistMovies);
		return "index";
	}
}