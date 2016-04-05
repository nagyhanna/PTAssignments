package dataAccessLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import entities.Admins;
import entities.Products;
import mainPack.JDBCMySQLConnection;

public class ProductsDAO {
	
	private ResultSet resultSet;
	private Connection connection;
	private Statement statement;
	private List<Products> products;
	private Products product;
	
	public Products getProduct(int productId) {
		product = null;
		String query = "SELECT * FROM products WHERE product_id=" + productId;

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			if (resultSet.next()) {

				product = new Products();
				product.setId(resultSet.getInt("product_id"));
				product.setName(resultSet.getString("product_name"));
				product.setBrand(resultSet.getString("product_brand"));
				product.setColour(resultSet.getString("product_colour"));
				product.setSize(resultSet.getString("product_Size"));
				product.setRam(resultSet.getString("product_RAM"));
				product.setPrice(resultSet.getDouble("product_price"));
				product.setQuantity(resultSet.getInt("product_quantity"));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return product;
	}
	

	
	public List<Products> getAllProducts() {
		products = new ArrayList<Products>();
		String query = "SELECT * FROM products";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {

				product = new Products();
				product.setId(resultSet.getInt("product_id"));
				product.setName(resultSet.getString("product_name"));
				product.setBrand(resultSet.getString("product_brand"));
				product.setColour(resultSet.getString("product_colour"));
				product.setSize(resultSet.getString("product_Size"));
				product.setRam(resultSet.getString("product_RAM"));
				product.setPrice(resultSet.getDouble("product_price"));
				
				product.setQuantity(resultSet.getInt("product_quantity"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return products;
	}

	
	
	
	
	public void insertProduct(int productId, String name,String brand, String colour, String size, String ram, double price, int quantity) {

		String query = "INSERT into products (product_id, product_name,product_brand, product_colour, "
				+ "product_size, product_ram, product_price, product_quantity) VALUES " + "(" + productId + ",'"
				+name +"','" + brand + "','" + colour + "','" + size+ "','" + ram+"','" + price+"','" + quantity+"')";

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (MySQLIntegrityConstraintViolationException e) {
			JOptionPane.showConfirmDialog(null, "ERROR dublicate id OR existent userName");

		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERROR SQL ERROR");
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	public void deleteProduct(int productId) {
		product = null;
		String query = "DELETE FROM products WHERE product_id=" + productId;

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	
	
	
	
	public void updateProduct(int productId, String name,String brand, String colour, String size, String ram, double price, int quantity) {

		String query = "UPDATE products SET product_name=" + "'" + name + "'," + "product_brand=" + "'" + brand
				+"',"+"product_colour="+ "'" + colour + "'," +"product_size="+ "'"+size + "'," + "product_ram="+"'" 
				+ ram + "',"+"product_price="+"'" + price + "',product_quantity=" +quantity + " WHERE product_id=" + productId;

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (MySQLIntegrityConstraintViolationException e) {
			JOptionPane.showConfirmDialog(null, "ERROR DUBLICATE KEY or exitent Username");

		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERROR SQL EXCEPTION");
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	public void updateProductQuantity(int productId, int quantity) {

		String query = "UPDATE products SET product_quantity=" + quantity + " WHERE product_id=" + productId;
		
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (MySQLIntegrityConstraintViolationException e) {
			JOptionPane.showConfirmDialog(null, "ERROR DUBLICATE KEY or exitent Username");

		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERROR SQL EXCEPTION");
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	
	
	public ResultSet getAllProductResultSet() {
		
		String query = "SELECT * FROM products";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
		               ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(query);
			return resultSet;
			
		} catch (SQLException e) {
			e.printStackTrace();
		 
		}
		return null;
	}
	
	public ResultSet getAllProductFilteredByBrandResultSet(String brand) {
		
		String query = "SELECT * FROM products WHERE product_brand="+ "'"+brand+"'";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
		               ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(query);
			return resultSet;
			
		} catch (SQLException e) {
			e.printStackTrace();
		 
		}
		return null;
	}

	public int getQuantity(int productId){
		int quantity=0;
		String query = "SELECT product_quantity FROM products WHERE product_id=" + productId;

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			if (resultSet.next()){
			quantity= resultSet.getInt("product_quantity");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return quantity;
	}
	
	

}
