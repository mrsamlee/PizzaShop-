
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class ConcretePizzaShopDAO implements PizzaShopDAO{
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public Order insertOrder(Order order, Customer cust) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		order.setTime();
		order.setCustomer(cust);
		cust.setOrder(order);
		session.save(order);
		session.getTransaction().commit();
		session.close();
		
		return order;
	}

	public Customer insertCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(customer);
		session.getTransaction().commit();
		session.close();
		
		return customer;
	}

	public boolean updateOrder(Order order) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(order);
		session.getTransaction().commit();
		session.close();
		
		return true;
	}

	public boolean deleteOrder(Order order) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for(Topping t : order.getToppings()){
			session.delete(t);
		}
		session.delete(order);
		session.getTransaction().commit();
		session.close();
		
		return true;
	}
	
	public List<Order> viewOrdersDAO(Customer cus) {
		//query to find all orders with the foreign key = (cus ID)
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryString = "from Order where customer = :cus";
		Query query = session.createQuery(queryString);
		query.setParameter("cus", cus);
		List<Order> orders = query.list();
		session.getTransaction().commit();
		session.close();
		return orders;
	}

	@Override
	public Order getByKey(int OrderPrimaryKey) {
		Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    Object o = session.get(Order.class, OrderPrimaryKey);
	    session.getTransaction().commit();
	    session.close();
		return (Order)o;
	}

	@Override
	public Customer getByUser(String name, String pass) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryString = "from Customer where username = :name and password = :pass";
		Query query = session.createQuery(queryString);
		query.setString("name", name);
		query.setString("pass", pass);
		Customer temp = (Customer) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return temp;
	}

}
