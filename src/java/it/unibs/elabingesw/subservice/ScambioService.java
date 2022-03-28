package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.gestione.GestoreScambio;
import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;
import it.unibs.eliapitozzi.mylib.InputDati;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elia
 */
public class ScambioService {
    private final GestoreScambio gestoreScambio;

    public ScambioService(GestoreScambio gestoreScambio) {
        this.gestoreScambio = gestoreScambio;
    }

    public void impostaInfoScambio() {
        if (this.gestoreScambio.isInfoScambioDaConfigurare()) {
            System.out.println("Procedura settaggio info di scambio avviata");
            var piazza = chiediNomePiazza();
            var listaLuoghi = chiediLuoghiDiScambio();
            var listaGiorni = chiediGiorniDiScambio();
            var intervalliOrari = chiediIntervalliOrari();
            var scadenza = chiediScadenza();

            Scambio scambio = new Scambio(piazza, listaLuoghi, listaGiorni, intervalliOrari, scadenza);

            gestoreScambio.impostaInfoDiScambio(scambio);

        } else {
            System.out.println("\tAttenzione: info di scambio già impostate.");
        }
    }

    private int chiediScadenza() {
        System.out.println("Settaggio parametro scadenza");
        return InputDati.leggiInteroPositivo(
                "Inserisci il massimo numero di giorni consentito per accettazione proposta di scambio: "
        );
    }

    private List<IntervalloOrario> chiediIntervalliOrari() {
        System.out.println("Settaggio parametro intervalli orari");
        return IntervalloOrariService.chiediIntervalliOrari();
    }

    private List<DayOfWeek> chiediGiorniDiScambio() {
        System.out.println("Settaggio parametro giorni di scambio");
        return GiorniDiSettimanaService.chiediGiorniDiSettimana();
    }

    private List<String> chiediLuoghiDiScambio() {
        System.out.println("Settaggio parametro luoghi di scambio");
        List<String> listaLuoghi = new ArrayList<>();
        String nuovoLuogo = InputDati.leggiStringaNonVuota("Inserisci un luogo dove fare gli scambi: ");
        listaLuoghi.add(nuovoLuogo);
        while (InputDati.yesOrNo("Vuoi inserire un altro luogo?")) {
            nuovoLuogo = InputDati.leggiStringaNonVuota("Inserisci un altro luogo dove fare gli scambi: ");
            while (listaLuoghi.contains(nuovoLuogo)) {
                System.out.println("Errore: luogo inserito già presente, inserirne un altro.");
                nuovoLuogo = InputDati.leggiStringaNonVuota("Inserisci un altro luogo dove fare gli scambi: ");
            }
            listaLuoghi.add(nuovoLuogo);
        }
        return listaLuoghi;
    }

    private String chiediNomePiazza() {
        System.out.println("Settaggio paramentro piazza");
        return InputDati.leggiStringaNonVuota("Inserisci nome della città: ");
    }

    public void visualizzaInfoDiScambioFormaEstesa() {
        System.out.println("Info parametri di scambio:");
        if (this.gestoreScambio.isInfoScambioDaConfigurare()) {
            System.out.println("\tInfo ancora da configurare");
        } else {
            System.out.println(this.gestoreScambio.getInfoDiScambio().get());
        }

    }

    public void visualizzaInfoDiScambioFormaRidotta() {
        System.out.println("Info parametri di scambio:");
        if (this.gestoreScambio.isInfoScambioDaConfigurare()) {
            System.out.println("\tInfo ancora da configurare");
        } else {
            System.out.println(this.gestoreScambio.getInfoDiScambio().get().toStringFormaRidotta());
        }

    }
}
