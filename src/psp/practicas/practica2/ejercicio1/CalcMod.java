package psp.practicas.practica2.ejercicio1;

/**
 * Objeto thread el cual se encarga de calacular el modulo
 * de un vector
 * @author Clara Bujeda Muñoz
 */
public class CalcMod extends Thread{

	DataShare ds;
	
	public CalcMod(DataShare ds) {
		super();
		this.ds = ds;
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual se ejecuta al iniciar
	 * 		 el thread
	 */
	public void run() {	
		float sum = 0;
		for(int i = 0; i < ds.getResult().length; i++) {
			sum = ds.getResult()[i] * ds.getResult()[i] + sum;
		}
		ds.setModule((float) Math.sqrt(sum));
		System.out.println("                          −−−−−→");
		System.out.println("El modulo del vector es: |" + ds.getModule()+"|");
	}
}
