import java.util.ArrayList;
import java.util.Scanner;

public class PresentationLayer {
	public static Scanner in = new Scanner(System.in);
	public static int TOTALTOPPINGSCHOICE = 10;
	public static void main(String[] args) {
	
		System.out.println("========Samson's Pizza Shop========");
		System.out.format("1. Sign Up %8s 2. Log in", "");
		System.out.println("\n===================================");
		switch(Integer.parseInt(in.nextLine())){
			case 1: signup(); menu(false, logIn());break;
			case 2: menu(false, logIn());break;
		}
		
		System.out.println("\nThank you for choosing Samson's Pizza!");
		
		in.close();
		System.exit(0);
	}
	public static void menu(boolean a, Customer cust){
		System.out.println("\nWELCOME, "+ cust.getUsername() + "!");
		System.out.println("===========USER MENU===============");	
		System.out.format("1. Regular Order  4. Change Order", "");
		System.out.format("\n2. Discount Order 5. Cancel Order", "");
		System.out.format("\n3. View Order %3s 6. Exit","");
		System.out.println("\n===================================");
		if(a == false){
			switch(Integer.parseInt(in.nextLine())){
			case 1: createOrders(cust, true); menu(false, cust);break;
			case 2: createOrders(cust, false); menu(false, cust);break;
			case 3: viewOrders(cust); menu(false, cust); break;
			case 4: changeOrder(cust); menu(false, cust); break;
			case 5: cancelOrder(cust); menu(false, cust); break;
			case 6: break;
			}
		}
		
	}

	public static Customer signup(){
		System.out.println("====Sign Up====");
		System.out.println("Enter username");
		String name = in.nextLine();
		System.out.println("Enter password");
		String pass = in.nextLine();
		System.out.println("Confirm password");
		String p = in.nextLine();
		if(!p.equals(pass)){
			System.out.println("Passwords don't match. Try again - make sure they're the same.");
			signup();
		}else{
			Customer cust = ServiceLayer.signUp(name, pass, Address.inputAddress(in));
			System.out.println("\nUser created");
			return cust;
		}
		return null;
	}
	public static Customer logIn(){
		System.out.println("====Log In====");
		System.out.println("Enter username");
		String name = in.nextLine();
		System.out.println("Enter password");
		String pass = in.nextLine();	
		
		return ServiceLayer.logIn(name, pass);
	}
	
	public static void createOrders(Customer cust, boolean notDiscounted){
		Order order = null;
		if(notDiscounted == true){
			System.out.println("===========Regular Order===========");
			order = new Order();
		}else if(notDiscounted == false){
			System.out.println("==========Discounted Order=========");
			order = new DiscountedOrder();
		}
		ArrayList<Topping> toppings = new ArrayList<Topping>();
		System.out.println("Size: 1. Small($3.00)  2. Medium($5.00)  3. Large($7.00)");
		switch(Integer.parseInt(in.nextLine())){
			case 1: order.setSize(PizzaSize.SMALL); break;
			case 2: order.setSize(PizzaSize.MEDIUM); break;
			case 3: order.setSize(PizzaSize.LARGE); break;
		}
		for(int i=1;i<4;i++){
			System.out.println("Topping " + i + ":\n 1. Pepperoni(+$1.00)    2. Mushrooms(+$.75)     3. Onions(+$.75)         4. Sausage(+$.75)    5. Bacon(+$.75)");
			System.out.println(" 6. Extra cheese(+$.75)  7. Black olives(+$.75)  8. Green peppers(+$.75)  9. Pineapple(+$.75)  10. Spinach(+$.75)\n 11.None\n");
			int t = Integer.parseInt(in.nextLine());
			if(t<=TOTALTOPPINGSCHOICE){
				Topping temp = new Topping();
				temp.addTopping(t);
				toppings.add(temp);
			}
		}
		order.setToppings(toppings);
		if(order instanceof DiscountedOrder){
			((DiscountedOrder) order).setPrice();
		}
		System.out.println("Total: $"+order.getPrice() + " Payment type? \n1. Cash 2. Visa 3. Master");
		switch(Integer.parseInt(in.nextLine())){
			case 1: order.setPayment(PaymentType.CASH); break;
			case 2: order.setPayment(PaymentType.VISA); break;
			case 3: order.setPayment(PaymentType.MASTER); break;
		}
		ServiceLayer.makeOrder(order, cust);			
	}
	
	public static void viewOrders(Customer cust){
		System.out.println("Orders for " + cust.getUsername());
		for(Order a : ServiceLayer.viewOrders(cust)){
			a.print();
		}
	}
	
	public static void changeOrder(Customer cust){
		PizzaSize size = null;
		PaymentType pType = null;
		ArrayList<Topping> toppings = new ArrayList<>();
		System.out.println("Id of order to change: ");
		//add a check for correct ID correspondence here 
		int id = Integer.parseInt(in.nextLine());
		System.out.println("Change size? 1. Yes  2. No");
		switch(Integer.parseInt(in.nextLine())){
		case 1: 
			System.out.println("Size: 1. Small  2. Medium  3. Large");
			switch(Integer.parseInt(in.nextLine())){
				case 1: size = PizzaSize.SMALL; break;
				case 2: size = PizzaSize.MEDIUM; break;
				case 3: size = PizzaSize.LARGE; break;
			}
		}
		System.out.println("Change toppings? 1. Yes  2. No");
		switch(Integer.parseInt(in.nextLine())){
		case 1: 
			for(int i=1;i<4;i++){
				System.out.println("Topping " + i + ":\n 1. Pepperoni     2. Mushrooms     3. Onions         4. Sausage     5. Bacon");
				System.out.println(" 6. Extra cheese  7. Black olives  8. Green peppers  9. Pineapple  10. Spinach\n 11.None\n");
				int t = Integer.parseInt(in.nextLine());
				if(t<=TOTALTOPPINGSCHOICE){
					Topping temp = new Topping();
					temp.addTopping(t);
					toppings.add(temp);
				}
			}
		}
		System.out.println("Change paymentType? 1. Yes  2. No");
		switch(Integer.parseInt(in.nextLine())){
		case 1:
			System.out.println("Payment type? \n1. Cash 2. Visa 3. Master");
			switch(Integer.parseInt(in.nextLine())){
				case 1: pType = PaymentType.CASH; break;
				case 2: pType = PaymentType.VISA; break;
				case 3: pType = PaymentType.MASTER; break;
			}
		}
		
		if(ServiceLayer.updateOrder(id, toppings, size, pType, cust) == true)
			System.out.println("\nOrder upgraded");
		else 
			System.out.println("Something went wrong - you entered an order id that doesn't belong to you. Bye");
	}
	
	public static void cancelOrder(Customer cust){
		System.out.println("Id of order to cancel: ");
		if(ServiceLayer.cancelOrder(Integer.parseInt(in.nextLine()), cust) == true){
			System.out.println("\nSuccessfully cancelled your order");
		}else 
			System.out.println("\nYou do not own this orderID.");
	}	
}
