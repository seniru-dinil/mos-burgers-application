package edu.icet.mos.repository;

import edu.icet.mos.entity.ItemEntity;
import edu.icet.mos.util.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.event.ItemEvent;
import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity,Integer> {
    List<ItemEntity> findByCategory(Category category);
    List<ItemEntity> findByNameContaining(String keyword);
}
