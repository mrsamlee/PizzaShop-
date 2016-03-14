import java.util.List;


//this will make DAO method calls in each of these methods, DAO will make calls to persist data to db
public class ServiceLayer{
	public static void makeOrder(Order order, Customer cust){
		ConcretePizzaShopDAO orderDAO = new ConcretePizzaShopDAO();
		orderDAO.insertOrder(order, cust);
	}
	public static Customer logIn(String name, String pass){
		ConcretePizzaShopDAO dao = new ConcretePizzaShopDAO();
		Customer t = dao.getByUser(name, pass);
		return t;
	}
	
	public static Customer signUp(String name, String pass, Address address){
		Customer customer = new Customer(name, pass, address);
		ConcretePizzaShopDAO dao = new ConcretePizzaShopDAO();
		dao.insertCustomer(customer);
		return customer;
	}

	public static List<Order> viewOrders(Customer cust){
		ConcretePizzaShopDAO dao = new ConcretePizzaShopDAO();
		return dao.viewOrdersDAO(cust);	
	}
	
	public static boolean updateOrder(int pKeyOfOrder, List<Topping> toppings, PizzaSize size, PaymentType ptype,  Customer cust){
		ConcretePizzaShopDAO DAO = new ConcretePizzaShopDAO();
		for(Order o : viewOrders(cust)){
			if(o.getID() == pKeyOfOrder){
				if(size!=null)
					o.setSize(size);
				if(!toppings.isEmpty())
					o.setToppings(toppings);
				else
					o.addPriceOfPersistedToppings();
				if(ptype!=null)
					o.setPayment(ptype);
				if(o.getIsDiscounted() == true)
					o.setPrice(o.getPrice()*.9);
				DAO.updateOrder(o);
				return true;
			}
		}
		return false;
	}
	
	public static boolean cancelOrder(int pKeyOfOrder, Customer cust){
		ConcretePizzaShopDAO DAO = new ConcretePizzaShopDAO();
		for(Order o : viewOrders(cust)){
			if(o.getID() == pKeyOfOrder){
				DAO.deleteOrder(o);
				return true;
			}
		}
		return false;
	}
}
