package it.unibs.elabingesw.businesslogic.gestione;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe GestoreGenerico che verrà ereditata dalle classi
 * GestoreUtenti e GestoreGerarchie.
 * <p>
 * Invariante di classe: assumo gli attributi immutabili,
 * dopo la creazione dell'oggetto.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
abstract class GestoreGenerico<T extends Manageable & Serializable> {
    private final String pathRepository;
    private final String dataDir = "./Dati/";
    private final List<T> listaElementi;

    /**
     * Costruttore di classe che accetta come parametro il
     * nome di un file.
     * Precondizione: assumo parametro costruttore non nullo.
     * Inoltre assumo parametro non uguale a stringa vuota.
     * Post condizione: costruisco, in funzione del parametro,
     * il percorso su cui operare la lettura del file
     * dove eventualmente i dati sono memorizzati.
     * Quindi a seconda del contenuto del file e delle sua presenza
     * aggiungo o meno gli elementi ottenuti dalla lettura.
     *
     * @param fileName nome del file da leggere, senza estensione.
     */
    public GestoreGenerico(String fileName) {
        this.pathRepository = dataDir + fileName + ".dat";
        inizializzaDirDati();
        this.listaElementi = new ArrayList<>();
        caricaElementi();
    }

    /**
     * Metodo che crea una directory se questa non è
     * già presente, e non lancia eccezione se questa è
     * già presente.
     * <p>
     * Post condizione: directory dati ora sicuramente presente.
     */
    private void inizializzaDirDati() {
        try {
            Files.createDirectories(Path.of(this.dataDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che salva i dati su file.
     */
    public void salvaDati() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(pathRepository))) {
            output.writeObject(this.listaElementi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che carica, dal file, tutti gli elementi in lista.
     * <p>
     * Post condizione: se il file esiste allora lo si legge e
     * si caricano in lista gli elementi letti in esso. Se il file non è
     * presente allora non si opera alcun caricamento.
     */
    protected void caricaElementi() {
        if (new File(pathRepository).exists()) {
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(pathRepository))) {
                List<T> listFromFile = (List<T>) input.readObject();
                if (listFromFile != null) {
                    this.listaElementi.addAll(listFromFile);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo che controlla se un elemento è presente
     * nella lista o meno, dell'elemento è fornito solo
     * il suo nome associato.
     * <p>
     * Precondizione: assumo parametro non nullo
     * e diverso da stringa vuota.
     *
     * @param nome il nome dell'elemento
     * @return TRUE se l'elemento è presente in lista
     * FALSE se l'elemento non è presente in lista
     */
    public boolean isElementoInListaByNome(String nome) {
        return trovaElementoConNome(nome).isPresent();
    }

    /**
     * Metodo getter.
     *
     * @return la lista degli elementi
     */
    public List<T> getListaElementi() {
        return listaElementi;
    }

    /**
     * Metodo che permette di cercare un elemento
     * dato il suo nome associato passato come parametro.
     * <p>
     * Precondizione: assumo parametro non nullo
     * e diverso da stringa vuota.
     * Post condizione: ritorna l'Optional di tipo T,
     * inizializzato con un elemento se ne è stato
     * trovato uno con lo stesso nome. Altrimenti
     * l'Optional ritornato è vuoto.
     *
     * @param nome il nome dell'elemento
     * @return l'optional dell'elemento cercato
     */
    public Optional<T> trovaElementoConNome(String nome) {
        for (T elemento : listaElementi) {
            if (elemento.isStessoNome(nome)) return Optional.of(elemento);
        }

        return Optional.empty();
    }

    /**
     * Metodo che permette d'inserire un elemento.
     * <p>
     * Precondizione: assumo il parametro non nullo e
     * correttamente inizializzato. Ovvero che sia
     * dotato di un nome univoco rispetto a tutti
     * gli altri elementi della lista in cui viene
     * aggiunto.
     *
     * @param e un oggetto generico di tipo T.
     */
    public void inserisciElemento(T e) {
        this.listaElementi.add(e);
    }

}
