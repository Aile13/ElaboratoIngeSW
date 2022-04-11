package it.unibs.elabingesw.subservice;

import it.unibs.eliapitozzi.mylib.InputDati;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe GiorniDiSettimanaService che gestisce la
 * richiesta dei giorni della settimana.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class GiorniDiSettimanaService {
    
    /**
     * Metodo che chiede all'utente di inserire la lista
     * dei giorni in cui è possibile effettuare scambi.
     *
     * @return la lista dei giorni
     */
    public static List<DayOfWeek> chiediGiorniDiSettimana() {
        List<DayOfWeek> listaGiorni = new ArrayList<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            if (InputDati.yesOrNo("Vuoi avere " + day + " tra i giorni di scambio?")) {
                listaGiorni.add(day);
            }
        }

        while (listaGiorni.isEmpty()) {
            System.out.println("Errore: nessun giorno della settimana selezionato, riprovare.");
            for (DayOfWeek day : DayOfWeek.values()) {
                if (InputDati.yesOrNo("Vuoi avere " + day + " tra i giorni di scambio?")) {
                    listaGiorni.add(day);
                }
            }
        }

        return listaGiorni;
    }
}
