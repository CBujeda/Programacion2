package psp.practicas.practica4.server.avion;

public class Plaza {

	
	private int fila;	//1, 2, 3, 4
	
	private int column;	// A, B, C, D
	
	private boolean reserved;
	
	private int clientID;

	public Plaza(int fila, int column) {
		super();
		clientID = -1;
		reserved = false;
		this.fila = fila;
		this.column = column;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Asignamos una plaza a un cliente
	 */
	public void asiggned(int clientID) {
		if(this.clientID == -1) {
			this.clientID = clientID;
			this.reserved = true;
		}
	}
	
	/**
	 * Pre:
	 * Post: Verificamos si una plaza esta disponible
	 */
	public boolean isDisponible(String f) {
		if(getPlaza().equalsIgnoreCase(f) && reserved == false) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * Pre:
	 * Post: Obtenemos la plaza como string
	 */
	public String getPlaza() {
		return getFila() + getColumn(); 
	}
	
	/**
	 * Pre:
	 * Post: Metodo con el cual obtenemos fila
	 */
	public String getFila() {
		if(this.fila >=0 || this.fila < 4) {
			return fila+1 + "";
		}else {
			return "?";
		}
		
	}
	
	/*
	 * Pre:
	 * Post: Metodo el cual traduce la columna
	 * 		 a lenguaje humano y la devuelve
	 */
	public String getColumn() {
		if(column >=0 || column < 4) {
			String[] dt = {"A","B","C","D"};
			return dt[column];
		}else {
			return "?";
		}
		
	}

	/*
	 * Pre:
	 * Post: Verifica que este reservada
	 */
	public boolean isReserved() {
		return reserved;
	}

	/**
	 * Pre:
	 * Post: Establece una reserva
	 */
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}



	

	

}
