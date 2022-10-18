package psp.practicas.practica2.ejercicio1;

/**
 * Objeto compartido el cual se encarga de guardar datos
 * @author Clara Bujeda Muñoz
 */
public class DataShare {

	private float [] [] array;
	private float [] vector;
	private float [] result;
	private float module;
	private int tMax;
	private int idMaxLento;
	public DataShare(float[][] array, float[] vector, float[] result) {
		this.array = array;
		this.vector = vector;
		this.result = result;
		this.module = 0;
	}
	
	
	/*
	 * GETTERS AND SETTERS 
	 */
	public float[][] getArray() {
		return array;
	}

	public void setArray(float[][] array) {
		this.array = array;
	}

	public float[] getVector() {
		return vector;
	}

	public void setVector(float[] vector) {
		this.vector = vector;
	}

	public float[] getResult() {
		return result;
	}

	public void setResult(float[] result) {
		this.result = result;
	}

	public float getModule() {
		return module;
	}

	public void setModule(float module) {
		this.module = module;
	}

	public float gettMax() {
		return tMax;
	}

	public void settMax(int tMax) {
		this.tMax = tMax;
	}

	public float getIdMaxLento() {
		return idMaxLento;
	}

	public void setIdMaxLento(int idMaxLento) {
		this.idMaxLento = idMaxLento;
	}	
}
