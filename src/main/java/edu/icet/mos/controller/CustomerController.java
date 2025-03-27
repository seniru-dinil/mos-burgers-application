package edu.icet.mos.controller;


import edu.icet.mos.dto.Customer;
import edu.icet.mos.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        Customer save = service.save(customer);
        return save!=null?ResponseEntity.ok(save):ResponseEntity.internalServerError().body("something went wrong!");
    }


    @PutMapping
    public ResponseEntity<?> update(@RequestBody Customer customer) {
        Customer update = service.update(customer);
        return update!=null?ResponseEntity.ok(update):ResponseEntity.internalServerError().body("something went wrong!");
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam(value = "id") Integer id) {
        try{
            service.delete(id);
            return ResponseEntity.ok("customer deleted successfully!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam(value = "id") Integer id) {
        return ResponseEntity.ok(service.getById(id).orElseThrow(()->new RuntimeException("ivalid customer id")));
    }


    @GetMapping("/list")
    public List<Customer> getAll() {
        return service.getAll();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByUserName(@RequestParam(value = "name") String firstName){
        return ResponseEntity.ok(service.findByUserName(firstName));
    }

}
