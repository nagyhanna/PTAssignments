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
import entities.Clients;
import mainPack.JDBCMySQLConnection;

public class ClientsDAO {

	private ResultSet resultSet;
	private Connection connection;
	private Statement statement;
	private List<Clients> clients;
	private Clients client;
	//private List<Integer> current_clientIds;

	public ClientsDAO() {
		resultSet = null;
		connection = null;
		statement = null;
	}

	public Clients getClient(int clientId) {
		client = null;
		String query = "SELECT * FROM clients WHERE client_id=" + clientId;

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			if (resultSet.next()) {

				client = new Clients();
				client.setId(resultSet.getInt("client_id"));
				client.setUsername(resultSet.getString("client_username"));
				client.setPassword(resultSet.getString("client_password"));
				client.setEmail(resultSet.getString("client_email"));
				client.setCountry(resultSet.getString("client_country"));
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
		return client;
	}

	public List<Clients> getAllClients() {
		clients = new ArrayList<Clients>();
		String query = "SELECT * FROM clients";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {

				client = new Clients();
				client.setId(resultSet.getInt("client_id"));
				client.setUsername(resultSet.getString("client_username"));
				client.setPassword(resultSet.getString("client_password"));
				client.setEmail(resultSet.getString("client_email"));
				client.setCountry(resultSet.getString("client_country"));
				clients.add(client);
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
		return clients;
	}

	public void insertClient(String username, String password, String email, String country) {

		String query = "INSERT into clients (client_username, client_password, client_email, client_country) VALUES "
				+ "("  + "'" + username + "','" + password + "','" + email + "','" + country + "')";

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			JOptionPane.showConfirmDialog(null, "Welcome dear new client! \n You can log in!");
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

	public void deleteClient(int clientId) {
		client = null;
		String query = "DELETE FROM clients WHERE client_id=" + clientId;

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
	
	
	
	
	
	
	
	public void updateClient(int clientId, String username, String password) {

		String query = "UPDATE clients SET client_username=" + "'" + username + "'," + "client_password=" + "'" + password
				+"'" +" WHERE client_id=" + clientId;

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			JOptionPane.showConfirmDialog(null, "Profile Successfully updated");

		} catch (MySQLIntegrityConstraintViolationException e) {
			JOptionPane.showConfirmDialog(null, "ERROR DUBLICATE KEY or existent Username");

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
	
	
	
	public int getExistentClient(String username, String password) {
		int id=-1;
		String query = "SELECT client_id FROM clients WHERE client_username=" + "'"+username+"'"+" AND client_password="+"'"+password+"'";

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			if(resultSet.next()){
			
					id=resultSet.getInt("client_id");
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
		return id;
	}
	
	
	
public ResultSet getAllClientResultSet() {
		
		String query = "SELECT * FROM clients";
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
	
	
	
	
	
	

}
