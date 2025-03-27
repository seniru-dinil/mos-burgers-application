package edu.icet.mos.service.impl;

import edu.icet.mos.dto.Item;
import edu.icet.mos.entity.ItemEntity;
import edu.icet.mos.repository.ItemRepository;
import edu.icet.mos.service.ItemService;
import edu.icet.mos.util.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ModelMapper mapper;
    private final ItemRepository repository;

    @Override
    public Item save(Item item) {
        ItemEntity save = repository.save(mapper.map(item, ItemEntity.class));
        return mapper.map(save, Item.class);
    }

    @Override
    public Item update(Item item) {
        return save(item);
    }

    @Override
    public Boolean delete(Integer id) {
        if (repository.existsById(id)) {
            try {
                repository.deleteById(id);
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return false;
    }

    @Override
    public List<Item> getAll() {
        return repository.findAll().stream().map(item->mapper.map(item,Item.class)).collect(Collectors.toList());
    }

    @Override
    public List<Item> getAll(Category category) {
        return repository.findByCategory(category).stream().map(item->mapper.map(item,Item.class)).collect(Collectors.toList());
    }

    @Override
    public List<Item> findByName(String name) {
        return repository.findByNameContaining(name).stream().map(item->mapper.map(item,Item.class)).toList();
    }

    @Override
    public Optional<Item> getById(Integer id) {
        return repository.findById(id).map(item->mapper.map(item,Item.class));
    }
}
