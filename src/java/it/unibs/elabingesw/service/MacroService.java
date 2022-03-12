package it.unibs.elabingesw.service;

import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;

/**
 * Classe MacroService di gestione generale.
 * 
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class MacroService {
    private final GestoreUtenti gestoreUtenti;
    private final GestoreGerarchie gestoreGerarchie;
    private final GerarchiaService gerarchiaService;
    
    /**
     * Costruttore di classe, accetta come parametri un oggetto di
     * tipo GestoreUtenti e un oggetto di tipo GestoreGerarchie.
     *
     * @param gestoreUtenti
     * @param gestoreGerarchie
     * @see GestoreUtenti
     * @see GestoreGerarchie
     */
    public MacroService(GestoreUtenti gestoreUtenti, GestoreGerarchie gestoreGerarchie) {
        this.gestoreUtenti = gestoreUtenti;
        this.gestoreGerarchie = gestoreGerarchie;
        this.gerarchiaService = new GerarchiaService(this.gestoreGerarchie);
    }
    
    /**
     * Metodo che salva utenti e gerarchie su file JSON.
     */
    private void eseguiSalvataggio() {
        gestoreUtenti.salvaUtenti();
        gestoreGerarchie.salvaGerarchie();
    }
    
    /**
     * Metodo che rimanda alla classe GerarchiaService per
     * creare una nuova gerarchia.
     * 
     * @see GerarchiaService
     */
    public void creaNuovaGerarchia() {
        this.gerarchiaService.creaNuovaGerarchia();
    }
    
    /**
     * Metodo che esegue la procedura di uscita dall'appli-
     * cativo dopo aver salvato i dati inseriti.
     */
    public void eseguiProceduraDiUscita() {
        eseguiSalvataggio();
    }
    
    /**
     * Metodo che rimanda alla classe GerarchiaService per
     * visualizzare le tutte gerarchie caricate.
     *
     * @see GerarchiaService
     */
    public void visualizzaGerarchie() {
       this.gerarchiaService.visualizzaGerarchie();

    }
}
