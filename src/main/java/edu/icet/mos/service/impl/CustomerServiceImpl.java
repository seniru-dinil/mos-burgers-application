package edu.icet.mos.service.impl;


import edu.icet.mos.dto.Customer;
import edu.icet.mos.entity.CustomerEntity;
import edu.icet.mos.repository.CustomerRepository;
import edu.icet.mos.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final ModelMapper mapper;


    @Override
    public Customer save(Customer customer){
        CustomerEntity save = repository.save(mapper.map(customer, CustomerEntity.class));
        return mapper.map(save,Customer.class);
    }


    @Override
    public void delete(Integer id) {
         if(repository.existsById(id)){
             try{
                 repository.deleteById(id);
                 return;
             }catch (Exception e){
                 throw  new RuntimeException(e.getMessage());
             }
         }
         throw new RuntimeException("invalid customer id");
    }

    @Override
    public List<Customer> getAll() {
       return repository.findAll().stream().map(c->mapper.map(c,Customer.class)).collect(Collectors.toList());
    }

    @Override
    public List<Customer> findByUserName(String firstName) {
        return repository.findByFirstNameContaining(firstName).stream().map(customer->mapper.map(customer,Customer.class)).toList();
    }


    @Override
    public Optional<Customer> getById(Integer id){
        return repository.findById(id).map(c->mapper.map(c,Customer.class));
    }


    @Override
    public Customer update(Customer customer){
        CustomerEntity save = repository.save(mapper.map(customer, CustomerEntity.class));
        return mapper.map(save,Customer.class);
    }

    @Override
    public Integer cal(int a,int b){
        return a+b;
    }
}
