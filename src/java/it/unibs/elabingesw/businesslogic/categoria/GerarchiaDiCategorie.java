package it.unibs.elabingesw.businesslogic.categoria;

import it.unibs.elabingesw.businesslogic.gestione.Manageable;

import java.io.Serializable;

/**
 * @author Elia
 */
public class GerarchiaDiCategorie implements Manageable, Serializable {
    private final Tree<Categoria> gerarchia;

    private GerarchiaDiCategorie(Tree<Categoria> gerarchia) {
        this.gerarchia = gerarchia;
    }

    public GerarchiaDiCategorie(Categoria categoriaRadice) {
        this(new Tree<>(categoriaRadice));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GerarchiaDiCategorie that = (GerarchiaDiCategorie) o;

        return gerarchia.equals(that.gerarchia);
    }

    @Override
    public int hashCode() {
        return gerarchia.hashCode();
    }

    @Override
    public boolean isStessoNome(String nome) {
        return getNome().equals(nome);
    }

    public String getNome() {
        return this.gerarchia.getDato().getNome();
    }

    @Override
    public String toString() {
        return "GerarchiaDiCategorie{" +
                "nome='" + this.gerarchia.getDato().getNome() + '\'' +
                ", gerarchia=" + gerarchia +
                '}';
    }

    public GerarchiaDiCategorie inserisciSottoCategoria(CategoriaFiglio categoriaFiglio) {
        this.gerarchia.aggiungiFiglio(categoriaFiglio);
        return new GerarchiaDiCategorie(categoriaFiglio);
    }
}
