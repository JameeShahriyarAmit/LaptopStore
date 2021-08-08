package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Staff;



public class StaffDao {
   
    // data needed to make the connection
    
        private final String jdbcURL = "jdbc:mysql://localhost:3306/laptopshop";
	private final String jdbcUsername = "root";
	private final String jdbcPassword = ""; 
        private final String jdbcDriver = "com.mysql.jdbc.Driver";
        
   /// all the sql statements  
         private static final String SELECT_USERS_LOGIN = "SELECT * FROM staff WHERE username = ? and password = ?";
         
    // function to create a connection 
    // returns a Connection object    
       
       protected Connection getConnection() throws ClassNotFoundException, SQLException{
           Connection con = null;           
           
                Class.forName(jdbcDriver);         
                con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                       
           return con;
       }
       
    // function to validate staff   
       
    public Staff checkLogin(String uname,String pass) 
    {
        Staff staff = new Staff();
        
        try{
            
            Connection con = getConnection();
            
            // STEP 2: create a statement using connection object
                PreparedStatement pst = con.prepareStatement(SELECT_USERS_LOGIN);
                pst.setString(1, uname);
                pst.setString(2, pass);
                
                // STEP 3: execute querry
                
                ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                    int tempID = rs.getInt("id");
                    String Name = rs.getString("staffname");                    
                    String uName = rs.getString("username");
                    String temppass = rs.getString("password");
                   
                    staff.setStaffID(tempID);
                    staff.setStaffName(uName);
                    staff.setUsername(uName);
                    staff.setPassword(temppass);
                    
                
                return staff;
                
            }
        
        } catch (ClassNotFoundException | SQLException e) {return staff;}
        
        return staff;
    }
       
       
    
}
