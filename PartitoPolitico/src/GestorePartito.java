import java.util.ArrayList;

import model.*;
import controller.*;
public class GestorePartito {
	private String nomePartito;
	
	private ArrayList<Area> listaAree;
	private ArrayList<CartaIscritto> listaVotanti;
	private ArrayList<Iscritto> listaIscritti;
	private ArrayList<Votazione> listaVotazioni;
	
	public GestorePartito(String nomePartito) {
		this.nomePartito = nomePartito;
	}
	public int calcoloNumeroVotzioniSuArea(Area a) {
		CalcoloSuVotanti c = new CalcolaNumeroVotazioni(a);
		return a.calcolaNumeroVotazioni(c);
	}
}
