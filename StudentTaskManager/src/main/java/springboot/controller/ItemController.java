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

import springboot.model.Item;
import springboot.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/items")
	public String viewHomePage(Model model) {
		return findPaginated(1, "itemName", "asc", model);
	}

	@GetMapping("/showNewItemForm")
	public String showAddItemForm(Model model) {
		Item item = new Item();
		model.addAttribute("item", item);
		return "new_item";
	}

	@PostMapping("/saveItem")
	public String saveItem(@ModelAttribute("item") Item item) {
		itemService.saveItem(item);
		return "redirect:/items";
	}

	@GetMapping("/showItemFormForUpdate/{item_id}")
	public String showItemFormForUpdate(@PathVariable(value = "item_id") long item_id, Model model) {

		Item item = itemService.getItemById(item_id);

		model.addAttribute("item", item);
		return "update_item";
	}

	@GetMapping("/deleteItem/{item_id}")
	public String deleteItem(@PathVariable(value = "item_id") long item_id) {

		this.itemService.deleteItemById(item_id);
		return "redirect:/items";
	}

	@GetMapping("/itemsPage/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 10;

		Page<Item> page = itemService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Item> listItems = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listItems", listItems);
		return "list_items";
	}
}