import java.io.Serializable;

public class HourlyEmployee extends Employee implements Serializable {
	
	private int hours;

	public HourlyEmployee(String name, double wage) {
		
		super(name, wage);
	
	}

	public double computePay(int hours) {
	
		double pay = 0;
		
		if (hours <= 40) {
			pay = hours * getWage();
		}
		else {
			
			int overtime = hours - 40;
			double overtimeWage = getWage() * 1.5;
			pay = (getWage() * 40) + (overtime * overtimeWage);
			
		}
		return pay;
	}
	public String toString() {
		
		String hourlyWage = ("$" + Utilities.toDollars(getWage()) + "/hour");
		
		return	Utilities.pad(getName(), 40 - hourlyWage.length()) + hourlyWage;
	}	
	
}

