package psp.practicas.practica3.colario;

public class Node {
	
	private String texto;
	private Node next;
	
	/**
	 * CONSTRUCTOR
	 */
	public Node(String texto){
		this.texto = texto;
	}
	
	/*
	 * Getters and setters 
	 */
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
	
	
}
