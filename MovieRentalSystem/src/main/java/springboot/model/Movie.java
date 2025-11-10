package springboot.model;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long movie_id;

	@Column(name = "movie_name")
	private String movieName;

	@Column(name = "movie_description")
	private String movieDescription;

	@Column(name = "due_Date")
	private String dueDate;

	private String countdown;

	public long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(long movie_id) {
		this.movie_id = movie_id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String string) {
		this.dueDate = string;
	}

	public String getCountdown() {
		String noSpaces = getDueDate().replace(" ", "T");
		LocalDateTime dateTime = LocalDateTime.parse(noSpaces);
		LocalDateTime now = LocalDateTime.now();
		Duration myD = Duration.between(dateTime, now);
		String myString = "";
		if (myD.toDays() < 0) {
			myString = Long.toString(myD.abs().toDays());
			myString += " days";
		} else if (myD.toHours() < 0) {
			myString = Long.toString(myD.abs().toHours());
			myString += " hours";

		} else if (myD.toMinutes() < 0) {
			myString = Long.toString(myD.abs().toMinutes());
			myString += " minutes";

		} else {
			myString = "Overdue";
		}
		return myString;
	}

	public void setCountdown(String countdown) {
		this.countdown = countdown;
	}
}