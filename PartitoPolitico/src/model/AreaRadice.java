package model;
import java.util.ArrayList;

import controller.CalcoloSuVotanti;

public class AreaRadice extends Area{
	
	private ArrayList<Area> listaAreeFiglie = new ArrayList<>();

	public AreaRadice(String nomeArea, String regione, String citta, String municipio, String quartiere, String cap) {
		super(nomeArea, regione, citta, municipio, quartiere, cap);
	}

	@Override
	public void aggiungiAreaFiglio(Area figlio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Area> getAreeFiglie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Area getPadre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calcolaNumeroVotazioni(CalcoloSuVotanti calcolo) {
		int count = calcolo.calcola(listaVotanti);
		
		for(Area a : listaAreeFiglie) 
			count+=a.calcolaNumeroVotazioni(calcolo);
		
		return count;
	}

}
