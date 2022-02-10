package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.util.Objects;

/**
 * @author Elia
 */
public class GerarchiaDiCategorie implements Manageable {
    private String nome;
    private Tree<Categoria> gerarchia;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GerarchiaDiCategorie that = (GerarchiaDiCategorie) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public boolean isStessoNome(String nome) {
        return this.nome.equals(nome);
    }
}
