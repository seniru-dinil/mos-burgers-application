package edu.icet.mos.controller;


import edu.icet.mos.dto.Item;
import edu.icet.mos.service.ItemService;
import edu.icet.mos.util.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService service;

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam(value = "id") Integer id) {
        return ResponseEntity.ok(service.getById(id).orElseThrow(()->new RuntimeException("invalid item id")));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam(value = "id") Integer id) {
        try{
            if (service.delete(id)){
                return ResponseEntity.ok("item deleted");
            }else{
                return ResponseEntity.badRequest().body("invalid item id");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Item item) {
        return ResponseEntity.ok(service.save(item));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Item item) {
        return ResponseEntity.ok(service.update(item));
    }


    @GetMapping("/list")
    public List<Item> getAll() {
        return service.getAll();
    }

    @GetMapping("/category")
    public List<Item> getAll(@RequestParam(value = "category")Category category){
        return service.getAll(category);
    }


    @GetMapping("/search")
    public List<Item> findByName(@RequestParam(value = "name") String name){
        return service.findByName(name);
    }


}
