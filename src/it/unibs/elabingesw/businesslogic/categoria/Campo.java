package it.unibs.elabingesw.businesslogic.categoria;

/**
 * @author Elia
 */
final class Campo {
    private String nome;
    private boolean obbligatorio;

    public Campo(String nome, boolean obbligatorio) {
        this.nome = nome;
        this.obbligatorio = obbligatorio;
    }
}
