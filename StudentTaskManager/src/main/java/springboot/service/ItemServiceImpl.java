package springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import springboot.model.Item;
import springboot.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List < Item > getAllItems() {
        return itemRepository.findAll();
        
    }

	@Override
	public void saveItem(Item item) {
		this.itemRepository.save(item );
	}
	
	@Override
	public Item getItemById(long item_id) {
	    Optional < Item > optional = itemRepository.findById(item_id);
	    Item item = null;
	    if (optional.isPresent()) {
	        item = optional.get();
	    } else {
	        throw new RuntimeException(" Item not found for id :: " + item_id);
	    }
	    return item;
	}
	@Override
	public void deleteItemById(long item_id) {
	   this.itemRepository.deleteById(item_id);
	}
	@Override
	public Page<Item> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
	 Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
	  Sort.by(sortField).descending();
	 
	 Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
	 return this.itemRepository.findAll(pageable);
	}

	
}