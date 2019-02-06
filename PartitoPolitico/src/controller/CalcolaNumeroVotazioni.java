package controller;

import java.util.ArrayList;

import model.Area;
import model.CartaIscritto;

public class CalcolaNumeroVotazioni implements CalcoloSuVotanti{
	private Area radice;
	public CalcolaNumeroVotazioni(Area radice) {
		this.radice=radice;
	}
	@Override
	public int calcola(ArrayList<CartaIscritto> votanti) {
		return votanti.size();
	}

}
