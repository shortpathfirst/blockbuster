package model;
import java.util.ArrayList;

import controller.CalcoloSuVotanti;

public class AreaInterna extends Area {

	private ArrayList<Area> listaAreeFiglie = new ArrayList<>();
	private Area padre;
	
	public AreaInterna(String nomeArea, String regione, String citta, String municipio, String quartiere, String cap,Area padre) {
		super(nomeArea, regione, citta, municipio, quartiere, cap);
		
		this.padre = padre;
		this.padre.aggiungiAreaFiglio(this);
	}

	@Override
	public void aggiungiAreaFiglio(Area figlio) {
		this.listaAreeFiglie.add(figlio);
	}

	@Override
	public ArrayList<Area> getAreeFiglie() {
		return this.listaAreeFiglie;
	}

	@Override
	public Area getPadre() {
		return this.padre;
	}

	@Override
	public int calcolaNumeroVotazioni(CalcoloSuVotanti calcolo) {
		int count = 0;
		
		for(Area a : listaAreeFiglie) 
			count+=a.calcolaNumeroVotazioni(calcolo);
		
		return count;
	}


	
}
