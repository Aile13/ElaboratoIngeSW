package it.unibs.elabingesw.service;

import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.eliapitozzi.mylib.InputDati;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elia
 */
public class CampoService {

    public static List<Campo> chiediListaDiCampi() {
        List<Campo> campi = new ArrayList<>();
        if (InputDati.yesOrNo("Inserire un campo?")) {
            campi.add(chiediNuovoCampo());

            while (InputDati.yesOrNo("Inserire un nuovo campo?")) {
                campi.add(chiediNuovoCampo());
            }
        }
        return campi;
    }

    private static Campo chiediNuovoCampo() {
        String nome = InputDati.leggiStringaNonVuota("Inserisci il nome del campo: ");
        boolean obbligatorio = InputDati.yesOrNo("È obbligatorio?");

        return new Campo(nome, obbligatorio);
    }

}
