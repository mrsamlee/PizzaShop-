
import java.util.Scanner;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String StreetName;
	private String City;
	private String State;
	private String ZipCode;	
	
	public String getStreetName(){
		return StreetName;
	}
	public String getCity(){
		return City;
	}
	public String getState(){
		return State;
	}
	public String getZipCode(){
		return ZipCode;
	}
	public void setStreetName(String name){
		StreetName = name;
	}	
	public void setCity(String city){
		City = city;
	}
	public void setState(String state){
		State = state;
	}
	public void setZipCode(String zip){
		ZipCode = zip;
	}
	public static Address inputAddress(Scanner in){
		Address a = new Address();
		System.out.println("Enter your address");
		System.out.println("Street name: ");
		a.setStreetName(in.nextLine());
		System.out.println("City: ");
		a.setCity(in.nextLine());
		System.out.println("State: ");
		a.setState(in.nextLine());
		System.out.println("Zipcode: ");
		a.setZipCode(in.nextLine());
		return a;
	}
}
