package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

/**
 * @author Elia
 */
public class GerarchiaDiCategorie implements Manageable {
    private String nome;
    private Tree<Categoria> gerarchia;


    @Override
    public String getNome() {
        return nome;
    }
}
