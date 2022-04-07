package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.eliapitozzi.mylib.InputDati;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe CampoService contenente due metodi statici appositi
 * per gestire le richieste dell'utente di aggiungere nuovi
 * campi alle categorie.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
class CampoService {

    /**
     * Metodo statico che ritorna la lista dei campi che
     * l'utente inserisce nell'applicativo.
     *
     * @return la lista dei campi inseriti dall'utente
     */
    public static List<Campo> chiediListaDiCampiPerCategoriaRadice() {
        List<Campo> campi = new ArrayList<>();
        if (InputDati.yesOrNo("Inserire un campo?")) {
            var campo = chiediNuovoCampo();

            while (campo.isCampoDiDefault()) {
                System.out.println("Attenzione: campo già usato, reinserirne un altro.");
                campo = chiediNuovoCampo();
            }
            campi.add(campo);

            while (InputDati.yesOrNo("Inserire un nuovo campo?")) {
                campo = chiediNuovoCampo();
                while (campo.isCampoInListaByNome(campi) || campo.isCampoDiDefault()) {
                    System.out.println("Attenzione: campo già usato, reinserirne un altro.");
                    campo = chiediNuovoCampo();
                }
                campi.add(campo);
            }
        }
        return campi;
    }


    public static List<Campo> chiediListaDiCampiPerCategoriaFiglio(GerarchiaDiCategorie gerarchia) {
        List<Campo> campi = new ArrayList<>();
        if (InputDati.yesOrNo("Inserire un campo?")) {
            var campo = chiediNuovoCampo();
            while (gerarchia.isCampoGiaPreso(campo)) {
                System.out.println("Attenzione: campo già usato, reinserirne un altro.");
                campo = chiediNuovoCampo();
            }
            campi.add(campo);

            while (InputDati.yesOrNo("Inserire un nuovo campo?")) {
                campo = chiediNuovoCampo();
                while (gerarchia.isCampoGiaPreso(campo) ||
                        campo.isCampoInListaByNome(campi)) {
                    System.out.println("Attenzione: campo già usato, reinserirne un altro.");
                    campo = chiediNuovoCampo();
                }
                campi.add(campo);
            }
        }
        return campi;
    }

    /**
     * Metodo statico che chiede all'utente di inserire
     * un nuovo campo. Ritorna il nuovo campo.
     * <p>
     * Devono essere inseriti il nome del nuovo campo e
     * se quest'ultimo è obbligatorio oppure no.
     *
     * @return un nuovo campo
     * @see Campo
     */
    private static Campo chiediNuovoCampo() {
        String nome = InputDati.leggiStringaNonVuota("Inserisci il nome del campo: ");
        boolean obbligatorio = InputDati.yesOrNo("È obbligatorio?");

        return new Campo(nome, obbligatorio);
    }
}
