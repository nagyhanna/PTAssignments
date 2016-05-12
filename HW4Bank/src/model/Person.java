package model;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;


public class Person implements Observer, Serializable{
	
	private static int id1=100;
	private final int id;
	
	private String name;
	
	public Person(int id, String name) {
		super();
		this.id=id;
		this.name = name;
	}

	public Person(String name) {
		super();
		this.id=id1++;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
   public String getName() {
		return name;
	}
   public void setName(String name) {
		this.name = name;
	}
   @Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Account account= (Account) arg0;
		Double sum =(Double) arg1;
		System.out.println("Owner:"+this.getName() +"'s account with ID: "+account.getAccId() +" has changed, "
				+ "the sum of "+sum+" was added; new sum is: "+ account.getMoney());
	}


}
