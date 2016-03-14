import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "ORDER_TABLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Order {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int orderId;
	private double price;
	private Timestamp deliveryTime;
	
	@Enumerated(EnumType.STRING)
	private PizzaSize size;
	@Enumerated(EnumType.STRING)
	private PaymentType payType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_Id")
	private Customer customer;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="order_topping", joinColumns = {@JoinColumn(name="order_id")}, 
									 inverseJoinColumns = {@JoinColumn(name="topping_id")})
	private List<Topping> toppings;
	private boolean isDiscounted;
	
	Order(){
		isDiscounted = false;
		customer = null;
		deliveryTime = new Timestamp(new java.util.Date().getTime());
	}
	
	public int getID(){
		return this.orderId;
	}
	
	public boolean setSize(PizzaSize s){
		size = s;
		price = s.getPizzaCost();
		return true;
	}
	
	public boolean setPayment(PaymentType p){
		payType = p;
		return true;
	}
	
	public boolean setTime(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 60);
		deliveryTime = new Timestamp(cal.getTime().getTime());
		return true;
	}
	public boolean setCustomer(Customer cus){
		customer = cus;
		return true;
	}
	
	public boolean setToppings(List<Topping> ts){
		toppings = new ArrayList<Topping>(ts);
		for(Topping d: ts){
			price += d.getPrice();
		}
		return true;
	}
	public double getPrice(){
		return price;
	}
	public void setPrice(double price){
		this.price = price;
	}
	
	public void addPriceOfPersistedToppings(){
		for(Topping d: toppings){
			price += d.getPrice();
		}
	}
	
	public List<Topping> getToppings(){
		return this.toppings;
	}
	
	public void isDiscounted(){
		this.isDiscounted = true;
	}
	
	public boolean getIsDiscounted(){
		return isDiscounted;
	}
	public void print(){
		System.out.println("\nOrder ID: " + this.orderId);
		System.out.println("Size: " +this.size.toString());
		System.out.println("Toppings: ");
		int count = 1;
		for(Topping t: toppings)
			System.out.println(count++ + "->"+t.getToppings()); 
		System.out.println("Payment Type: "+ this.payType.toString());
		System.out.println("Price: $" + this.price);
		if(isDiscounted == true)
			System.out.println("Discounted by 10%");
		System.out.println("Delivery Time: " + this.deliveryTime);
		
	}
}
