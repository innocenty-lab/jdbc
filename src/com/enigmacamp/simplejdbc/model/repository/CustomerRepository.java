package com.enigmacamp.simplejdbc.model.repository;

import com.enigmacamp.simplejdbc.model.model.Customer;

import java.util.List;

public interface CustomerRepository {
    void insertCustomer(Customer customer);
    Customer getById(Integer id);
    List<Customer> getAllData(Integer page, Integer pageSize);
    void deleteById(Integer id);
    void updateById(Integer id, String customerName, String customerAddress, String birthDate, String status, String customerPhone);

    void bulkInsertCustomer(List<Customer> customers);

    Integer getTotalCustomer();
}
