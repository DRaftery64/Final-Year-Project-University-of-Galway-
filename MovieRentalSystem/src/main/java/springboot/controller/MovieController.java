package springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springboot.model.Movie;
import springboot.service.MovieService;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/movies")
	public String viewHomePage(Model model) {
		return findPaginated(1, "dueDate", "asc", model);
	}

	@GetMapping("/showNewMovieForm")
	public String showAddMovieForm(Model model) {
		Movie movie = new Movie();
		model.addAttribute("movie", movie);
		return "new_movie";
	}

	@PostMapping("/saveMovie")
	public String saveMovie(@ModelAttribute("movie") Movie movie) {
		movieService.saveMovie(movie);
		return "redirect:/movies";
	}

	@GetMapping("/showMovieFormForUpdate/{movie_id}")
	public String showMovieFormForUpdate(@PathVariable(value = "movie_id") long movie_id, Model model) {

		Movie movie = movieService.getMovieById(movie_id);

		model.addAttribute("movie", movie);
		return "update_movie";
	}

	@GetMapping("/deleteMovie/{movie_id}")
	public String deleteMovie(@PathVariable(value = "movie_id") long movie_id) {

		this.movieService.deleteMovieById(movie_id);
		return "redirect:/movies";
	}
	
	@GetMapping("/moviesPage/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 10;

		Page<Movie> page = movieService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Movie> listMovies = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalMovies", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listMovies", listMovies);
		return "list_movies";
	}
}