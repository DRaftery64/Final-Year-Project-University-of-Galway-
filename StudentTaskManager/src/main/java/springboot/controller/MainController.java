package springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import springboot.model.Task;
import springboot.model.Item;
import springboot.service.ItemService;
import springboot.service.TaskService;

@Controller
public class MainController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private ItemService itemService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		Page<Task> page = taskService.findPaginated(1, 6, "dueDate", "asc");
		List<Task> listTasks = page.getContent();
		model.addAttribute("listTasks", listTasks);
		Page<Item> itemPage = itemService.findPaginated(1, 6, "itemName", "asc");
		List<Item> listItems = itemPage.getContent();
		model.addAttribute("listItems", listItems);
		return "index";
	}
}