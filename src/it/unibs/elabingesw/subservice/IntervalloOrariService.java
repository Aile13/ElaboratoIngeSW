package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.eliapitozzi.mylib.InputDati;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe IntervalloOrariService che gestisce le varie operazioni
 * che si effettuano su uno o più intervalli orari.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
class IntervalloOrariService {

    /**
     * Metodo che chiede all'utente gli intervalli orari in cui
     * è possibile effettuare gli scambi: questi intervalli orari
     * verranno poi inseriti in una lista apposita.
     *
     * @return la lista degli intervalli orari
     */
    public static List<IntervalloOrario> chiediIntervalliOrari() {
        List<IntervalloOrario> listaIntervalliOrari = new LinkedList<>();

        System.out.println("Inserisci un intervallo orario");
        listaIntervalliOrari.add(chiediIntervalloOrario());

        while (chiediSeAggiungereAltroIntervalloOrario()) {
            System.out.println("Inserisci altro intervallo orario");
            var nuovoIntervalloOrario = chiediIntervalloOrario();
            boolean incompatibile = listaIntervalliOrari.stream().anyMatch(nuovoIntervalloOrario::intersecaAltroIntervalloOrario);

            while (incompatibile) {
                System.out.println("Errore: ultimo intervallo inserito interseca altri intervalli già inseriti. Riprovare");
                nuovoIntervalloOrario = chiediIntervalloOrario();
                incompatibile = listaIntervalliOrari.stream().anyMatch(nuovoIntervalloOrario::intersecaAltroIntervalloOrario);
            }

            listaIntervalliOrari.add(nuovoIntervalloOrario);
        }

        return listaIntervalliOrari;
    }

    /**
     * Metodo per chiedere all'utente se vuole aggiungere un altro
     * intervallo orario.
     *
     * @return TRUE se si vuole aggiungere un intervallo orario
     * FALSE se non si vuole aggiungere un intervallo orario
     */
    private static boolean chiediSeAggiungereAltroIntervalloOrario() {
        return InputDati.yesOrNo("Vuoi aggiungere un altro intervallo orario?");
    }

    /**
     * Metodo che chiede all'utente di inserire un singolo
     * intervallo orario.
     *
     * @return l'intervallo orario
     */
    private static IntervalloOrario chiediIntervalloOrario() {
        System.out.println("Inserimento orario iniziale");
        LocalTime iniziale = chiediOrario();

        System.out.println("Inserimento orario finale");
        LocalTime finale = chiediOrario();

        while (!finale.isAfter(iniziale)) {
            System.out.println("Errore: orario finale precede o è uguale a orario iniziale. Riprovare.");
            System.out.println("Reinserimento di orario finale");
            finale = chiediOrario();
        }

        return new IntervalloOrario(iniziale, finale);
    }

    /**
     * Metodo che chiede all'utente di inserire un singolo
     * orario.
     *
     * @return l'orario
     */
    private static LocalTime chiediOrario() {
        int ora = chiediOre();
        int minuti = chiediMinuti();
        return LocalTime.of(ora, minuti);
    }

    /**
     * Metodo che chiede all'utente di inserire le ore
     * di un determinato orario
     *
     * @return le ore
     */
    private static int chiediOre() {
        return InputDati.leggiIntero("Inserisci solo l'ora (24h): ", 0, 23);
    }

    /**
     * Metodo che chiede all'utente di inserire i minuti
     * di un determinato orario
     *
     * @return i minuti
     */
    private static int chiediMinuti() {
        int minuti = InputDati.leggiIntero("Inserisci solo i minuti(0 o 30): ");
        while (minuti != 0 && minuti != 30) {
            System.out.println("Attenzione minuto inserito non valido: riprovare.");
            minuti = InputDati.leggiIntero("Inserisci solo i minuti (0 o 30): ");
        }
        return minuti;
    }

}
