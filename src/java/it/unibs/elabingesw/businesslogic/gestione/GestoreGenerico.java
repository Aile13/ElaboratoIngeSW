package it.unibs.elabingesw.businesslogic.gestione;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Elia
 */
abstract class GestoreGenerico<T extends Manageable & Serializable> {
    private final String pathRepository;
    private final String dataDir = "./Dati/";
    private final List<T> listaElementi;

    public GestoreGenerico(String fileName) {
        this.pathRepository = dataDir + fileName + ".dat";
        inizializzaDirDati();
        this.listaElementi = new ArrayList<>();
        caricaElementi();
    }

    private void inizializzaDirDati() {
        try {
            Files.createDirectories(Path.of(this.dataDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvaDati() {
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(pathRepository))) {
            output.writeObject(this.listaElementi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public boolean isElementoInListaByNome(String nome) {
        return trovaElementoConNome(nome).isPresent();
    }

    public List<T> getListaElementi() {
        return listaElementi;
    }

    public Optional<T> trovaElementoConNome(String nome) {
        for (T elemento :
                listaElementi) {
            if (elemento.isStessoNome(nome))
                return Optional.of(elemento);
        }

        return Optional.empty();
    }

    public void inserisciElemento(T e) {
        this.listaElementi.add(e);
    }

}
