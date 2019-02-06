package model;

public class Votazione {
	private CartaIscritto carta;
	private Area areaVotazione;
	public Votazione(CartaIscritto carta, Area areaVotazione) {
		super();
		this.carta = carta;
		this.areaVotazione = areaVotazione;
	}
	public CartaIscritto getCarta() {
		return carta;
	}
	public Area getAreaVotazione() {
		return areaVotazione;
	}
	
}
