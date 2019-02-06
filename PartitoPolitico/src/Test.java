import model.*;
import controller.*;
import view.*;

public class Test {

	public static void main(String[] args) {
		GestorePartito gestore = new GestorePartito("Partito politico");
		
		Area radice = new AreaRadice("Radice","Lazio","Roma","municipio1","quartiere1","cap1");
		Area areaInterna1 = new AreaInterna("area1","Lazio","Roma","municipio1","quartiere1","cap1",radice);
		Area areaInterna2 = new AreaInterna("area2","Lazio","Roma","municipio1","quartiere1","cap1",radice);
		Area areaFoglia = new AreaFoglia("foglia","Lazio","Roma","municipio1","quartiere1","cap1",areaInterna2);
		
		areaFoglia.aggiungiVotante(new CartaIscritto(1,new Iscritto("tizio","qua","1234","@123")));
		areaFoglia.aggiungiVotante(new CartaIscritto(2,new Iscritto("caio","qua","1234","@123")));
		System.out.println(areaFoglia.getListaVotanti().size());
		int num = gestore.calcoloNumeroVotzioniSuArea(radice);
		new Stampa().stampa(""+num);

	}

}
