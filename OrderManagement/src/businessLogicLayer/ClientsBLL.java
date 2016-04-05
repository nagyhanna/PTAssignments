package businessLogicLayer;

import java.sql.ResultSet;
import java.util.List;

import dataAccessLayer.ClientsDAO;
import entities.Clients;

public class ClientsBLL {
	
	private ClientsDAO clientDAO;
	//private static int clientID=0;
	
	
	public ClientsBLL(){
		clientDAO= new ClientsDAO();
		//clientID++;
	}
	
	
	public void insertNewClient(String username, String password, String email, String country){
		clientDAO.insertClient(username, password, email, country);
	}
	
	public void deleteClient(int clientId){
		clientDAO.deleteClient(clientId);
	}
	
	public void updateClient(int clientId, String username, String password){
		clientDAO.updateClient(clientId, username, password);
	}
	
	public List<Clients> getAllClients(){
		return clientDAO.getAllClients();
	}
	
	public int getExistentClient(String username, String password){
		System.out.println(clientDAO.getExistentClient(username, password));
		return clientDAO.getExistentClient(username, password);
	}
	
	public ResultSet getResultSet(){
		return this.clientDAO.getAllClientResultSet();
	}

}
