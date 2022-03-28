package it.unibs.elabingesw.subservice;

import it.unibs.eliapitozzi.mylib.InputDati;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elia
 */
public class GiorniDiSettimanaService {
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
