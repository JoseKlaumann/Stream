package model.services;

import java.util.List;
import java.util.function.Predicate;

import entities.Employee;

public class EmployeeService {

	public double filteredSum(List<Employee> list, Predicate<Employee> criteria) {
		//dessa forma consigo deixar a classe fechada para alteracoes e ela se torna mais flexivel
		//estou criando um predicado de employee e assim consigo aplicar no programa principal
		double sum = 0.0;
		for (Employee p : list) {
			if(criteria.test(p)) {
				sum += p.getSalary();
			}
		}
		return sum;
	}
	
}
