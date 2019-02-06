package model;
import java.util.ArrayList;

import controller.CalcoloSuVotanti;

public class AreaFoglia extends Area {

	private Area padre;
	
	public AreaFoglia(String nomeArea, String regione, String citta, String municipio, String quartiere, String cap,Area padre) {
		super(nomeArea, regione, citta, municipio, quartiere, cap);
		this.padre = padre;
		this.padre.aggiungiAreaFiglio(this);
	}

	@Override
	public void aggiungiAreaFiglio(Area figlio) {
		throw new IllegalStateException("E' un area foglia");
	}

	@Override
	public ArrayList<Area> getAreeFiglie() {
		throw new IllegalStateException("E' un area foglia");
	}

	@Override
	public Area getPadre() {
		return padre;
	}

	@Override
	public int calcolaNumeroVotazioni(CalcoloSuVotanti calcolo) {
		return calcolo.calcola(listaVotanti);
	}

}
