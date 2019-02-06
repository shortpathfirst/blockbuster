package model;
import java.util.ArrayList;

import controller.CalcoloSuVotanti;

public abstract class Area {
	private String nomeArea;
	private String regione;
	private String citta;
	private String municipio;
	private String quartiere;
	private String cap;
	protected ArrayList<CartaIscritto> listaVotanti;
	
	public Area(String nomeArea, String regione, String citta, String municipio, String quartiere, String cap) {
		super();
		this.nomeArea = nomeArea;
		this.regione = regione;
		this.citta = citta;
		this.municipio = municipio;
		this.quartiere = quartiere;
		this.cap = cap;
		this.listaVotanti = new ArrayList<>();
	}
	
	public String getNomeArea() {
		return nomeArea;
	}

	public String getRegione() {
		return regione;
	}

	public String getCitta() {
		return citta;
	}

	public String getMunicipio() {
		return municipio;
	}

	public String getQuartiere() {
		return quartiere;
	}

	public String getCap() {
		return cap;
	}

	public ArrayList<CartaIscritto> getListaVotanti() {
		return listaVotanti;
	}
	public void aggiungiVotante(CartaIscritto iscritto) {
		this.listaVotanti.add(iscritto);
	}
	public abstract void aggiungiAreaFiglio(Area figlio);
	public abstract ArrayList<Area> getAreeFiglie();
	public abstract Area getPadre();
	public abstract int calcolaNumeroVotazioni(CalcoloSuVotanti calcolo);
}
