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
import mainPack.JDBCMySQLConnection;

public class AdminsDAO {

	private ResultSet resultSet;
	private Connection connection;
	private Statement statement;
	private List<Admins> admins;
	private Admins admin;
	

	public AdminsDAO() {
		resultSet = null;
		connection = null;
		statement = null;
	}
	
	public Admins getAdmin(int adminId) {
		admin = null;
		String query = "SELECT * FROM admins WHERE admin_id=" + adminId;

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			if (resultSet.next()) {

				admin = new Admins();
				admin.setId(resultSet.getInt("admin_id"));
				admin.setUsername(resultSet.getString("admin_username"));
				admin.setPassword(resultSet.getString("admin_password"));
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
		return admin;
	}

	public int getExistentAdmin(String username, String password) {
		int id=-1;
		String query = "SELECT admin_id FROM admins WHERE admin_username=" + "'"+username+"'"+"AND admin_password="+"'"+password+"'";

		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			if(resultSet.next()){
			
					id=resultSet.getInt("admin_id");
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
	
	public List<Admins> getAllAdmin() {
		admins = new ArrayList<Admins>();
		String query = "SELECT * FROM admins";
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {

				admin = new Admins();
				admin.setId(resultSet.getInt("admin_id"));
				admin.setUsername(resultSet.getString("admin_username"));
				admin.setPassword(resultSet.getString("admin_password"));
				admins.add(admin);
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
		return admins;
	}

	
	public ResultSet getAllAdminResultSet() {
		admins=new ArrayList<Admins>();
		String query = "SELECT * FROM admins";
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

	public void insertAdmin(int adminId, String username, String password) {

		String query = "INSERT into admins (admin_id, admin_username, admin_password) VALUES " + "(" + adminId + ",'"
				+ username + "','" + password + "'" + ")";

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

	public void deleteAdmin(int adminId) {
		admin = null;
		String query = "DELETE FROM admins WHERE admin_id=" + adminId;

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

	public void updateAdmin(int adminId, String username, String password) {

		String query = "UPDATE admins SET admin_username=" + "'" + username + "'," + "admin_password=" + "'" + password
				+ "'" + " WHERE admin_id=" + adminId;

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

	
	
	
	
	
}
