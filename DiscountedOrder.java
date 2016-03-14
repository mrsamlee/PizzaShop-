import javax.persistence.*;

@Entity
@Table(name="DiscountedOrders_Table")
public class DiscountedOrder extends Order{
	
	private double discountRate;
	
	public DiscountedOrder(){
		discountRate = .9;
		super.setPrice(super.getPrice()*discountRate);
		super.isDiscounted();
	}
	
	public void setPrice(){
		super.setPrice(super.getPrice()*discountRate);
	}

	
}
