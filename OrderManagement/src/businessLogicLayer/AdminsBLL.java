package businessLogicLayer;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import dataAccessLayer.AdminsDAO;
import entities.Admins;

public class AdminsBLL {


	AdminsDAO adminDAO;
	
	public AdminsBLL(){
		adminDAO=new AdminsDAO();
	}
	
	public Admins getAdmin(int adminID) {
		return adminDAO.getAdmin(adminID);
	}
	public int getExistentAdmin(String username, String password){
		System.out.println(adminDAO.getExistentAdmin(username, password));
		return adminDAO.getExistentAdmin(username, password);
	}
	
	public String getPassword(int adminID) {
		return adminDAO.getAdmin(adminID).getPassword();
	}
	
	public void setPassword(int adminId, String username,String password) {
		adminDAO.updateAdmin(adminId, username, password);
	}
	public List<Admins> getAllAdmins(){
		return adminDAO.getAllAdmin();
	}
	public ResultSet getResultSet(){
		return this.adminDAO.getAllAdminResultSet();
	}
	
	
	
	
	
	
}
