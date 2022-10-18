package psp.practicas.practica2.ejercicio1;

/**
 * Objeto Thread el cual calcula una cantidad de datos
 * estableciados de la matriz y el vector
 * @author Clara Bujeda Muñoz
 */
public class CalcThd extends Thread {

	private DataShare ds;
	private int stLine;
	private int enLine;
	private int id_thread;
	
	/**
	 * CONSTRUCTOR
	 */
	public CalcThd(DataShare ds) {
		super();
		this.ds = ds;
	}
	
	/**
	 * CONSTRUCTOR
	 */
	public CalcThd(DataShare ds, int stLine, int enLine,int id_thread) {
		super();
		this.ds = ds;
		this.stLine = stLine;
		this.enLine = enLine;
		this.id_thread = id_thread;
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual corre al iniciar el thread
	 */
	public void run() {
		for(int i = this.stLine; i < this.enLine; i++) {
			calc(i); 	//Calculamos dato de vector
		}
		masLento();		//Es mas lento?
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual calcula un dato del vector
	 */
	public void calc(int mxLine) {
		if(ds.getArray()[mxLine].length == ds.getVector().length && 
				ds.getVector().length == ds.getResult().length) {
			float sum = 0;
			for(int i = 0; i < ds.getArray()[mxLine].length;i++) {
				sum = sum + ds.getArray()[mxLine][i] * ds.getVector()[i];
			}
			ds.getResult()[mxLine] = sum;
		}
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual calcula si el thread es el mas
	 * 		 lento si es asi se añade a los datos
	 */
	public synchronized void masLento() {
		long fin = System.currentTimeMillis();
		System.out.println("id: " + this.getId_thread() + " Tiempo: " + fin + "      --> [FINALIZADO]");
		if(this.ds.gettMax() < fin) {					//Comparacion de tiempo
			this.ds.settMax((int) fin);
			this.ds.setIdMaxLento(this.getId_thread());
		}
	}

	
	/*
	 * GETTERS AND SETTERS
	 */
	public DataShare getDs() {
		return ds;
	}

	public void setDs(DataShare ds) {
		this.ds = ds;
	}

	public int getStLine() {
		return stLine;
	}

	public void setStLine(int stLine) {
		this.stLine = stLine;
	}

	public int getEnLine() {
		return enLine;
	}

	public void setEnLine(int enLine) {
		this.enLine = enLine;
	}

	public int getId_thread() {
		return id_thread;
	}

	public void setId_thread(int id_thread) {
		this.id_thread = id_thread;
		System.out.println(id_thread);
	}

	
	
	
	
}
