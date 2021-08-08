/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Customer;

public class CustomerDao {
    
    // data needed to make the connection
    
        private final String jdbcURL = "jdbc:mysql://localhost:3306/laptopshop";
	private final String jdbcUsername = "root";
	private final String jdbcPassword = ""; 
        private final String jdbcDriver = "com.mysql.jdbc.Driver";
        
    // all the sql statements    
        
       private static final String  INSERT_USERS_SQL =  "INSERT INTO customer" +
                " (first_name, last_name, username, password, address, contact) VALUES " +
                " (?,?,?,?,?,?);" ;
       
       
       private static final String SELECT_USERS_LOGIN = "SELECT * FROM customer WHERE username = ? AND password = ?";
       
       
      
    // function to create a connection 
    // returns a Connection object    
       
       protected Connection getConnection() throws ClassNotFoundException, SQLException{
           Connection con = null;           
           
                Class.forName(jdbcDriver);  
                
                con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                       
           return con;
       }  
       
        
    // function to register customer
            
    public void registerCustomer (Customer customer) throws ClassNotFoundException, SQLException{
        
                
        Connection con = getConnection();
        
        //STEP: 2 create a prepared statement 
        
        PreparedStatement pst = con.prepareStatement(INSERT_USERS_SQL);
       
        pst.setString(1, customer.getFirstName());
        pst.setString(2, customer.getLastName());
        pst.setString(3, customer.getUsername());
        pst.setString(4, customer.getPassword());
        pst.setString(5, customer.getAddress());
        pst.setString(6, customer.getContact());
        
        // step 3 - execute querry        
        pst.executeUpdate();        
        
    }
    
    
    // validate login
    
    public Customer checkLogin(String uname,String pass) 
    {
        Customer customer = new Customer();
        
        try{
            
            Connection con = getConnection();
            
            // STEP 2: create a statement using connection object
                PreparedStatement pst = con.prepareStatement(SELECT_USERS_LOGIN);
                pst.setString(1, uname);
                pst.setString(2, pass);
                
                // STEP 3: execute querry
                
                ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                
                 String fName = rs.getString("first_name");
                    String lName = rs.getString("last_name");
                    String uName = rs.getString("username");
                    String temppass = rs.getString("password");
                    String add = rs.getString("address");
                    String contact = rs.getString("contact");
                    
                    
                    customer.setFirstName(fName);
                    customer.setLastName(lName);
                    customer.setUsername(uName);
                    customer.setPassword(temppass);
                    customer.setAddress(add);
                    customer.setContact(contact);
                    
                
                return customer;
                
            }
        
        } catch (ClassNotFoundException | SQLException e) {return customer;}
        
        return customer;
    }
        
    
    
    
}
