import model.*;
import controller.*;
public class Test {

	public static void main(String[] args) {
		GestorePartito gestore = new GestorePartito("Partito politico");
		
		Area radice = new AreaRadice("Radice","Lazio","Roma","municipio1","quartiere1","cap1");
		
		
		gestore.calcoloNumeroVotzioniSuArea(radice);

	}

}
