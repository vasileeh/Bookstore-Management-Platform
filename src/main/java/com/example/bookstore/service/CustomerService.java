package com.example.bookstore.service;

import com.example.bookstore.repository.CustomerRepository;
import com.example.bookstore.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
       return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("The customer was not found"));
    }

    public Customer getCustomerByEmail(String email){
        return customerRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("There is no customer using this email"));
    }

    public Customer editCustomer(Long id, Customer updateCustomer){
        Customer existing=getCustomerById(id);
        existing.setName(updateCustomer.getName());
        existing.setEmail(updateCustomer.getEmail());
        existing.setAddress(updateCustomer.getAddress());
        existing.setOrders(updateCustomer.getOrders());

        return customerRepository.save(existing);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }
}
