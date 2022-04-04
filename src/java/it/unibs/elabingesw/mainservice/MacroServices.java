package it.unibs.elabingesw.mainservice;

import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreOfferte;
import it.unibs.elabingesw.businesslogic.gestione.GestoreScambio;
import it.unibs.elabingesw.businesslogic.gestione.GestoreUtenti;
import it.unibs.elabingesw.businesslogic.utente.Utente;
import it.unibs.elabingesw.subservice.GerarchiaService;
import it.unibs.elabingesw.subservice.OfferteService;
import it.unibs.elabingesw.subservice.ScambioService;

/**
 * Classe MacroService di gestione generale.
 * 
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class MacroServices {
    private final GestoreUtenti gestoreUtenti;
    private final GestoreGerarchie gestoreGerarchie;
    private final GestoreScambio gestoreScambio;
    private final GestoreOfferte gestoreOfferte;

    private final GerarchiaService gerarchiaService;
    private final ScambioService scambioService;
    private final OfferteService offerteService;

    private Utente utente;
    
    /**
     * Costruttore di classe, accetta come parametri un oggetto di
     * tipo GestoreUtenti e un oggetto di tipo GestoreGerarchie.
     *
     * @param gestoreUtenti
     * @param gestoreGerarchie
     * @param gestoreScambio
     * @param gestoreOfferte
     * @see GestoreUtenti
     * @see GestoreGerarchie
     */
    public MacroServices(GestoreUtenti gestoreUtenti, GestoreGerarchie gestoreGerarchie, GestoreScambio gestoreScambio, GestoreOfferte gestoreOfferte) {
        this.gestoreUtenti = gestoreUtenti;
        this.gestoreGerarchie = gestoreGerarchie;
        this.gestoreScambio = gestoreScambio;
        this.gestoreOfferte = gestoreOfferte;

        this.gerarchiaService = new GerarchiaService(this.gestoreGerarchie);
        this.scambioService = new ScambioService(this.gestoreScambio);
        this.offerteService = new OfferteService(this.gestoreOfferte, this.gestoreGerarchie, this.gestoreScambio);
    }
    
    /**
     * Metodo che salva utenti e gerarchie su file JSON.
     * @see GestoreUtenti
     * @see GestoreGerarchie
     */
    private void eseguiSalvataggio() {
        gestoreUtenti.salvaUtenti();
        gestoreGerarchie.salvaGerarchie();
        gestoreScambio.salvaInfoScambio();
        gestoreOfferte.salvaOfferte();
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
    public void visualizzaGerarchieFormaEstesa() {
       this.gerarchiaService.visualizzaGerarchieInFormaEstesa();
    }

    public void visualizzaGerarchieFormaRidotta() {
     this.gerarchiaService.visualizzaGerarchieInFormaRidotta();
    }

    public void visualizzaInfoDiScambioFormaEstesa() {
        this.scambioService.visualizzaInfoDiScambioFormaEstesa();
    }

    public void visualizzaInfoDiScambioFormaRidotta() {
        this.scambioService.visualizzaInfoDiScambioFormaRidotta();
    }

    public void impostaInfoDiScambio() {
        this.scambioService.impostaInfoScambio();
    }

    public void setUser(Utente userLogged) {
        this.offerteService.setUser(userLogged);
    }

    public void visualizzaOfferteUtente() {
        this.offerteService.visualizzaOfferteUtente();
    }

    public void creaNuovaOfferta() {
        this.offerteService.creaNuovaOfferta();
    }

    public void ritiraOfferte() {
        this.offerteService.ritiraOfferte();
    }

    public void visualizzaOfferteAperteConSelezioneFoglia() {
        this.offerteService.visualizzaOfferteAperteConSelezioneFoglia();
    }

    public void selezionaUnaOffertaApertaPerBaratto() {
        this.offerteService.selezionaUnaOffertaApertaPerBaratto();
    }

    public void visualizzaProposteDiScambio() {
        this.offerteService.visualizzaProposteDiScambio();
    }

    public void visualizzaOfferteInScambio() {
        this.offerteService.visualizzaOfferteInScambio();
    }

    public void visualizzaUltimeRispostePerOfferteInScambio() {
        this.offerteService.visualizzaUltimeRispostePerOfferteInScambio();
    }

    public void visualizzaOfferteInScambioEChiuseConSelezioneFoglia() {
        this.offerteService.visualizzaOfferteInScambioEChiuseConSelezioneFoglia();
    }
}
