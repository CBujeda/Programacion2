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

	public void asiggned(int clientID) {
		if(this.clientID == -1) {
			this.clientID = clientID;
			this.reserved = true;
		}
	}
	
	public boolean isDisponible(String f) {
		if(getPlaza().equalsIgnoreCase(f) && reserved == false) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public String getPlaza() {
		return getFila() + getColumn(); 
	}
	
	public String getFila() {
		if(this.fila >=0 || this.fila < 4) {
			return fila+1 + "";
		}else {
			return "?";
		}
		
	}

	public String getColumn() {
		if(column >=0 || column < 4) {
			String[] dt = {"A","B","C","D"};
			return dt[column];
		}else {
			return "?";
		}
		
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}



	

	

}
