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
     *
     * @param fileName
     */
    public GestoreGenerico(String fileName) {
        this.pathRepository = dataDir + fileName + ".dat";
        inizializzaDirDati();
        this.listaElementi = new ArrayList<>();
        caricaElementi();
    }
    
    /**
     * Metodo che crea una directory creando prima tutte le 
     * directory padre inesistenti.
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
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(pathRepository))) {
            output.writeObject(this.listaElementi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo che carica tutti gli utenti e tutte le gerar-
     * chie su file.
     */
    private void caricaElementi() {
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
     * nella lista o meno.
     *
     * @param nome il nome dell'elemento
     * @return TRUE se l'elemento è presente in lista
     *         FALSE se l'elemento non è presente in lista
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
     * dato il suo nome passato come parametro.
     *
     * @param nome il nome dell'elemento
     * @return l'elemento cercato
     */
    public Optional<T> trovaElementoConNome(String nome) {
        for (T elemento :
                listaElementi) {
            if (elemento.isStessoNome(nome))
                return Optional.of(elemento);
        }

        return Optional.empty();
    }
    
    /**
     * Metodo che permette di inserire un elemento.
     *
     * @param e un oggetto generico
     */
    public void inserisciElemento(T e) {
        this.listaElementi.add(e);
    }

}
