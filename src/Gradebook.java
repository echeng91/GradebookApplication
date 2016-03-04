import java.util.Scanner;
import java.util.Calendar;
import java.text.DateFormat;

public class Gradebook {
	static Scanner sc = new Scanner(System.in);
	static final int numberOfRecords = 100;
	static String[] studentNames = new String[numberOfRecords];
	static String[] letterGrades = new String[numberOfRecords];
	static String[] genders = new String[numberOfRecords];
	static String[] majors = new String[numberOfRecords];
	static String[] statesOfOrigin = new String[numberOfRecords];
	static int[] grades = new int[numberOfRecords] ;

	public static void main(String[] args)
	{
		recordInit();
		getInput();
	}

	/*
	 * Initialize all student records to empty string
	 */
	private static void recordInit()
	{
		for(int i = 0; i < numberOfRecords; i++)
		{
			studentNames[i] = "";
			letterGrades[i] = "";
			genders[i] = "";
			majors[i] = "";
			statesOfOrigin[i] = "";
			grades[i] = 0;
		}
	}

	private static void getInput()
	{
		String choice = "";
		int counter = 0;

		while(counter < numberOfRecords 
				&& !choice.equalsIgnoreCase("exit"))
		{
			System.out.println("Would you like to add a record, display results, or exit?");
			System.out.print("Add, Display, or Exit?: ");
			choice = sc.nextLine();
			if(choice.equalsIgnoreCase("add"))
			{
				addRecord(counter);
				counter++;
			}
			else if(choice.equalsIgnoreCase("display"))
			{
				displayResults(counter);
			}
			else if(choice.equalsIgnoreCase("exit"))
			{
				System.out.println("Exiting Gradebook.");
			}
			else
			{
				System.out.println("Invalid Input.");
			}
			System.out.println("\n");
		}
	}

	private static void addRecord(int position)
	{
		System.out.print("Input student\'s name: ");
		studentNames[position] = sc.nextLine();
		System.out.print("Input student\'s letter grade: ");
		letterGrades[position] = sc.nextLine();
		System.out.print("Input student\'s gender: ");
		genders[position] = sc.nextLine();
		System.out.print("Input student\'s major: ");
		majors[position] = sc.nextLine();
		System.out.print("Input student\'s State of origin: ");
		statesOfOrigin[position] = sc.nextLine();
		System.out.print("Input student\'s numerical grade: ");
		grades[position] = sc.nextInt();
		sc.nextLine();//absorbs enter press
	}

	private static void displayResults(int count)
	{
		double overallAverage = 0;
		String choice = "";
		Calendar currentDate = Calendar.getInstance();
		DateFormat df = DateFormat.getDateInstance();
		
		System.out.printf("\nReport Date: %s%n", df.format(currentDate.getTime()));
		System.out.printf("Number of Students: %d%n", count);
		if(count == 0)
		{
			System.out.println("No records.");
		}
		else
		{
			for(int i = 0; i < count; i++)
			{
				overallAverage += (double)grades[i] / (double)count;
			}
			System.out.printf("Overall Average: %.3f%n", overallAverage);
			System.out.print("\nWould you like to see the average of a subset? ");
			choice = sc.nextLine();
			while(!choice.equalsIgnoreCase("yes") 
					&& !choice.equalsIgnoreCase("no"))
			{
				System.out.print("Invalid input. Please type 'yes' or 'no': ");
				choice = sc.nextLine();
			}
			while(choice.equalsIgnoreCase("yes"))
			{
				displaySubsetAverage(count);
				System.out.print("\nWould you like to see the average of another subset? ");
				choice = sc.nextLine();
				while(!choice.equalsIgnoreCase("yes") 
						&& !choice.equalsIgnoreCase("no"))
				{
					System.out.print("Invalid input. Please type 'yes' or 'no': ");
					choice = sc.nextLine();
				}
			}
		}
	}
	
	private static void displaySubsetAverage(int count)
	{
		int subsetCount = 0;
		int subsetTotal = 0;
		double subsetAverage;
		String searchTerm = "";
		
		System.out.println("What type of average would you like to see? Gender, major, or State of origin?");
		System.out.print("(gender, major, state): ");
		String searchType = sc.nextLine();
		while(!searchType.equalsIgnoreCase("gender") 
				&& !searchType.equalsIgnoreCase("major") 
				&& !searchType.equalsIgnoreCase("state"))
		{
			System.out.print("Invalid input. Please type 'gender', 'major', or 'state': ");
			searchType = sc.nextLine();
		}
		if(searchType.equalsIgnoreCase("gender"))
		{
			System.out.print("What gender would you like to see the average for?: ");
			searchTerm = sc.nextLine();
			for(int i = 0; i < count; i++)
			{
				if(genders[i].equalsIgnoreCase(searchTerm))
				{
					subsetCount++;
					subsetTotal += grades[i];
				}
			}
			if(subsetCount == 0)
			{
				System.out.println("No such records found.");
			}
			else
			{
				subsetAverage = (double)subsetTotal / (double)subsetCount;
				System.out.printf("Gender: %s%nAverage: %.3f%n", searchTerm, subsetAverage);
			}
		}
		else if(searchType.equalsIgnoreCase("major"))
		{
			System.out.print("What major would you like to see the average for?: ");
			searchTerm = sc.nextLine();
			for(int j = 0; j < count; j++)
			{
				if(majors[j].equalsIgnoreCase(searchTerm))
				{
					subsetCount++;
					subsetTotal += grades[j];
				}
			}
			if(subsetCount == 0)
			{
				System.out.println("No such records found.");
			}
			else
			{
				subsetAverage = (double)subsetTotal / (double)subsetCount;
				System.out.printf("Major: %s%nAverage: %.3f%n", searchTerm, subsetAverage);
			}
		}
		else if(searchType.equalsIgnoreCase("state"))
		{
			System.out.print("What State of origin would you like to see the average for?: ");
			searchTerm = sc.nextLine();
			for(int k = 0; k < count; k++)
			{
				if(statesOfOrigin[k].equalsIgnoreCase(searchTerm))
				{
					subsetCount++;
					subsetTotal += grades[k];
				}
			}
			if(subsetCount == 0)
			{
				System.out.println("No such records found.");
			}
			else
			{
				subsetAverage = (double)subsetTotal / (double)subsetCount;
				System.out.printf("State of Origin: %s%nAverage: %.3f%n", searchTerm, subsetAverage);
			}
		}
	}

}
