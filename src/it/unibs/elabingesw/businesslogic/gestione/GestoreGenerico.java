package it.unibs.elabingesw.businesslogic.gestione;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Elia
 */
abstract class GestoreGenerico<T extends Manageable> {
    private final List<T> listaElementi;
    private final String pathRepository;

    GestoreGenerico(String pathRepository) {
        this.pathRepository = pathRepository;
        this.listaElementi = new ArrayList<>();
    }

    public List<T> getListaElementi() {
        return listaElementi;
    }

    public Optional<T> trovaConNome(String nome) {
        for (T elemento:
             listaElementi) {
            if (elemento.getNome().equals(nome))
                return Optional.of(elemento);
        }
        return Optional.empty();
    }

    public void aggiungiElemento(T e) {
        this.listaElementi.add(e);
    }

}
