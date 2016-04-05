package entities;

public class Clients {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String country;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	@Override
    public String toString() {
        return "Client [clientid=" + id + ", username=" + username + ", password="
                +password+" email: " + email +" country: " + country+"]" ; 
    }     
	
	

}