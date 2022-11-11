package ad.ejercicios.ejercicio_1;

import java.util.Scanner;

public class Main {

	public static void help() {
		System.out.println("████████████████████████═════════════════════════════════╗\n"
						 + "█ Comandos:            █ Info:                           ║\n"
						 + "█ > mysql get alumnos  █ (Tabla alumnos MySql)           ║\n"
						 + "█ > SqlM get alumnos   █ (Tabla alumnos Sql Microsoft)   ║\n"
						 + "█ > exit               █ (Salir de la app)               ║\n"
						 + "█ > ?                  █ (ayuda, esta ventana)           ║\n"
						 + "████████████████████████═════════════════════════════════╝\n");
	}
	
	public static void main(String[] arg) {
		SQLS_MySql sqmy = new SQLS_MySql();
		SQLS_Microsoft sqmi = new SQLS_Microsoft();
		
		Scanner sc = new Scanner(System.in);
		help();
		while(true) {
			System.out.print("?> ");
			String option = sc.nextLine();
			if(option.equalsIgnoreCase("mysql get alumnos")) {
				sqmy.makeQuery();
			}else if(option.equalsIgnoreCase("sqlm get alumnos")){
				sqmi.makeQuery();
			}else if(option.equalsIgnoreCase("exit")) {
				break;
			}else if(option.equalsIgnoreCase("?") || option.equalsIgnoreCase("help")){
				help();
			}else {
				System.out.println("[?] Sintaxis del comando incomprensible, ecriba ? para help");
			}
		}
		sqmy.close();
		sqmi.close();
		
	}

	
	
}
