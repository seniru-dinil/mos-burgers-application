package edu.icet.mos.controller;


import edu.icet.mos.dto.Admin;
import edu.icet.mos.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService service;


    @PostMapping
    public void save(@RequestBody Admin admin){
        Admin obj = service.save(admin);
    }


    @PutMapping
    public void update(@RequestBody Admin admin){
        service.update(admin);
    }

    @DeleteMapping
    public void delete(@RequestParam(value ="id") Integer id){
        service.delete(id);
    }

    @GetMapping
    public Admin getById(@RequestParam(value = "id") Integer id){
        return service.getById(id);
    }


    @GetMapping("/list")
    public List<Admin> getAll(){
        return service.getAll();
    }
}
