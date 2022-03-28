package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.eliapitozzi.mylib.InputDati;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elia
 */
class IntervalloOrariService {
    public static List<IntervalloOrario> chiediIntervalliOrari() {
        List<IntervalloOrario> listaIntervalliOrari = new ArrayList<>();

        System.out.println("Inserisci un intervallo orario");
        listaIntervalliOrari.add(chiediIntervalloOrario());

        while (chiediSeAggiungereAltroIntervalloOrario()) {
            System.out.println("Inserisci altro intervallo orario");
            var nuovoIntervalloOrario = chiediIntervalloOrario();
            boolean incompatibile = listaIntervalliOrari.stream()
                    .anyMatch(nuovoIntervalloOrario::intersecaAltroIntervalloOrario);

            while (incompatibile) {
                System.out.println("Errore: ultimo intervallo inserito interseca altri intervalli già inseriti. Riprovare");
                nuovoIntervalloOrario = chiediIntervalloOrario();
                incompatibile = listaIntervalliOrari.stream()
                        .anyMatch(nuovoIntervalloOrario::intersecaAltroIntervalloOrario);
            }

            listaIntervalliOrari.add(nuovoIntervalloOrario);
        }

        return listaIntervalliOrari;
    }

    private static boolean chiediSeAggiungereAltroIntervalloOrario() {
        return InputDati.yesOrNo("Vuoi aggiungere un altro intervallo orario?");
    }

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

    private static LocalTime chiediOrario() {
        int ora = chiediOre();
        int minuti = chiediMinuti();
        return LocalTime.of(ora, minuti);
    }

    private static int chiediOre() {
        return InputDati.leggiIntero("Inserisci solo l'ora (24h): ", 0, 23);
    }

    private static int chiediMinuti() {
        int minuti = InputDati.leggiIntero("Inserisci solo i minuti(0 o 30): ");
        while (minuti != 0 && minuti != 30) {
            System.out.println("Attenzione minuto inserito non valido: riprovare.");
            minuti = InputDati.leggiIntero("Inserisci solo i minuti (0 o 30): ");
        }
        return minuti;
    }

}
