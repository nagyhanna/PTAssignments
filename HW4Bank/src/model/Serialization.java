package model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Serialization {

	private FileOutputStream fileOut;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	
	public Serialization(){
		
	}
	
	public void serialization(Map<Person, Set<Account>> client) {
		try {
			fileOut = new FileOutputStream("clientss.ser");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(client);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ser(Map<Person, Set<Account>> client){
		try {
			fileOut = new FileOutputStream("clientss.ser");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(client);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<Person, Set<Account>> deserialization() {
		try {
			FileInputStream fileIn = new FileInputStream("clientss.ser");
			in = new ObjectInputStream(fileIn);
			System.out.println("nnnn");
			Map<Person, Set<Account>> clients = (HashMap<Person, Set<Account>>) in.readObject();
			//System.out.println(in.readObject().toString());
			
			for (Map.Entry<Person, Set<Account>> entry : clients.entrySet()) {
				Set<Account> acc = entry.getValue();
				for (Account account : acc) {
					
					System.out.println(entry.getKey().getName() + " --> " + account.getAccId()+" $: " +account.getMoney());
				}
			}
			return clients;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}