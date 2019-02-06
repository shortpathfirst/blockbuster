package model;

public class CartaIscritto {
	private int numero;
	private Iscritto iscritto;
	public CartaIscritto(int numero, Iscritto iscritto) {
		super();
		this.numero = numero;
		this.iscritto = iscritto;
	}
	public int getNumero() {
		return numero;
	}
	public Iscritto getIscritto() {
		return iscritto;
	}
	

}
