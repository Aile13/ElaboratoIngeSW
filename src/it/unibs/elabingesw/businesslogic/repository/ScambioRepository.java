package it.unibs.elabingesw.businesslogic.repository;

import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.util.Optional;

/**
 * Interfaccia ScambioRepository con metodi appositi
 * per gestire operazioni sugli scambi.
 * 
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public interface ScambioRepository {
    
    /**
     * Metodo che controlla se la lista delle informazioni
     * relativa a uno scambio è vuota o meno.
     *
     * @return TRUE se le info di uno scambio sono da configurare
     * FALSE se le info di uno scambio non sono da configurare
     */
    boolean isInfoScambioDaConfigurare();

    /**
     * Metodo che permette di salvare le informazioni sugli
     * scambi inserite.
     */
    void salvaInfoScambio();

    /**
     * Metodo che imposta le informazioni di uno scambio
     * passato per parametro.
     * <p>
     * Precondizioni: assumo parametro non nullo e
     * correttamente inizializzato.
     * Inoltre assumo che il metodo in questione venga invocato solo
     * quando le info di scambio non sono già state impostate.
     * Post condizione: Quella del metodo chiamato.
     *
     * @param scambio l'oggetto di tipo Scambio
     */
    void impostaInfoDiScambio(Scambio scambio);

    /**
     * Metodo getter.
     *
     * @return le informazioni di scambio
     */
    Optional<Scambio> getInfoDiScambio();
}
