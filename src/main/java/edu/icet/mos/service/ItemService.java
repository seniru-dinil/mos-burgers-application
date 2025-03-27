package edu.icet.mos.service;

import edu.icet.mos.dto.Item;
import edu.icet.mos.util.Category;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item save(Item item);
    Item update(Item item);
    Boolean delete(Integer id);
    Optional<Item> getById(Integer id);
    List<Item> getAll();
    List<Item> getAll(Category category);

    List<Item> findByName(String name);
}
