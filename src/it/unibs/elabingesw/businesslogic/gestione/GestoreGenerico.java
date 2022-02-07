package it.unibs.elabingesw.businesslogic.gestione;

import java.util.List;

/**
 * @author Elia
 */
abstract class GestoreGenerico<T extends Manageable> {
    private final List<T> listaElementi;
    private final String pathRepository;

    GestoreGenerico(List<T> listaElementi, String pathRepository) {
        this.listaElementi = listaElementi;
        this.pathRepository = pathRepository;
    }

    public List<T> getListaElementi() {
        return listaElementi;
    }

    public void aggiungiElemento(T e) {
        this.listaElementi.add(e);
    }

}
