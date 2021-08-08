
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

/**
 *
 * @author shahr
 */
public class ProductDAO {
    	private String jdbcURL = "jdbc:mysql://localhost:3306/laptopshop";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_PROD_SQL = "INSERT INTO product" + "  (id, name, description, price, image) VALUES "
			+ " (?, ?, ?, ?, ?);";

	private static final String SELECT_PROD_BY_ID = "select name,description,price,image from product where id =?";
	private static final String SELECT_ALL_PROD = "select * from product";
	private static final String DELETE_PROD_SQL = "delete from product where id = ?;";
	private static final String UPDATE_PROD_SQL = "update product set name = ?,description= ?, price= ?, image= ? where id = ?;";

	public ProductDAO() {
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

	public void insertProduct(Product product) throws SQLException {
		System.out.println(INSERT_PROD_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROD_SQL)) {
			preparedStatement.setInt(1, product.getId());
                        preparedStatement.setString(2, product.getName());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setDouble(4, product.getPrice());
                        preparedStatement.setString(5, product.getImage());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Product selectProduct(int id) {
		Product product = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROD_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
                                String image = rs.getString("image");
				product = new Product(id, name, description, price, image);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return product;
	}

	public List<Product> selectAllProduct() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Product> product = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROD);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
                                int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
                                String image = rs.getString("image");
				product.add(new Product(id, name, description, price, image));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return product;
	}

	public boolean deleteProduct(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PROD_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateProduct(Product product) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PROD_SQL);) {
			
                        statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
                        statement.setString(4, product.getImage());
                        statement.setInt(5, product.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
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

