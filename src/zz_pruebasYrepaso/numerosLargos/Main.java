package zz_pruebasYrepaso.numerosLargos;

public class Main {
	
	public static void main(String[] arg) {
		
		IntBig b = new IntBig();
		b.limit(1000);
		b.stringToBigNumber("123543213456789");
		b.view();
		IntBig bCopy = new IntBig();
		bCopy.copy(b);
		bCopy.view();
		System.out.println(b.sonIguales(b, bCopy));
		bCopy.stringToBigNumber("09876543234567");
		bCopy.view();
		System.out.println(b.sonIguales(b, bCopy));
		b.copy(b,bCopy);
		b.view();
		bCopy.view();
		System.out.println("Cifras totales: " + b.numCifras());
		b.viewAllData();

		System.out.println("--------------");
		IntBig c = b.sum(b, bCopy);
		c.view();
		System.out.println("--------------");
		b.view();
		bCopy.view();
		
	}
	

	
	

}
