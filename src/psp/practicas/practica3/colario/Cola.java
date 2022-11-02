package psp.practicas.practica3.colario;

/**
 * ES - Objeto el cual crea una cola
 * EN - Object which creates a queue
 */
public class Cola {

	private Node first;
	private Node last;
	private int maxSize;
	private int size = 0 ;
	
	/*
	 * CONSTRUCTORS
	 */
	public Cola(int maxSize) {
		super();
		this.maxSize = maxSize;
		
	}

	public Cola(Node first, Node last) {
		this.first = first;
		this.last = last;
	}

	/*
	 * Getters and setters 
	 */
	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public Node getLast() {
		return last;
	}

	public void setLast(Node last) {
		this.last = last;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Pre:
	 * Post: ES - Método el cual añade un nodo a la cola.
	 * 		 EN - This function add a node on the queue.
	 */
	public boolean push(Node node) {
		if(this.maxSize > size) {
			try {
				if(size == 0) {
					first = node;
					last = node;
					size++;
					return true;
				}else {
					Node p = last;
					p.setNext(node);
					last = node;
					
					size++;
					return true;
				}
			} catch(Exception e) {
				System.out.println(e.toString());
				return false;
			}	
		}else {
			System.out.println("[ERROR]  Size = " + (this.size+1) + " !> Size Max " + this.maxSize);
			return false;
		}
	}
	
	/**
	 * Pre:
	 * Post: ES - Método el cual saca un nodo de la cola.
	 * 		 EN - This function pull out the node to the queue. 
	 */
	public Node pop() {
		Node p = null; 
		try {
			if(size > 0) {
				p = first;
				first = first.getNext();
				size--;
			}else {
				System.out.println("(X) [NO POP] NO DATA");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return p;	
	}

	/**
	 * Pre:
	 * Post: ES - Método el cual comprueba si la cola está vacía.
	 * 		 EN - This function verify if the queue is empty
	 */
	public boolean isEmpty() {
		if(size == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * Pre:
	 * Post: ES - Método el cual imprime por consola la cola.
	 * 		 EN - This function print the queue in the console.
	 */
	public void show() {show(true);}
	public void show(boolean ln) {
		if(size > 0) {
			Node p = first;
			System.out.print("(First)");
			for(int i = 0; i < size; i++) {
				System.out.print("[ "+p.getTexto()+" ]");
				p = p.getNext();
			}
			System.out.print("(Last)");
			if(ln == true) {
				System.out.println();
			}
		} else {
			System.out.println("(X) [NO SHOW] NO DATA");
		}
	}
}