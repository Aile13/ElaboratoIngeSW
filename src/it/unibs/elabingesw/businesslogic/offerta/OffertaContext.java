package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.repository.Manageable;
import it.unibs.elabingesw.businesslogic.offerta.state.ApertaState;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;
import it.unibs.elabingesw.businesslogic.utente.Utente;

import java.io.Serializable;
import java.util.Optional;

/**
 * Classe Offerta che rappresenta un'offerta generica.
 * <p>
 * Invariante di classe: assumo gli attributi immutabili,
 * dopo la creazione dell'oggetto.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public final class OffertaContext implements Manageable, Serializable, DomainTypeToRender {
    private final String nomeArticolo;
    private final Utente autore;
    private final Categoria categoriaDiAppartenenza;
    private final ListaCampiCompilati listaCampiCompilati;
    private OffertaState offertaState;

    /**
     * Costruttore di classe, accetta come parametri il nome dell'
     * articolo, l'autore dell'offerta, la lista dei campi compilati e
     * la categoria di appartenenza dell'articolo.
     * <p>
     * Precondizione: assumo ogni parametro non nullo e inizializzato correttamente.
     * Per nomeArticolo si intende che il parametro non corrisponda a una stringa vuota,
     * e che non sia coincidente a nessun nomeArticolo di nessun'altra offerta registrata
     * precedentemente nel sistema.
     * Per autore si intende che il parametro sia una istanza di un utente fruitore
     * registrato nel sistema.
     * Per listaCampiCompilati si intende una istanza dell'apposito tipo e che questa sia compilata
     * ad hoc rispetto campi nativi ricavati dalla gerarchia a cui appartiene la categoria foglia a
     * cui l'offerta è associata.
     * Per categoriaDiAppartenenza si intende una categoria foglia (terminale) appartenente a
     * una gerarchia registrata nel sistema a cui l'offerta è poi associata.
     *
     * @param nomeArticolo            nome dell'articolo.
     * @param autore                  utente fruitore autore dell'offerta
     * @param listaCampiCompilati     lista dei campi con associato eventuale valore compilato
     * @param categoriaDiAppartenenza categoria foglia a cui l'articolo appartiene
     */
    public OffertaContext(String nomeArticolo, Utente autore, ListaCampiCompilati listaCampiCompilati, Categoria categoriaDiAppartenenza) {
        this.nomeArticolo = nomeArticolo;
        this.autore = autore;
        this.categoriaDiAppartenenza = categoriaDiAppartenenza;
        this.listaCampiCompilati = listaCampiCompilati;
        this.offertaState = new ApertaState();
    }

    public void setOffertaState(OffertaState offertaState) {
        this.offertaState = offertaState;
    }

    public Utente getAutore() {
        return autore;
    }

    public ListaCampiCompilati getListaCampiCompilati() {
        return listaCampiCompilati;
    }

    /**
     * Metodo getter.
     *
     * @return la categoria di appartenenza dell'offerta
     */
    public Categoria getCategoriaDiAppartenenza() {
        return categoriaDiAppartenenza;
    }

    /**
     * Metodo getter.
     *
     * @return il nome dell'articolo
     */
    public String getNomeArticolo() {
        return nomeArticolo;
    }

    public OffertaState getOffertaState() {
        return offertaState;
    }

    /**
     * Metodo getter.
     * <p>
     * Precondizione: assumo che questo metodo venga
     * chiamato solo quando l'offerta è in stato di
     * offerta selezionata. E quindi si vuole conoscere
     * l'offerte accoppiata a essa.
     *
     * @return l'offerta accoppiata
     */
    public OffertaContext getOffertaAssociata() {
        return offertaState.getOffertaAssociata(this);
    }

    /**
     * Metodo getter.
     * <p>
     * Precondizione: assumo che il metodo in questione
     * venga invocato solo quando l'offerta è nello
     * stato di offerta in scambio.
     * Post condizione: quella del metodo chiamato.
     *
     * @return lo lista dei campi di un appuntamento
     */
    public ListaCampiCompilati getListaCampiAppuntamento() {
        return this.offertaState.getListaCampiAppuntamento(this);
    }

    /**
     * Metodo implementato dall'interfaccia Manageable
     * che verifica se due articoli hanno lo stesso nome o meno.
     * <p>
     * Precondizione: assumo parametro del metodo non nullo.
     *
     * @param nomeArticolo il nome dell'articolo
     * @return TRUE se i nomi sono uguali
     * FALSE se i nomi sono diversi
     */
    @Override
    public boolean isStessoNome(String nomeArticolo) {
        return this.nomeArticolo.equals(nomeArticolo);
    }

    /**
     * Metodo che controlla se un'offerta è aperta o meno.
     *
     * @return TRUE se l'offerta è aperta
     * FALSE se l'offerta non è aperta
     */
    public boolean isOffertaAperta() {
        return this.offertaState.isOffertaAperta(this);
    }

    /**
     * Metodo che controlla se due autori sono uguali
     * o meno.
     * <p>
     * Precondizione: assumo parametro del metodo non nullo.
     * Inoltre assumo che l'utente passato come parametro sia
     * un fruitore registrato nel sistema.
     *
     * @param autore l'oggetto di tipo Utente fruitore
     * @return TRUE se gli autori sono uguali
     * FALSE se gli autori sono diversi
     */
    public boolean isStessoAutore(Utente autore) {
        return this.autore.equals(autore);
    }

    /**
     * Metodo che ritira un'offerta trasformandone lo
     * stato in RITIRATA.
     */
    public void ritiraOfferta() {
        this.offertaState.ritiraOfferta(this);
    }

    /**
     * Metodo per la formattazione che converte un oggetto
     * nella relativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "Offerta{" + "nomeArticolo='" + nomeArticolo + '\'' + ", autore=" + autore + ", listaCampiCompilati=" + listaCampiCompilati + ", categoriaDiAppartenenza=" + categoriaDiAppartenenza.toStringRidotto() + ", statoOfferta=" + offertaState + '}';
    }

    /**
     * Metodo che controlla se un articolo appartiene
     * a una categoria passata per parametro.
     * <p>
     * Precondizione: assumo parametro del metodo non nullo.
     * Inoltre assumo che la categoria parametro sia una categoria foglia (terminale)
     * appartenente a una gerarchia registrata nel sistema.
     *
     * @param categoria l'oggetto di tipo Categoria
     * @return TRUE se l'articolo appartiene alla categoria in ingresso
     * FALSE se l'articolo non appartiene alla categoria in ingresso
     */
    public boolean appartieneA(Categoria categoria) {
        return this.categoriaDiAppartenenza.equals(categoria);
    }

    /**
     * Metodo che permette di creare un legame tra due diverse offerte
     * <p>
     * Precondizione: assumo parametro metodo non nullo e correttamente inizializzata.
     * Ovvero assumo che l'offerta parametro sia
     * una offerta attualmente aperta e registrata nel sistema, e che questa
     * sia diversa dall'offerta che chiama il metodo e che rispetto a questa abbia un autore associato diverso.
     * Inoltre assumo che l'offerta che chiama il metodo e l'offerta parametro siano
     * della stessa categoria foglia della medesima gerarchia.
     * Post condizione: abbiamo che l'offerta parametro e l'offerta
     * che ha chiamato il metodo sono ora legato tra loro.
     * E abbiamo che l'offerta che ha chiamato il metodo cambia di stato, diventando
     * offerta accoppiata. Mentre l'offerta parametro va nello stato di offerta selezionata.
     *
     * @param offertaContextDaBarattareB oggetto di tipo Offerta
     * @param infoDiScambio
     */
    // todo  Laaraj descrizione param mancante in doc. Bisogna aggiungerla.
    public void creaLegameEModificaStatiConOffertaEInfoScambio(OffertaContext offertaContextDaBarattareB, Optional<Scambio> infoDiScambio) {
        this.offertaState.creaLegameEModificaStatiConOffertaEInfoScambio(this, offertaContextDaBarattareB, infoDiScambio.orElse(null));
    }

    /**
     * Metodo che aggiorna lo stato di un'offerta.
     * <p>
     * Post condizione: quella del metodo chiamato.
     */
    public void aggiornaStatoOfferta() {
        this.offertaState.aggiornaStatoOfferta(this);
    }

    /**
     * Metodo che controlla se un'offerta è selezionata o meno.
     *
     * @return TRUE se l'offerta è selezionata
     * FALSE se l'offerta non è selezionata
     */
    public boolean isOffertaSelezionata() {
        return this.offertaState.isOffertaSelezionata(this);
    }

    /**
     * Metodo che permette di accettare una proposta
     * di scambio associata.
     * <p>
     * Precondizione: assumo che questo metodo sia chiamato
     * solo da offerte in stato di offerta selezionata.
     * E quindi si vuole accettare la proposta di scambio
     * che la offerta accoppiata gli ha suggerito.
     * Assumo inoltre che la lista campi appuntamento non sia
     * nulla, e sia adeguatamente compilata con i campi e loro valori
     * che identificano un valido appuntamento tra quelli che
     * le info di scambio possono ammettere.
     * Post condizione: quella del metodo chiamato.
     *
     * @param listaCampiAppuntamento la lista dei campi dell'appuntamento
     */
    public void accettaPropostaDiScambioAssociata(ListaCampiCompilati listaCampiAppuntamento) {
        this.offertaState.accettaPropostaDiScambioAssociata(this, listaCampiAppuntamento);
    }

    /**
     * Metodo che controlla se un'offerta è in scambio o meno.
     *
     * @return TRUE se l'offerta è in scambio
     * FALSE se l'offerta non è in scambio
     */
    public boolean isOffertaInScambio() {
        return this.offertaState.isOffertaInScambio(this);
    }

    /**
     * Metodo che permette a una parte di accettare
     * l'appuntamento per lo scambio di un'offerta
     * di un'altra parte.
     * <p>
     * Precondizione: assumo che questo metodo venga
     * chiamato solo dalle offerte in stato di
     * offerta in scambio, e che a questa offerta chiamante
     * sia associata una lista campi appuntamento
     * non nulla e correttamente inizializzata.
     * Perché si vuole così procedere allo scambio dei beni
     * con le modalità fissate in quella lista di campi.
     * Post condizione: quella del metodo chiamato.
     */
    public void accettaAppuntamento() {
        this.offertaState.accettaAppuntamento(this);
    }

    /**
     * Metodo che propone un altro appuntamento,
     * con le specifiche di quest'ultimo inserite
     * in una lista che viene passata per parametro.
     * <p>
     * Precondizione: assumo che questo metodo sia
     * chiamato solo da offerte in stato di offerta
     * in scambio. E che questa offerta sia già dotata
     * di una lista campi compilati per un appuntamento non
     * nulla e valida. Che in questo caso si vuole rifiutare.
     * Assumo inoltre che il parametro del metodo sia un'altra
     * lista campi compilati per un nuovo appuntamento non
     * nulla e valida e diversa dall'appuntamento che l'offerta
     * ha associato e sta rifiutando.
     * Post condizione: quella del metodo chiamato.
     *
     * @param listaCampiAppuntamento la lista dei campi del nuovo appuntamento
     */
    public void proponiAltroAppuntamento(ListaCampiCompilati listaCampiAppuntamento) {
        this.offertaState.proponiAltroAppuntamento(this, listaCampiAppuntamento);
    }

    /**
     * Metodo che controlla se un'offerta è chiusa o meno.
     *
     * @return TRUE se l'offerta è chiusa
     * FALSE se l'offerta non è chiusa
     */
    public boolean isOffertaChiusa() {
        return this.offertaState.isOffertaChiusa(this);
    }
}
