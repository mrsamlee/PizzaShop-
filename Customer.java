import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER_TABLE")
public class Customer {
	@Id
	@GeneratedValue
	private int customerId;
	
	@Column(name = "username", unique=true)
	private String username;
	private String password;
	
	@OneToMany(mappedBy="customer", targetEntity = Order.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Order> orders;
	
	private Address address;
	
	Customer(){
		username = "";
		password = "";
		address = new Address();
		orders = new ArrayList<Order>();
	}
	
	Customer(String uName, String pWord, Address address){
		username = uName;
		password = pWord;
		this.address = address;
		orders = new ArrayList<Order>();
	}
	
	public int getID(){
		return this.customerId;
	}
	
	public String getUsername(){
		return username;
	}
	public boolean setOrder(Order order){
		orders.add(order);
		return true;
	}
	
	public String print(){
		return "Username: " + username + "\nPassword: " + password +"\nID: " + this.customerId;
	}
}
