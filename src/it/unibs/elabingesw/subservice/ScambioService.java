package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.repository.ScambioRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreScambioSerializableRepository;
import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;
import it.unibs.elabingesw.view.ScambioServiceView;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.CompositeDomainTypeLimitedRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.CompositeDomainTypeRenderer;
import it.unibs.eliapitozzi.mylib.InputDati;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe ScambioService che gestisce le varie operazioni
 * che si effettuano su uno scambio di un articolo.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class ScambioService {
    private final ScambioRepository scambioRepository;
    private final ScambioServiceView view = new ScambioServiceView();

    /**
     * Costruttore di classe, accetta come parametro un oggetto
     * GestoreScambio.
     *
     * @param scambioRepository
     * @see GestoreScambioSerializableRepository
     */
    public ScambioService(ScambioRepository scambioRepository) {
        this.scambioRepository = scambioRepository;
    }

    /**
     * Metodo che chiede all'utente e imposta le varie informazioni
     * relative a uno scambio.
     */
    public void impostaInfoScambio() {
        if (this.scambioRepository.isInfoScambioDaConfigurare()) {
            view.visualizzaMessaggio("Procedura settaggio info di scambio avviata");
            var piazza = chiediNomePiazza();
            var listaLuoghi = chiediLuoghiDiScambio();
            var listaGiorni = chiediGiorniDiScambio();
            var intervalliOrari = chiediIntervalliOrari();
            var scadenza = chiediScadenza();

            Scambio scambio = new Scambio(piazza, listaLuoghi, listaGiorni, intervalliOrari, scadenza);

            scambioRepository.impostaInfoDiScambio(scambio);
            view.visualizzaMessaggio("Info di scambio impostate correttamente.");
        } else {
            view.visualizzaMessaggio("\tAttenzione: info di scambio già impostate.");
        }
    }

    /**
     * Metodo che chiede la scadenza, ossia il numero massimo di
     * giorni entro cui un fruitore può accettare una proposta di
     * scambio.
     *
     * @return la scadenza in giorni
     */
    private int chiediScadenza() {
        view.visualizzaMessaggio("Settaggio parametro scadenza");
        return view.chiediGiorniScadenza();
    }

    /**
     * Metodo che chiede gli intervalli orari in cui è
     * possibile effettuare gli scambi.
     *
     * @return la lista degli intervalli orari
     */
    private List<IntervalloOrario> chiediIntervalliOrari() {
        view.visualizzaMessaggio("Settaggio parametro intervalli orari");
        return IntervalloOrariService.chiediIntervalliOrari();
    }

    /**
     * Metodo che chiede i giorni della settimana in cui è possibile
     * effettuare scambi.
     *
     * @return la lista dei giorni
     */
    private List<DayOfWeek> chiediGiorniDiScambio() {
        view.visualizzaMessaggio("Settaggio parametro giorni di scambio");
        return GiorniDiSettimanaService.chiediGiorniDiSettimana();
    }

    /**
     * Metodo che chiede i luoghi in cui si possono effettuare gli
     * scambi.
     *
     * @return la lista dei luoghi
     */
    private List<String> chiediLuoghiDiScambio() {
        view.visualizzaMessaggio("Settaggio parametro luoghi di scambio");
        List<String> listaLuoghi = new ArrayList<>();
        String nuovoLuogo = view.chiediLuogoDiScambioString();
        listaLuoghi.add(nuovoLuogo);
        while (view.chiediConfermaInserimentoNuovoLuogoDiScambio()) {
            nuovoLuogo = view.chiediNewLuogoDiScambioString();
            while (listaLuoghi.contains(nuovoLuogo)) {
                view.visualizzaMessaggio("Errore: luogo inserito già presente, inserirne un altro.");
                nuovoLuogo = view.chiediNewLuogoDiScambioString();
            }
            listaLuoghi.add(nuovoLuogo);
        }
        return listaLuoghi;
    }

    /**
     * Metodo che chiede il nome della piazza.
     *
     * @return il nome della piazza
     */
    private String chiediNomePiazza() {
        view.visualizzaMessaggio("Settaggio parametro piazza");
        return view.chiediNomeCittaString();
    }

    /**
     * Metodo che visualizza le informazioni di un determinato
     * scambio.
     */
    public void visualizzaInfoDiScambioFormaEstesa() {
        view.visualizzaMessaggio("Info parametri di scambio:");
        if (this.scambioRepository.isInfoScambioDaConfigurare()) {
            view.visualizzaMessaggio("\tInfo ancora da configurare");
        } else {
            view.visualizzaMessaggio(new CompositeDomainTypeRenderer().render(this.scambioRepository.getInfoDiScambio().get()));
        }

    }

    /**
     * Metodo che visualizza le informazioni di un determinato
     * scambio in forma ridotta.
     */
    public void visualizzaInfoDiScambioFormaRidotta() {
        view.visualizzaMessaggio("Info parametri di scambio:");
        if (this.scambioRepository.isInfoScambioDaConfigurare()) {
            view.visualizzaMessaggio("\tInfo ancora da configurare");
        } else {
            view.visualizzaMessaggio(new CompositeDomainTypeLimitedRenderer().render(this.scambioRepository.getInfoDiScambio().get()));
        }

    }
}
