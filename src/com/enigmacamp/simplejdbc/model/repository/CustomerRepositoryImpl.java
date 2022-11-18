package com.enigmacamp.simplejdbc.model.repository;

import com.enigmacamp.simplejdbc.model.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private Connection conn;

    public CustomerRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    public void insertCustomerStatement(Customer customer) {
        PreparedStatement statement = null;
        String sql = "INSERT INTO mst_customer(name, address, birth_date, status, phone) VALUES(?, ?, ?, ?, ?)";

        try {
            statement = conn.prepareStatement(sql);

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
//        statement.setDate(3, Date.valueOf(customer.getBirthDate().toString()));
            statement.setDate(3, new java.sql.Date(customer.getBirthDate().getTime()));
            statement.setString(4, customer.getStatus());
            statement.setString(5, customer.getPhone());

            statement.executeUpdate();

            System.out.println("Data " + customer.getName() + " masuk");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void insertCustomer(Customer customer) {
        try {
            insertCustomerStatement(customer);
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Customer getById(Integer id) {
        PreparedStatement statement = null;
        String sql = "SELECT * FROM mst_customer WHERE id = ?";

        try {
            statement = conn.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            Customer customer = null;

            Integer customerId = resultSet.getInt("id");
            String customerName = resultSet.getString("name");
            String customerAddress = resultSet.getString("address");
            String customerBirthDate = resultSet.getString("birth_date");
            String customerStatus = resultSet.getString("status");
            String customerPhone = resultSet.getString("phone");

            customer = new Customer(customerId, customerName, customerAddress, Date.valueOf(customerBirthDate), customerStatus, customerPhone);

            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new  RuntimeException(e);
            }
        }
    }

    @Override
    public List<Customer> getAllData(Integer page, Integer pageSize) {
        PreparedStatement statement = null;
        Integer offset = page > 0 ? (page - 1) * pageSize : 0;
        String sql = "SELECT * FROM mst_customer ORDER BY id LIMIT ? OFFSET ?";

        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();

            List<Customer> customerList = new ArrayList<>();

            while (resultSet.next()) {
                Integer customerId = resultSet.getInt("id");
                String customerName = resultSet.getString("name");
                String customerAddress = resultSet.getString("address");
                String customerBirthDate = resultSet.getString("birth_date");
                String customerStatus = resultSet.getString("status");
                String customerPhone = resultSet.getString("phone");

                Customer customer = new Customer(customerId, customerName, customerAddress, Date.valueOf(customerBirthDate), customerStatus, customerPhone);
                customerList.add(customer);
            }

            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new  RuntimeException(e);
            }
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement statement = null;
        String sql = "DELETE FROM mst_customer WHERE id = ?";

        try {
            statement = conn.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new  RuntimeException(e);
            }
        }
    }

    @Override
    public void updateById(Integer id, String customerName, String customerAddress, String birthDate, String status, String customerPhone) {
        PreparedStatement statement = null;
        String sql = "UPDATE mst_customer SET name = ?, address = ?, birth_date = ?, status = ?, phone = ? WHERE id = ?";

        try {
            statement = conn.prepareStatement(sql);

            statement.setString(1, customerName);
            statement.setString(2, customerAddress);
            statement.setDate(3, Date.valueOf(birthDate));
            statement.setString(4, status);
            statement.setString(5, customerPhone);
            statement.setInt(6, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new  RuntimeException(e);
            }
        }
    }

    @Override
    public void bulkInsertCustomer(List<Customer> customers) {
        try {
            for (Customer c:customers) {
                insertCustomerStatement(c);
            }
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Integer getTotalCustomer() {
        Statement statement = null;
        String countCustomerSql = "SELECT COUNT(id) FROM mst)customer";
        Integer result = 0;

        try {
            statement = conn.createStatement();
            statement.executeQuery(countCustomerSql);
            ResultSet resultSet = statement.executeQuery(countCustomerSql);
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new  RuntimeException(e);
            }
        }
        return result;
    }
}
