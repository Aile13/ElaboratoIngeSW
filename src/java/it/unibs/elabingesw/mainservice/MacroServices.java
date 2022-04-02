package it.unibs.elabingesw.mainservice;

import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreScambio;
import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;
import it.unibs.elabingesw.subservice.GerarchiaService;
import it.unibs.elabingesw.subservice.ScambioService;

/**
 * Classe MacroServices di gestione generale.
 * 
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class MacroServices {
    private final GestoreUtenti gestoreUtenti;
    private final GestoreGerarchie gestoreGerarchie;
    private final GestoreScambio gestoreScambio;
    private final GerarchiaService gerarchiaService;
    private final ScambioService scambioService;
    
    /**
     * Costruttore di classe, accetta come parametri un oggetto di
     * tipo GestoreUtenti e un oggetto di tipo GestoreGerarchie.
     *
     * @param gestoreUtenti
     * @param gestoreGerarchie
     * @param gestoreScambio
     * @see GestoreUtenti
     * @see GestoreGerarchie
     * @see GestoreScambio
     */
    public MacroServices(GestoreUtenti gestoreUtenti, GestoreGerarchie gestoreGerarchie, GestoreScambio gestoreScambio) {
        this.gestoreUtenti = gestoreUtenti;
        this.gestoreGerarchie = gestoreGerarchie;
        this.gestoreScambio = gestoreScambio;
        this.gerarchiaService = new GerarchiaService(this.gestoreGerarchie);
        this.scambioService = new ScambioService(this.gestoreScambio);
    }
    
    /**
     * Metodo che salva utenti, gerarchie e informazioni
     * sugli scambi.
     *
     * @see GestoreUtenti
     * @see GestoreGerarchie
     */
    private void eseguiSalvataggio() {
        gestoreUtenti.salvaUtenti();
        gestoreGerarchie.salvaGerarchie();
        gestoreScambio.salvaInfoScambio();
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
     * visualizzare tutte le gerarchie caricate.
     *
     * @see GerarchiaService
     */
    public void visualizzaGerarchieFormaEstesa() {
       this.gerarchiaService.visualizzaGerarchieInFormaEstesa();
    }
    
    /**
     * Metodo che rimanda alla classe GerarchiaService per
     * visualizzare tutte le gerarchie caricate in forma 
     * ridotta.
     *
     * @see GerarchiaService
     */
    public void visualizzaGerarchieFormaRidotta() {
     this.gerarchiaService.visualizzaGerarchieInFormaRidotta();
    }
    
    /**
     * Metodo che rimanda alla classe ScambioService per
     * visualizzare tutte le informazioni degli scambi.
     *
     * @see ScambioService
     */
    public void visualizzaInfoDiScambioFormaEstesa() {
        this.scambioService.visualizzaInfoDiScambioFormaEstesa();
    }
    
    /**
     * Metodo che rimanda alla classe ScambioService per
     * visualizzaretutte le informazioni degli scambi in
     * forma ridotta.
     *
     * @see ScambioService
     */
    public void visualizzaInfoDiScambioFormaRidotta() {
        this.scambioService.visualizzaInfoDiScambioFormaRidotta();
    }
    
    /**
     * Metodo che rimanda alla classe ScambioService per
     * impostare le informazioni sugli scambi.
     *
     * @see ScambioService
     */
    public void impostaInfoDiScambio() {
        this.scambioService.impostaInfoScambio();
    }
}
