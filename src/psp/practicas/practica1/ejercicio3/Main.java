package psp.practicas.practica1.ejercicio3;

/**
 * Programa Java el Cual crea una tabla con 100 caracteres
 * random, posteriormente inicia 3 threads los cuales calculan
 * la media, el maximo, el minimo y la desviacion tipica
 * @author Clara Bujeda Muñoz
 */
public class Main {
	
	/**
	 * Pre:
	 * Post: Metodo principal el cual crea la tabla y crea e inicia
	 * 		 los threads
	 */
	public static void main(String[] arg) {
		int[] t = new int[100];
		for(int i = 0; i < t.length;i++) {t[i] = getRandomInt(1000,0);}
		for(int i = 0; i < t.length;i++) {System.out.print("[" + t[i] + "] ");}
		System.out.println();
		T_Calculator[] cal = new T_Calculator[3];
		for(int i = 1; i <= cal.length;i++) {cal[i-1] = new T_Calculator(t,i);}
		for(int i = 0; i < cal.length;i++) {cal[i].start();}
		try {
			for(int i = 0; i < cal.length;i++) {cal[i].join();}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual genera un valor random entre do
	 */
	public static int getRandomInt(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }

}
