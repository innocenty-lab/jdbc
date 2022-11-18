package com.enigmacamp.simplejdbc.model;

import com.enigmacamp.simplejdbc.model.model.Customer;
import com.enigmacamp.simplejdbc.model.repository.CustomerRepository;
import com.enigmacamp.simplejdbc.model.repository.CustomerRepositoryImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static Connection conn;
//    public static void main(String[] args) throws SQLException {
//        String dbHost = "localhost";
//        String dbPort = "5432";
//        String dbName = "enigma";
//        String dbUser = "postgres";
//        String dbPassword = "12345";
//
//        String dbURL = String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName);
//
//        try {
////            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/enigma", "postgres", "12345");
//            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
//            System.out.println("connect");
//
////            PreparedStatement statement = conn.prepareStatement(
////                    "INSERT INTO mst_customer(name, address, birth_date, status, phone) VALUES('Putri', 'Bekasi', '2000-11-30', 'trainee', '0895335577902')"
////            );
//
////            insertCustomer("Ciyo", "Bekasi", "2022-04-30", "Trainee", "0895127391");
//
////            getById(3);
////            getAllData(1, 2);
////            deleteById(5);
////            updateById(2, "Uji", "Semarang", "2001-02-23", "Trainee", "0895124233");
//
//            Customer customer = getById(3);
//            System.out.println(customer);
//
//            List<Customer> customerList = getAllData(1, 2);
//            for (Customer c:customerList) {
//                System.out.println(c);
//            }
//
//
//        } catch (SQLException e) {
//            System.out.println("msg: " + e.getMessage());
//        } finally {
//            conn.close();
//        }
//    }

//    public static void main(String[] args) {
//        try {
//            conn = DataSourceFactory.getConn();
//
//            Customer customer = getById(3);
//            System.out.println(customer);
//
//            List<Customer> customerList = getAllData(1, 2);
//            for (Customer c:customerList) {
//                System.out.println(c);
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            DataSourceFactory.connClose();
//        }
//    }

    public static void main(String[] args) {
        try {
            conn = DataSourceFactory.getConn();
            CustomerRepository customerRepository = new CustomerRepositoryImpl(conn);

            Customer customer = customerRepository.getById(3);
            System.out.println(customer);

            List<Customer> customerList = customerRepository.getAllData(1, 2);
            for (Customer c:customerList) {
                System.out.println(c);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DataSourceFactory.connClose();
        }
    }

//    static void insertCustomer(Customer customer) throws SQLException {
//        String sql = "INSERT INTO mst_customer(name, address, birth_date, status, phone) VALUES(?, ?, ?, ?, ?)";
//
//        PreparedStatement statement = conn.prepareStatement(sql);
//
//        statement.setString(1, customer.getName());
//        statement.setString(2, customer.getAddress());
////        statement.setDate(3, Date.valueOf(customer.getBirthDate().toString()));
//        statement.setDate(3, new java.sql.Date(customer.getBirthDate().getTime()));
//        statement.setString(4, customer.getStatus());
//        statement.setString(5, customer.getPhone());
//
//        statement.executeUpdate();
//
//        statement.close();
//
//        System.out.println("Data " + customer.getName() + " masuk");
//    }

//    static Customer getById(Integer id) throws SQLException {
//        String sql = "SELECT * FROM mst_customer WHERE id = ?";
//
//        PreparedStatement statement = conn.prepareStatement(sql);
//
//        statement.setInt(1, id);
//
//        ResultSet resultSet = statement.executeQuery();
//
//        resultSet.next();
//
//        Customer customer = null;
//
//        Integer customerId = resultSet.getInt("id");
//        String customerName = resultSet.getString("name");
//        String customerAddress = resultSet.getString("address");
//        String customerBirthDate = resultSet.getString("birth_date");
//        String customerStatus = resultSet.getString("status");
//        String customerPhone = resultSet.getString("phone");
//
//        customer = new Customer(customerId, customerName, customerAddress, Date.valueOf(customerBirthDate), customerStatus, customerPhone);
//
////        String data = String.format("%d, %s, %s, %s, %s, %s", customerId, customerName, customerAddress, customerBirthDate, customerStatus, customerPhone);
////        System.out.println(data);
//
//        statement.close();
//
//        return customer;
//    }

//    static List<Customer> getAllData(Integer page, Integer pageSize) throws SQLException {
//        Integer offset = page > 0 ? (page - 1) * pageSize : 0;
//
//        String sql = "SELECT * FROM mst_customer ORDER BY id LIMIT ? OFFSET ?";
//
//        PreparedStatement statement = conn.prepareStatement(sql);
//        statement.setInt(1, pageSize);
//        statement.setInt(2, offset);
//
//        ResultSet resultSet = statement.executeQuery();
//
//        List<Customer> customerList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            Integer customerId = resultSet.getInt("id");
//            String customerName = resultSet.getString("name");
//            String customerAddress = resultSet.getString("address");
//            String customerBirthDate = resultSet.getString("birth_date");
//            String customerStatus = resultSet.getString("status");
//            String customerPhone = resultSet.getString("phone");
//
////            String data = String.format("%d, %s, %s, %s, %s, %s", customerId, customerName, customerAddress, customerBirthDate, customerStatus, customerPhone);
////
////            System.out.println(data);
//
//            Customer customer = new Customer(customerId, customerName, customerAddress, Date.valueOf(customerBirthDate), customerStatus, customerPhone);
//            customerList.add(customer);
//        }
//
//        conn.close();
//
//        return customerList;
//    }

//    static void deleteById(Integer id) throws SQLException {
//        String sql = "DELETE FROM mst_customer WHERE id = ?";
//
//        PreparedStatement statement = conn.prepareStatement(sql);
//
//        statement.setInt(1, id);
//
//        statement.executeQuery();
//
//        statement.close();
//    }

//    static void updateById(Integer id, String customerName, String customerAddress, String birthDate, String status, String customerPhone) throws SQLException {
//        String sql = "UPDATE mst_customer SET name = ?, address = ?, birth_date = ?, status = ?, phone = ? WHERE id = ?";
//
//        PreparedStatement statement = conn.prepareStatement(sql);
//
//        statement.setString(1, customerName);
//        statement.setString(2, customerAddress);
//        statement.setDate(3, Date.valueOf(birthDate));
//        statement.setString(4, status);
//        statement.setString(5, customerPhone);
//        statement.setInt(6, id);
//
//        statement.executeUpdate();
//
//        statement.close();
//    }
}