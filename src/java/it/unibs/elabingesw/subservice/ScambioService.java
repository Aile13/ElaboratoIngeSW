package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.gestione.GestoreScambio;
import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;
import it.unibs.eliapitozzi.mylib.InputDati;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe ScambioService che gestisce le varie operazioni
 * che si effettuano su uno scambio di un articolo.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class ScambioService {
    private final GestoreScambio gestoreScambio;
    
    /**
     * Costruttore di classe, accetta come parametro un oggetto
     * GestoreScambio.
     *
     * @param gestoreScambio
     * @see GestoreScambio
     */
    public ScambioService(GestoreScambio gestoreScambio) {
        this.gestoreScambio = gestoreScambio;
    }
    
    /**
     * Metodo che chiede all'utente e imposta le varie informazioni
     * relative a uno scambio.
     */
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
    
    /**
     * Metodo che chiede la scadenza, ossia il numero massimo di
     * giorni entro cui un fruitore può accettare una proposta di
     * scambio.
     *
     * @return la scadenza in giorni
     */
    private int chiediScadenza() {
        System.out.println("Settaggio parametro scadenza");
        return InputDati.leggiInteroPositivo(
                "Inserisci il massimo numero di giorni consentito per accettazione proposta di scambio: "
        );
    }

    /**
     * Metodo che chiede la scadenza, ossia il numero massimo di
     * giorni entro cui un fruitore può accettare una proposta di
     * scambio.
     *
     * @return la lista degli intervalli orari
     */
    private List<IntervalloOrario> chiediIntervalliOrari() {
        System.out.println("Settaggio parametro intervalli orari");
        return IntervalloOrariService.chiediIntervalliOrari();
    }
    
    /**
     * Metodo che chiede i giorni della settimana in cui è possibile
     * effettuare scambi.
     *
     * @return la lista dei giorni
     */
    private List<DayOfWeek> chiediGiorniDiScambio() {
        System.out.println("Settaggio parametro giorni di scambio");
        return GiorniDiSettimanaService.chiediGiorniDiSettimana();
    }
    
    /**
     * Metodo che chiede i luoghi in cui si possono effettuare gli
     * scambi.
     *
     * @return la lista dei luoghi
     */
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
    
    /**
     * Metodo che chiede il nome della piazza.
     *
     * @return il nome della piazza
     */
    private String chiediNomePiazza() {
        System.out.println("Settaggio paramentro piazza");
        return InputDati.leggiStringaNonVuota("Inserisci nome della città: ");
    }
    
    /**
     * Metodo che visualizza le informazioni di un determinato
     * scambio.
     */
    public void visualizzaInfoDiScambioFormaEstesa() {
        System.out.println("Info parametri di scambio:");
        if (this.gestoreScambio.isInfoScambioDaConfigurare()) {
            System.out.println("\tInfo ancora da configurare");
        } else {
            System.out.println(this.gestoreScambio.getInfoDiScambio().get());
        }

    }
    
    /**
     * Metodo che visualizza le informazioni di un determinato
     * scambio in forma ridotta.
     */
    public void visualizzaInfoDiScambioFormaRidotta() {
        System.out.println("Info parametri di scambio:");
        if (this.gestoreScambio.isInfoScambioDaConfigurare()) {
            System.out.println("\tInfo ancora da configurare");
        } else {
            System.out.println(this.gestoreScambio.getInfoDiScambio().get().toStringFormaRidotta());
        }

    }
}
