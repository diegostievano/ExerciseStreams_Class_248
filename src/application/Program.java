package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employees;

/*Fazer um programa para ler os dados (nome, email e salário)
de funcionários a partir de um arquivo em formato .csv.

Em seguida mostrar, em ordem alfabética, o email dos
funcionários cujo salário seja superior a um dado valor
fornecido pelo usuário.

Mostrar também a soma dos salários dos funcionários cujo
nome começa com a letra 'M'.
Veja exemplo na próxima página.*/

public class Program {

	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");		
		String path = input.nextLine();	
		
		System.out.print("Enter salary: ");		
		Double baseSalary = input.nextDouble(); 
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			List<Employees> list = new ArrayList<>();
			 
			String line = br.readLine();
			
			while (line != null){
				String[] fields = line.split(",");
				
				String name = fields[0];
				String email = fields[1];
				Double salary = Double.parseDouble(fields[2]);
				
				list.add(new Employees(name, email, salary));
				
				line = br.readLine();
			}
			
			//double salaryBigger = list.stream().map(p -> p.getSalary()).filter(p -> )
			
			List<String> emails = list.stream().filter(e -> e.getSalary() > baseSalary).map(e -> e.getEmail()).sorted().collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", baseSalary));
			
			emails.forEach(System.out::println);
			
			Double sum = list.stream().filter(e -> e.getName().charAt(0) == 'M').map(e -> e.getSalary()).reduce(0.0, (x,y) -> x + y);			
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));
			
		}catch (IOException e) {
			System.out.println("Error " + e.getMessage());
		}
		
		input.close();

	}

}
