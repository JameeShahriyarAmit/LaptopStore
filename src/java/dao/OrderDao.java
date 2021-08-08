package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;

/**
 *
 * @author shahr
 */
public class OrderDao {
    	private String jdbcURL = "jdbc:mysql://localhost:3306/laptopshop";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_ORDER_SQL = "INSERT INTO cart" + "  (name,price) VALUES "
			+ " (?, ?);";
        private static final String SELECT_ALL_ORDER = "select * from cart";

	public OrderDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
        
        	public List<Order> selectAllOrder() {
                 
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Order> orderList = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDER);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				orderList.add(new Order(name, price));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return orderList;
	}

	public void insertOrder(Order order) throws SQLException {
		System.out.println(INSERT_ORDER_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_SQL)) {
                        preparedStatement.setString(1, order.getName());
			preparedStatement.setDouble(2, order.getPrice());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}


	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}