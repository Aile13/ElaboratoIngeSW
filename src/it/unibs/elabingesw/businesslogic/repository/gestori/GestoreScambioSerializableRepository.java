package it.unibs.elabingesw.businesslogic.repository.gestori;

import it.unibs.elabingesw.businesslogic.repository.ScambioRepository;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;

import java.util.Optional;

/**
 * Classe GestoreScambio, sotto-classe della classe GestoreGenerico.
 * <p>
 * Invariante di classe: lo stesso della super-classe.
 * Inoltre il tipo generico T è settato immutabilmente con
 * il tipo Scambio.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public final class GestoreScambioSerializableRepository extends GestoreGenerico<Scambio> implements ScambioRepository {
    private static final String FILE_NAME = "InfoScambio";

    /**
     * Costruttore di classe.
     * <p>
     * Post condizione: Quella del costruttore della super-classe.
     */
    public GestoreScambioSerializableRepository() {
        super(FILE_NAME);
    }

    /**
     * Metodo che controlla se la lista delle informazioni
     * relativa a uno scambio è vuota o meno.
     * <p>
     * Post condizione: uso del GestoreGenerico in modo degenere:
     * ovvero salvando al più un solo elemento in lista. L'elemento
     * in lista qui è, se presente, un'istanza delle info di scambio.
     * Infine la post condizione qui è quella del metodo chiamato.
     *
     * @return TRUE se le info di uno scambio sono da configurare
     * FALSE se le info di uno scambio non sono da configurare
     */
    @Override
    public boolean isInfoScambioDaConfigurare() {
        return this.getListaElementi().isEmpty();
    }

    /**
     * Metodo che permette di salvare le informazioni sugli
     * scambi inserite.
     * <p>
     * Precondizione: quella del metodo chiamato.
     * Post condizione: quella del metodo chiamato.
     */
    @Override
    public void salvaInfoScambio() {
        salvaDati();
    }

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
    @Override
    public void impostaInfoDiScambio(Scambio scambio) {
        this.inserisciElemento(scambio);
    }

    /**
     * Metodo getter.
     * <p>
     * Precondizioni: le info di scambio sono già state configurate.
     * Post condizioni: l'Optional ritornato è vuoto se
     * le info di scambio non sono state ancora impostate.
     * Altrimenti esso è configurato con le info di
     * scambio impostate.
     *
     * @return le informazioni di scambio
     */
    @Override
    public Optional<Scambio> getInfoDiScambio() {
        return this.getListaElementi().stream().findFirst();
    }
}
