import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="TOPPING_TABLE")
public class Topping {
	
	@Id
	@GeneratedValue
	private int toppingId;
	private double price;
	private String toppings;
	
	Topping(){
		price = 0;
		toppings = "N/A";
	}
	public double getPrice(){
		return price;
	}
	
	public String getToppings(){
		return toppings;
	}
	
	/*
	 * number system for adding toppings. one key push > typing
	 */
	public void addTopping(int tNum){
		switch(tNum){
			case 1: toppings = "Pepperoni";
					price += 1.00;break;
			case 2: toppings = "Mushrooms";
					price += .75;break;
			case 3: toppings = "Onions";
					price += .75;break;
			case 4: toppings = "Sausage";
					price += .75;break;
			case 5: toppings = "Bacon";
					price += .75;break;
			case 6: toppings = "Extra cheese";
					price += .75;break;
			case 7: toppings = "Black olives";
					price += .75;break;
			case 8: toppings = "Green peppers";
					price += .75;break;
			case 9: toppings = "Pineapple";
					price += .75;break;
			case 10: toppings = "Spinach";
					price += .75;break;
		}
	}
	
}
