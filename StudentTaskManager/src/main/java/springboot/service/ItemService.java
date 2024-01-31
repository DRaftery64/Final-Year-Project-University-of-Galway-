package springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import springboot.model.Item;

public interface ItemService {
 List<Item> getAllItems();
 void saveItem(Item item);
 Item getItemById(long item_id);
 void deleteItemById(long item_id);
 Page<Item> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}