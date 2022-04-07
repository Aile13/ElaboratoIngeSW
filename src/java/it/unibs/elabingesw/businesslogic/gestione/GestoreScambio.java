package it.unibs.elabingesw.businesslogic.gestione;

import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.util.Optional;

/**
 * Classe GestoreScambio, figlia della classe GestoreGenerico.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class GestoreScambio extends GestoreGenerico<Scambio> {
    private static final String FILE_NAME = "InfoScambio";

    /**
     * Costruttore di classe.
     */
    public GestoreScambio() {
        super(FILE_NAME);
    }

    /**
     * Metodo che controlla se la lista delle informazioni 
     * relativa a uno scambio è vuota o meno.
     *
     * @return TRUE se le info di uno scambio sono da configurare
     *         FALSE se le info di uno scambio non sono da configurare
     */
    public boolean isInfoScambioDaConfigurare() {
        return this.getListaElementi().isEmpty();
    }

    /**
     * Metodo che permette di salvare le informazioni sugli
     * scambi inseriti.
     */
    public void salvaInfoScambio() {
        salvaDati();
    }

    /**
     * Metodo che imposta le informazioni di uno scambio
     * passato per parametro.
     *
     * @param scambio l'oggetto di tipo Scambio
     */
    public void impostaInfoDiScambio(Scambio scambio) {
        this.inserisciElemento(scambio);
    }

    /**
     * Metodo getter.
     *
     * @return le informazioni di uno scambio
     */
    public Optional<Scambio> getInfoDiScambio() {
        return this.getListaElementi().stream().findFirst();
    }
}
