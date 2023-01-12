package it.unibs.elabingesw.controller.subservice;

import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.elabingesw.view.IntervalloOrariServiceView;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe IntervalloOrariService che gestisce le varie operazioni
 * che si effettuano su uno o più intervalli orari.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
class IntervalloOrariServiceController {

    private final static IntervalloOrariServiceView view = new IntervalloOrariServiceView();

    /**
     * Metodo che chiede all'utente gli intervalli orari in cui
     * è possibile effettuare gli scambi: questi intervalli orari
     * verranno poi inseriti in una lista apposita.
     *
     * @return la lista degli intervalli orari
     */
    public static List<IntervalloOrario> chiediIntervalliOrari() {
        List<IntervalloOrario> listaIntervalliOrari = new LinkedList<>();

        view.visualizzaMessaggio("Inserisci un intervallo orario");
        listaIntervalliOrari.add(chiediIntervalloOrario());

        while (chiediSeAggiungereAltroIntervalloOrario()) {
            view.visualizzaMessaggio("Inserisci altro intervallo orario");
            var nuovoIntervalloOrario = chiediIntervalloOrario();
            boolean incompatibile = listaIntervalliOrari.stream().anyMatch(nuovoIntervalloOrario::intersecaAltroIntervalloOrario);

            while (incompatibile) {
                view.visualizzaMessaggio("Errore: ultimo intervallo inserito interseca altri intervalli già inseriti. Riprovare");
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
        return view.chiediSeAggiungereAltroIntervalloOrario();
    }

    /**
     * Metodo che chiede all'utente d'inserire un singolo
     * intervallo orario.
     *
     * @return l'intervallo orario
     */
    private static IntervalloOrario chiediIntervalloOrario() {
        view.visualizzaMessaggio("Inserimento orario iniziale");
        LocalTime iniziale = chiediOrario();

        view.visualizzaMessaggio("Inserimento orario finale");
        LocalTime finale = chiediOrario();

        while (!finale.isAfter(iniziale)) {
            view.visualizzaMessaggio("Errore: orario finale precede o è uguale a orario iniziale. Riprovare.");
            view.visualizzaMessaggio("Reinserimento di orario finale");
            finale = chiediOrario();
        }

        return new IntervalloOrario(iniziale, finale);
    }

    /**
     * Metodo che chiede all'utente d'inserire un singolo
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
     * Metodo che chiede all'utente d'inserire le ore
     * di un determinato orario
     *
     * @return le ore
     */
    private static int chiediOre() {
        return view.chiediOreDiOrario();
    }

    /**
     * Metodo che chiede all'utente d'inserire i minuti
     * di un determinato orario
     *
     * @return i minuti
     */
    private static int chiediMinuti() {
        int minuti = view.chiediMinutiDiOrario();
        while (minuti != 0 && minuti != 30) {
            view.visualizzaMessaggio("Attenzione minuto inserito non valido: riprovare.");
            minuti = view.chiediMinutiDiOrario();
        }
        return minuti;
    }

}
