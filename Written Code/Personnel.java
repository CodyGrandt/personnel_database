import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Personnel {

	public static void main(String[] args) {
		
		ArrayList<Employee> personnel = new ArrayList<>();
		play(personnel);
		
	}
	public static void play(ArrayList<Employee> personnel) {
		
		printCommands();
		enterCommand(personnel);
		
	}
	public static void printCommands() {
		
		System.out.println("\n\n----------------------------------"
				+          "\n|Commands: n - New employee      |"
				+		   "\n|          c - Compute paychecks |"
				+	       "\n|          r - Raise wages       |"
				+		   "\n|          p - Print records     |"
				+          "\n|          d - Download data     |"
				+ 		   "\n|          u - Upload data       |"
				+          "\n|          q - Quit              |"
				+          "\n----------------------------------");
	}
	public static void enterCommand(ArrayList<Employee> personnel) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter command:  ");
		String in = input.next().toLowerCase();
		
		switch (in) {
		
			case "n": commandN(personnel);
						break;
			case "c": commandC(personnel);
						break;
			case "r": commandR(personnel);
						break;
			case "p": commandP(personnel);
						break;
			case "d": commandD(personnel);
						break;
			case "u": commandU(personnel);
						break;
			case "q": commandQ();
						break;
			default:  System.out.println("Command was not recognized;  please try again.");
						play(personnel);
						break;
			
		}
	}
	public static void commandN(ArrayList<Employee> personnel) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter name of new employee:  ");
		String employeeName = input.nextLine();
		
		commandNPayType(personnel, employeeName);
		
		play(personnel);
		
	}
	public static void commandNPayType(ArrayList<Employee> personnel, String employeeName) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Hourly (h) or salaried (s):  ");
		String in = input.next().toLowerCase();
		
		switch (in) {
		
			case "h": double wage = getHourlyWage();
						HourlyEmployee employee1 = new HourlyEmployee(employeeName, wage);
						personnel.add(employee1);
						break;
			
			case "s": double salary = getYearlySalary();
						SalariedEmployee employee2 = new SalariedEmployee(employeeName, salary);
						personnel.add(employee2);
						break;
			
			default:  System.out.println("Input was not h or s;  please try again.");
						commandNPayType(personnel, employeeName);
						
		}
		
	}
	
	public static double getHourlyWage() {
		
		double wage = 0;
		Scanner input = new Scanner(System.in);
	
		try {
			
			System.out.print("Enter hourly wage:  ");
			wage = input.nextDouble();
			
		}
		catch (InputMismatchException e) {
			
			System.out.println("Input must contain only numbers, pleae try again.");
			wage = getHourlyWage();
			
		}
		
		return wage;
		
	}
	
	public static double getYearlySalary() {
		
		double salary = 0;
		Scanner input = new Scanner(System.in);
		
		try {
			
			System.out.print("Enter annual salary: ");
			salary = input.nextInt();
			
		}
		catch (InputMismatchException e) {
			
			System.out.println("Input must contain only numbers, please try again.");
			salary = getYearlySalary();
		}
		
		return salary;
		
	}
	
	public static void commandC(ArrayList<Employee> personnel) {
		
		Scanner input = new Scanner(System.in);
			
		for (int count = 0; count < personnel.size(); count++) {
			
			System.out.print("Enter number of hours worked by " + personnel.get(count).getName() + ":  ");
			
			int hoursWorked = input.nextInt();
			double weeksPay = personnel.get(count).computePay(hoursWorked);
			
			System.out.printf("Pay:  $%.2f\n", weeksPay);
			
		}
		
		play(personnel);
		
	}
	
	public static void commandR(ArrayList<Employee> personnel) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter percentage increase:  ");
		double percentInc = input.nextDouble();
		
		System.out.print("\nNew wages"
					+    "\n---------\n");
		
		for (int count = 0; count < personnel.size(); count++) {
			
			personnel.get(count).increaseWage(percentInc);
			System.out.println(personnel.get(count).toString());
			
		}
		
		play(personnel);
		
	}
	
	public static void commandP(ArrayList<Employee> personnel) {
		
		System.out.print("\nPersonnel Records"
				+        "\n-----------------\n");
		
		if (personnel.isEmpty())
			System.out.println("Your Personnel Records are Currently Empty.");
		
		
		for (int i = 0; i < personnel.size(); i++) {
			
			if (! personnel.isEmpty()) {
			
				System.out.println(personnel.get(i).toString());
		
			}
			
		}
				
		play(personnel);
		
	}
	
	public static void commandU(ArrayList<Employee> personnel) {
		
		String fileName = "employee.dat";
		try {
			
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			personnel = (ArrayList <Employee>)in.readObject();
			
			in.close();
			
		}
		
		catch (IOException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		catch (ClassNotFoundException e) {
			
			System.out.println(e.getMessage());
			
		}
	
		System.out.println("The employee records have been imported into the program.");	
		play(personnel);
		
	}
	
	public static void commandD(ArrayList<Employee> personnel) {
		
		String fileName = "employee.dat";
		
		try {
			
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(personnel);
			
		}
		
		catch (IOException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		System.out.println("The employee records have been saved to an external file.");		
		play(personnel);
		
	}
	public static void commandQ() {
		
		System.out.println("Exiting Program, See You Soon! :)");
		System.exit(0);
		
	}

}

