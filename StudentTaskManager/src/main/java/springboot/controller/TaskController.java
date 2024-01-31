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

import springboot.model.Task;
import springboot.service.TaskService;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/tasks")
	public String viewHomePage(Model model) {
		return findPaginated(1, "dueDate", "asc", model);
	}

	@GetMapping("/showNewTaskForm")
	public String showAddTaskForm(Model model) {
		Task task = new Task();
		model.addAttribute("task", task);
		return "new_task";
	}

	@PostMapping("/saveTask")
	public String saveTask(@ModelAttribute("task") Task task) {
		taskService.saveTask(task);
		return "redirect:/tasks";
	}

	@GetMapping("/showTaskFormForUpdate/{task_id}")
	public String showTaskFormForUpdate(@PathVariable(value = "task_id") long task_id, Model model) {

		Task task = taskService.getTaskById(task_id);

		model.addAttribute("task", task);
		return "update_task";
	}

	@GetMapping("/deleteTask/{task_id}")
	public String deleteTask(@PathVariable(value = "task_id") long task_id) {

		this.taskService.deleteTaskById(task_id);
		return "redirect:/tasks";
	}
	
	@GetMapping("/tasksPage/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 10;

		Page<Task> page = taskService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Task> listTasks = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listTasks", listTasks);
		return "list_tasks";
	}
}