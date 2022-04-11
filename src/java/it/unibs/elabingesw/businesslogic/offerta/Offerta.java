package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.gestione.Manageable;
import it.unibs.elabingesw.businesslogic.utente.Utente;

import java.io.Serializable;

/**
 * Classe Offerta che rappresenta un'offerta generica.
 * <p>
 * Invariante di classe: assumo tutti gli attributi immutabili,
 * tranne l'attributo statoOfferta. Il quale è comunque sempre
 * dotato in ogni momento di una specifico stato secondo
 * la logica evolutiva dell'offerta, sin dalla creazione dell'oggetto.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class Offerta implements Manageable, Serializable {
    private final String nomeArticolo;
    private final Utente autore;
    private final Categoria categoriaDiAppartenenza;
    private final ListaCampiCompilati listaCampiCompilati;
    private StatoOfferta statoOfferta;

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
    public Offerta(String nomeArticolo, Utente autore, ListaCampiCompilati listaCampiCompilati, Categoria categoriaDiAppartenenza) {
        this.nomeArticolo = nomeArticolo;
        this.autore = autore;
        this.categoriaDiAppartenenza = categoriaDiAppartenenza;
        this.listaCampiCompilati = listaCampiCompilati;
        this.statoOfferta = StatoOfferta.APERTA;
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
        return this.statoOfferta == StatoOfferta.APERTA;
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
        this.statoOfferta = StatoOfferta.RITIRATA;
    }

    /**
     * Metodo per la formattazione che converte un oggetto
     * nella relativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "Offerta{" +
                "nomeArticolo='" + nomeArticolo + '\'' +
                ", autore=" + autore +
                ", listaCampiCompilati=" + listaCampiCompilati +
                ", categoriaDiAppartenenza=" + categoriaDiAppartenenza.toStringRidotto() +
                ", statoOfferta=" + statoOfferta +
                '}';
    }

    /**
     * Metodo getter.
     *
     * @return il nome dell'articolo
     */
    public String getNomeArticolo() {
        return nomeArticolo;
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
}
