package ad.ejercicios.ejercicio_1;

import java.util.Scanner;

public class Main {

	/**
	 * Pre:
	 * Post: Método con el cual mostramos por pantalla la ayuda
	 * 		 del programa.
	 */
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
		SQLS_MySql sqmy = new SQLS_MySql();			//Creamos objeto para Mysql Access
		SQLS_Microsoft sqmi = new SQLS_Microsoft(); //Creamos objeto para Microsoft sql
		Scanner sc = new Scanner(System.in);		//Lector
		help();										//Mostramos ayuda
		while(true) {
			System.out.print("?> ");
			String option = sc.nextLine();
			if(option.equalsIgnoreCase("mysql get alumnos")) {
				sqmy.makeQuery();								//Hacemos query con mysql 
				sqmy.close();	//Cerramos conexión
			}else if(option.equalsIgnoreCase("sqlm get alumnos")){
				sqmi.makeQuery();								//Hacemos query con Microsoft sql
				sqmi.close();	//Cerramos conexión
			}else if(option.equalsIgnoreCase("exit")) {
				break;											//Rompemos bucle
			}else if(option.equalsIgnoreCase("?") || option.equalsIgnoreCase("help")){
				help();											//Mostramos ayuda
			}else {
				System.out.println("[?] Sintaxis del comando incomprensible, ecriba ? para help");
			}
		}
		
		
		
	}

	
	
}
