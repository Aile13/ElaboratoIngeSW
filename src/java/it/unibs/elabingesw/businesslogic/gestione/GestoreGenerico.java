package it.unibs.elabingesw.businesslogic.gestione;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Elia
 */
abstract class GestoreGenerico<T extends Manageable> {
    private final String pathRepositoryName;
    private final List<T> listaElementi;

    GestoreGenerico(String pathRepository) {
        this.pathRepositoryName = pathRepository + ".dat"; // aggiungo estensione
        this.listaElementi = new ArrayList<>();
        caricaElementi();
    }

    void salvaDati() {
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(pathRepositoryName))) {
            output.writeObject(this.listaElementi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void caricaElementi() {
        if (new File(pathRepositoryName).exists()) {
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(pathRepositoryName))) {
                if (input.readObject() != null) {
                    this.listaElementi.addAll((List<T>) input.readObject());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    boolean isElementoInListaByNome(String nome) {
        return trovaElementoConNome(nome).isPresent();
    }

    List<T> getListaElementi() {
        return listaElementi;
    }

    Optional<T> trovaElementoConNome(String nome) {
        for (T elemento :
                listaElementi) {
            if (elemento.isStessoNome(nome))
                return Optional.of(elemento);
        }

        return Optional.empty();
    }

    void inserisciElemento(T e) {
        this.listaElementi.add(e);
    }

}
