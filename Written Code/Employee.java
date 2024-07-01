import java.io.Serializable;

public abstract class Employee implements Serializable {

	private String name;
	private double wage;
	
	public Employee (String name, double wage) {
		
		this.name = name;
		this.wage = wage;
		
	}
	public String getName() {
		return name;
		
	}
	
	public double getWage() {
		
		return wage;
		
	}
	
	public void setName(String newName) {
		
		name = newName;
		
	}
	
	public void setWage(double newWage) {
	
		wage = newWage;
		
	}
	public void increaseWage(double percent) {
	
		double payIncrease = wage * (percent/100);
		wage = wage + payIncrease;
		
	}
	public abstract double computePay(int hours);
}

