import java.util.List;


public interface PizzaShopDAO {
	Customer insertCustomer(Customer customer);
	Order insertOrder(Order order, Customer customer);
	boolean updateOrder(Order order);
	boolean deleteOrder(Order order);
	List viewOrdersDAO(Customer customer);
	Order getByKey(int OrderPrimaryKey);
	Customer getByUser(String name, String pass);
}
