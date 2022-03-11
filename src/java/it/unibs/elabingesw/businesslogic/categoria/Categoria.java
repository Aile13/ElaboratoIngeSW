package it.unibs.elabingesw.businesslogic.categoria;

import java.util.List;

/**
 * Classe Categoria che rappresenta una categoria generica
 * @author Elia Pitozzi
 */
public class Categoria {
    private final String nome;
    private final String descrizione;
    private final List<Campo> campiNativi;
    
    public Categoria(String nome, String descrizione, List<Campo> campiNativi) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.campiNativi = campiNativi;
    }

    public String getNome() {
        return nome;
    }

    public void inserisciCampiNativi(List<Campo> campi) {
        this.campiNativi.addAll(campi);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", campiNativi=" + campiNativi +
                '}';
    }
}
