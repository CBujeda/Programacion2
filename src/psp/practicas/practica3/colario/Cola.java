package psp.practicas.practica3.colario;

public class Cola {

	private Node first;
	private Node last;
	private int size = 0 ;
	
	
	public Cola() {
		super();
	}

	public Cola(Node first, Node last) {
		this.first = first;
		this.last = last;
	}

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
	
	public boolean push(Node node) {
		
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
	}
	
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

	
	public boolean isEmpty() {
		if(size == 0) {
			return false;
		}else {
			return true;
		}
	}
	
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