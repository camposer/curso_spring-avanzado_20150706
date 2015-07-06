package gui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import service.CalculadoraService;

@Component
public class CalculadoraGuiImpl implements CalculadoraGui {
	private Scanner scanner;
	@Autowired // Inyección por tipo
	@Qualifier("calculadoraService2")
//	@Resource(name = "calculadoraService2")
	private CalculadoraService calculadoraService;
	
	public CalculadoraGuiImpl() {
		scanner = new Scanner(System.in);
	}
	
	public void setCalculadoraService(CalculadoraService calculadoraService) {
		this.calculadoraService = calculadoraService;
	}

	@Override
	public void iniciar() {
		while(true) {
			System.out.println();
			System.out.println("1. Sumar");
			System.out.println("2. Restar");
			System.out.println("3. Multiplicar");
			System.out.println("4. Dividir");
			System.out.println("5. Salir");
			System.out.println("? ");
			
			String opcion = scanner.nextLine();
			
			if (opcion.equals("5"))
				break;
			
			System.out.print("a? ");
			float a = Float.parseFloat(scanner.nextLine());
			System.out.print("b? ");
			float b = Float.parseFloat(scanner.nextLine());
			
			if (opcion.equals("1"))
				System.out.println(a + " + " + b + " = " + 
						calculadoraService.sumar(a, b));
			else if (opcion.equals("2"))
				System.out.println(a + " - " + b + " = " + 
						calculadoraService.restar(a, b));
			else if (opcion.equals("3"))
				System.out.println(a + " * " + b + " = " + 
						calculadoraService.multiplicar(a, b));
			else if (opcion.equals("4"))
				System.out.println(a + " / " + b + " = " + 
						calculadoraService.dividir(a, b));
				
		}
	}
}
