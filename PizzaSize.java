 	
public enum PizzaSize {
	SMALL (3), MEDIUM (5), LARGE (7);
	
	private int pizzaCost;
	
	PizzaSize(int cost){
		pizzaCost = cost;
	}
	
	public int getPizzaCost(){
		return pizzaCost;
	}
	public void setPizzaCost(int cost){
		pizzaCost = cost;
	}
}

