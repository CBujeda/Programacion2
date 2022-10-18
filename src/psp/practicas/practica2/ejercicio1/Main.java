package psp.practicas.practica2.ejercicio1;

/**
 * Programa java el cual calcula un vector con diferentes threads
 * y calcula cual tarda mas tiempo
 * @author Clara Bujeda Muñoz
 */
public class Main {
	
	/**
	 * Pre:
	 * Post: Metodo el cual viualiza un array 2D
	 */
	public static void viewArray(float[][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int r = 0; r < array[i].length;r++) {
				System.out.print(array[i][r] + " | ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual visualiza un vector o 
	 * 		 array 1D
	 */
	public static void viewSimpleVect(float[] vector) {
		for(int i = 0; i < vector.length; i++) {
			System.out.print(vector[i] + " | ");
		}
		System.out.println();
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual genera un valor random entre dos
	 * 		 numeros
	 */
	public static int getRandomInt(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
	
	/**
	 * Pre:
	 * Post: Metodo el cual genera datos de un array 2D
	 * 		 de forma pseudoaleatoria
	 */
	public static float[][] genArray(float[][] array){
		for(int i = 0; i < array.length; i++) {
			for(int r = 0; r < array[i].length;r++) {
				array[i][r] = getRandomInt(10,0);
			}
		}
		return array;
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual genera datos de un vector
	 * 		 de forma pseudoaleatoria
	 */
	public static float[] genVector(float[] vector) {
		for(int i = 0; i < vector.length; i++) {
			vector[i] = getRandomInt(10, 0);
		}
		return vector;
	}
	
	/**
	 * Pre:
	 * Post: Metodo Inicial el cual hace ejecucion del programa
	 */
	public static void main(String[] arg) {
		int size= 512; 								//Tamaño de array inicial
		float[][] array = new float[size][size];	//Creamos array
		genArray(array);							//Generamos datos de array
		float[] vector = new float[size];			//Creamos Vector
		genVector(vector);							//Generamos datos Vector							
		viewArray(array);							//Visualizamos Array
		System.out.println("║==================Vector original===================║");
		viewSimpleVect(vector);						//Visualizamos vector
		float[] result = new float[size];			//Creamos array de resultados
		DataShare ds = new DataShare(array, vector, result);	//Creamos objeto de datos
		long inicio = System.currentTimeMillis();	//Obtenemos tiempo actual
		ds.settMax((int) inicio);					//Guardamos tiempo
		CalcThd[] ct = new CalcThd[16];				//Creamos tabla de threads de calculo
		int cont = 0;								//Variable de conteo
		for(int i = 0; i < ct.length;i++) {
			cont ++;
			ct[i] = new CalcThd(ds,32*i,(32*i)+32,cont);	//Creamos threads
		}
		for(int i = 0; i < ct.length; i++) {ct[i].start();}	// Iniciamos threads
		for(int i = 0; i < ct.length; i++) {
			try { ct[i].join();								//Main en espera
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		CalcMod cm = new CalcMod(ds);						//Creamos thread de modulo
		System.out.println("║=============Calculando nuevo vector..==============║");
		viewSimpleVect(ds.getResult());						//Vissualizamos el resultado
		System.out.println("║============Modulo del vector resultante============║");
		cm.start();											//Iniciamos CalMod
		try {cm.join();} catch (InterruptedException e) {e.printStackTrace();}	// Pausamos Main
		
		System.out.println("+-----------------------------+\n"
				 + "+ El thread mas lento es: " + ds.getIdMaxLento() + "\n"		//Mostramos thread mas lento
				 + "+-----------------------------+");
	}
	


}
