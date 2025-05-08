package com.example.bookstore.service;

import com.example.bookstore.service.CustomerService;
import com.example.bookstore.model.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.util.Assert;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        customer=new Customer();
        customer.setId(1L);
        customer.setName("Test Customer");
        customer.setAddress("Test Address");
        customer.setEmail("email@test");
        customer.setOrders(new ArrayList<>());
    }

    @Test
    void testCreateCustomer(){
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer saved=customerService.createCustomer(customer);

        assertEquals("Test Customer",saved.getName());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testGetAllCustomers(){
        List<Customer> customers=List.of(customer);
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result=customerService.getAllCustomers();

        assertEquals(1,result.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testGetCustomerById_Found(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result=customerService.getCustomerById(1L);
        assertEquals("Test Customer", result.getName());
    }

    @Test
    void testGetCustomerById_NotFound(){
        when(customerRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception=assertThrows(RuntimeException.class,
                ()->customerService.getCustomerById(2l));

        assertEquals("The customer was not found", exception.getMessage());
    }

    @Test
    void testGetCustomerByEmail_Found(){
        when(customerRepository.findByEmail("email@test")).thenReturn(Optional.of(customer));

        Customer result=customerService.getCustomerByEmail("email@test");

        assertEquals("email@test", result.getEmail());
    }

    @Test
    void testEditCustomer() {
        Customer editedCustomer = new Customer();
        editedCustomer.setName("Test Customer2");
        editedCustomer.setEmail("email@test2");
        editedCustomer.setAddress("Test Address2");
        editedCustomer.setOrders(new ArrayList<>());

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenAnswer(i -> i.getArgument(0));

        Customer result = customerService.editCustomer(1L, editedCustomer);

        assertEquals("Test Customer2", result.getName());
        assertEquals("Test Address2", result.getAddress());
        assertEquals("email@test2", result.getEmail());
    }

    @Test
    void testDeleteCustomer(){
        doNothing().when(customerRepository).deleteById(1L);

        customerService.deleteCustomer(1L);

        verify(customerRepository, times(1)).deleteById(1L);
    }

}
