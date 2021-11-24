package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;
import model.services.EmployeeService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		
		System.out.print("Enter salary: ");
		double salary = sc.nextDouble();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.printf("Email of people whose salary is more than %.2f%n", salary);
			
			List<String> emails = list.stream()
					//e necessario criar uma nova lista para a lista de emails de acordo com o valor
					.filter(p -> p.getSalary() > salary)
					//estou comparando os salarios com o salario determinado
					.map(p -> p.getEmail())
					//assim eu pego cada email referente ao salario que esta acima do valor que for digitado
					.sorted()
					//estou ordenando em ordem alfabetica
					.collect(Collectors.toList());
			        //transforma o stream de volta para o list
				
			emails.forEach(System.out::println);
			
			EmployeeService es = new EmployeeService();
			
			double sum = es.filteredSum(list, p -> p.getName().charAt(0) == 'M');
			//faz a soma dos salarios de todos os funcionarios que comecam com 'M'
			
			System.out.printf("Sum of salary of people whose name starts with 'M': %.2f", sum);
			
			
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		sc.close();
	}

}
