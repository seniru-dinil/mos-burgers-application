package edu.icet.mos.service;

import edu.icet.mos.dto.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer save(Customer customer);
    void delete(Integer id);
    Optional<Customer> getById(Integer id);
    Customer update(Customer customer);
    List<Customer> getAll();
     Integer cal(int a,int b);
    List<Customer> findByUserName(String firstName);
}
