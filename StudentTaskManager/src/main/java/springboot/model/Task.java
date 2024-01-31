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
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long task_id;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "task_description")
	private String taskDescription;

	@Column(name = "due_Date")
	private String dueDate;

	private String countdown;

	public long getTask_id() {
		return task_id;
	}

	public void setTask_id(long task_id) {
		this.task_id = task_id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
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