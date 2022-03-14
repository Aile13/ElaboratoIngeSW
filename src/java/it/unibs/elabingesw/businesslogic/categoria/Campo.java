package it.unibs.elabingesw.businesslogic.categoria;

import java.io.Serializable;
import java.util.List;

/**
 * @author Elia
 */
public final class Campo implements Serializable {
    private String nome;
    private boolean obbligatorio;

    public Campo(String nome, boolean obbligatorio) {
        this.nome = nome;
        this.obbligatorio = obbligatorio;
    }

    public static List<Campo> getCampiDiDefault() {
        return List.of(
                new Campo("Stato di conservazione", true),
                new Campo("Descrizione libera", false)
        );
    }

    @Override
    public String toString() {
        return "Campo{" +
                "nome='" + nome + '\'' +
                ", obbligatorio=" + obbligatorio +
                '}';
    }
}
