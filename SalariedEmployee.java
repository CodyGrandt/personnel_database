import java.io.Serializable;

public class SalariedEmployee extends Employee implements Serializable {

	private double salary;
	
	public SalariedEmployee(String name, double salary) {
		
		super (name, (salary/(52*40)));
		this.salary = salary;
		
	}
	public double getSalary() {
		
		return getWage()*40*52;
		
	}
	public void setSalary(double newSalary) {
		
		salary = newSalary;
		
	}
	public double computePay(int hours) {
		
		return getWage() * 40;
		
	}
	public String toString() {
		
		String yearlySalary = ("$" + Utilities.toDollars(getSalary()) + "/year");
		
		return	Utilities.pad(getName(), 40 - yearlySalary.length()) + yearlySalary;
		
	}
	
	
}

