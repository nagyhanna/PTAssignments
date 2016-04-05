package dataAccessLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import entities.Products;
import mainPack.JDBCMySQLConnection;

public class OrdersDAO {

	private Connection connection;
	private Statement statement;
	private double totalPrice;
	private ResultSet resultSet;

	public OrdersDAO() {
		totalPrice = 0;
	}

	public void insertAnOrder(int clientId, int productId, int quantity) {

		String query = "INSERT into orders (order_client_id, order_quantity, order_product_id) VALUES " + "('"
				+ clientId + "','" + quantity + "','" + productId + "'" + ")";

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

	public double calculateTotalPrice(int clientId) {

		ProductsDAO productsDAO = new ProductsDAO();
		String query = "SELECT order_quantity, order_product_id FROM orders WHERE order_client_id=" + clientId;

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				double pricePerProduct = productsDAO.getProduct(resultSet.getInt("order_product_id")).getPrice();
				totalPrice += resultSet.getInt("order_quantity") * pricePerProduct;
			}

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
		return totalPrice;

	}

	public ResultSet getAllOrderedProduct(int clientId) {

		String query = "SELECT a.product_id, a.product_name, a.product_price, b.order_quantity "
				+ "FROM products a, orders b WHERE " + "a.product_id=b.order_product_id AND b.order_client_id="
				+ clientId;
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(query);
			return resultSet;

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return null;
	}

	public void deleteAnOrder(int clientId, int productId) {

		String query = "DELETE FROM orders WHERE order_product_id= " + productId + " AND order_client_id= " + clientId;

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

	public void deleteAllOrder(int clientId) {

		String query = "DELETE FROM orders WHERE order_client_id= " + clientId;

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

}
