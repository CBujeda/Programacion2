package psp.practicas.practica6.Client;

import java.util.Scanner;

import psp.practicas.practica6.Config;

public class Input extends Thread implements Config{

	Scanner sc;
	
	public Input() {
		super();
		 this.sc = new Scanner(System.in);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		super.run();
		
		while(true) {
			String getInput = "";
			getInput = this.sc.nextLine();
		}
	}

}
