package zz_pruebasYrepaso.numerosLargos;

public class IntBig {
	
	private int digits;
	private int[] bigNum;

	public IntBig() { super(); }
	
	public IntBig(int limit) {
		super();
		bigNum = new int[limit];
	}
	
	public void limit(int limit) { bigNum = new int[limit]; }
	
	
	public void stringToBigNumber(String BigNumber) { 
		if(bigNum == null) {
			bigNum = new int[BigNumber.length()];
		}
		if(BigNumber.length() <= bigNum.length) {
			
			for(int i = 0;i < BigNumber.length();i++) {
				try {
					bigNum[i] = Integer.parseInt(BigNumber.substring(i,i+1));
				}catch(Exception e) {
					System.err.println("Error al parsear el string");
				}
			}
			digits = BigNumber.length();
		}else {
			System.out.println("[ADVERTENCIA] - No es posible añadir el entero el limite es demasiado pequeño");
		}
	}
	
	public void copy(IntBig original) {
		bigNum = new int[original.getBigNum().length];
		for(int i = 0; i < original.getBigNum().length; i++) {
			bigNum[i] = original.getBigNum()[i];
		}
		digits = original.getDigits();
	}
	
	public boolean sonIguales(IntBig a, IntBig b) {
		boolean equal = true;
		if(a.getBigNum().length != b.getBigNum().length) {equal = false;
		}else {
			for(int i = 0; i < a.getBigNum().length;i++) {
				if(a.getBigNum()[i] != b.getBigNum()[i]) {
					equal = false;
					break;
				}
			}
		}
		return equal;
	}

	public void copy(IntBig original, IntBig copy) {
		copy.setBigNum(new int[original.getBigNum().length]);
		for(int i = 0; i < original.getBigNum().length; i++) {
			copy.getBigNum()[i] = original.getBigNum()[i];
		}
		copy.setDigits(original.getDigits());
	}
	
	public int numCifras() {
		return digits;
	}
	public int[] getBigNum() {
		return bigNum;
	}

	public void setBigNum(int[] bigNum) {
		this.bigNum = bigNum;
	}
	
	public void view() {
		System.out.println();
		for(int i = 0; i < digits;i++) {
			System.out.print(bigNum[i]);
		}
		System.out.println();
	}
	
	public void viewAllData() {
		System.out.println();
		for(int i = 0; i < bigNum.length;i++) {
			System.out.print(bigNum[i]);
		}
		System.out.println();
	}
	
	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}
	
	public IntBig sum(IntBig a,IntBig b) {
		int max = a.getDigits();
		if(a.getDigits() < b.getDigits()) {
			max = b.getDigits();
		}
		IntBig c = new IntBig();
		c.limit(max);
		int sumA=0, sumB=0,res=0,eleva=0;
		String part ="",elevaS="";
		for(int i = max; i >= 0; i--) {
			System.out.print(i+ " ");
			if(max <= a.getDigits()) {sumA = a.getBigNum()[i];}
			if(max <= b.getDigits()) {sumB = b.getBigNum()[i];}
			res = sumA+sumB;
			res = res+eleva;
			if(part.length() > 1) {
				part = Integer.toString(res);
				elevaS = part.substring(0, part.length()-1);
				part = part.substring( part.length()-1,part.length());
				eleva = Integer.parseInt(elevaS);
				res = Integer.parseInt(part);
			} 
			
			
			
		}
		return c;
	}
}
