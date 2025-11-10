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

import springboot.model.WatchlistMovie;
import springboot.service.WatchlistMovieService;

@Controller
public class WatchlistMovieController {

	@Autowired
	private WatchlistMovieService watchlistMovieService;

	@GetMapping("/watchlistMovies")
	public String viewHomePage(Model model) {
		return findPaginated(1, "watchlistMovieName", "asc", model);
	}

	@GetMapping("/showNewWatchlistMovieForm")
	public String showAddWatchlistMovieForm(Model model) {
		WatchlistMovie watchlistMovie = new WatchlistMovie();
		model.addAttribute("watchlistMovie", watchlistMovie);
		return "new_watchlistMovie";
	}

	@PostMapping("/saveWatchlistMovie")
	public String saveWatchlistMovie(@ModelAttribute("watchlistMovie") WatchlistMovie watchlistMovie) {
		watchlistMovieService.saveWatchlistMovie(watchlistMovie);
		return "redirect:/watchlistMovies";
	}

	@GetMapping("/showWatchlistMovieFormForUpdate/{watchlistMovie_id}")
	public String showWatchlistMovieFormForUpdate(@PathVariable(value = "watchlistMovie_id") long watchlistMovie_id, Model model) {

		WatchlistMovie watchlistMovie = watchlistMovieService.getWatchlistMovieById(watchlistMovie_id);

		model.addAttribute("watchlistMovie", watchlistMovie);
		return "update_watchlistMovie";
	}

	@GetMapping("/deleteWatchlistMovie/{watchlistMovie_id}")
	public String deleteWatchlistMovie(@PathVariable(value = "watchlistMovie_id") long watchlistMovie_id) {

		this.watchlistMovieService.deleteWatchlistMovieById(watchlistMovie_id);
		return "redirect:/watchlistMovies";
	}

	@GetMapping("/watchlistMoviesPage/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 10;

		Page<WatchlistMovie> page = watchlistMovieService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<WatchlistMovie> listWatchlistMovies = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalWatchlistMovies", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listWatchlistMovies", listWatchlistMovies);
		return "list_watchlistMovies";
	}
}