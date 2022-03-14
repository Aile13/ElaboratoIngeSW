package it.unibs.elabingesw.businesslogic.utente;

import java.io.Serializable;

/**
 * @author Elia
 */
final public class Configuratore extends Utente {

    public Configuratore(String username, String password) {
        super(username, password);
    }

    public static Configuratore getDefaultConfiguratore() {
        return new Configuratore("nuovoconfiguratore", "default");
    }

    public static boolean isDefaultConfiguratoreByUsername(String username) {
        return getDefaultConfiguratore().isStessoNome(username);
    }

}
