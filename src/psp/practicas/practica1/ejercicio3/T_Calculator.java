package psp.practicas.practica1.ejercicio3;
/**
 * Objeto Thread el cual calcula la media, el maximo
 * y el minimo o la desviacion tipica de una tabla 
 * @author Clara Bujeda Muñoz
 */
public class T_Calculator extends Thread  {
	
	int t[];
	int accion;
	
	/**
	 *  -- int accion
	 * 		- = 1  : Calcula la media
	 * 		- = 2  : Calcula el minimo y el maximo
	 * 		- = 3  : Calcula la desviacion tipica
	 * Pre:
	 * Post: Metodo constructor
	 */
	public T_Calculator(int t[],int accion) {
		super();
		this.t = t;
		this.accion = accion;
	}
	
	/**
	 * Pre:
	 * Post: Metodo principal el cual calcula los datos
	 * 		 al cargar el thread
	 */
	public void run() {
		if(accion == 1) {
			System.out.println("La media es: " + media());
		}else if(accion == 2) {
			int min = t[0];
			int max = t[0];
			for(int i = 0; i < t.length;i++) {
				if(t[i] < min) { 
					min = t[i];
				} else if(t[i] > max) { 
					max = t[i]; }
			}
			System.out.println("El valor max = " + max +" | El Valor min = " + min);
		}else if(accion == 3) {
			double media = media();
			double[] t2 = new double[t.length];
			for(int i = 0; i < t.length;i++) {t2[i] = t[i];}
			for(int i = 0 ;i< t2.length;i++) {
				t2[i] = t2[i] - media;
				t2[i] = t2[i] * t2[i];
			}
			double sum = 0;
			for(int i = 0; i < t2.length;i++) {sum = sum + t2[i];}
			double result = sum / t2.length;
			result = Math.sqrt(result);
			System.out.println("La desviacion tipica es: " + result);
		}else {
			System.out.println("[ERROR] - El valor del thread debe de estar"
							 + " comprendido entre 1 y 3 estos incluidos");	
		}
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual devuelve la media de { t[] }
	 */
	public double media() {
		int sum = 0;
		for(int i = 0; i < t.length;i++) {
			sum = sum + t[i];
		}
		double media = sum/t.length;
		return media;
	}

}
