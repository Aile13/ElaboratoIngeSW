package it.unibs.elabingesw.controller.subservice;

import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaRadice;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.repository.GerarchiaRepository;
import it.unibs.elabingesw.businesslogic.repository.gestori.GestoreGerarchieSerializableRepository;
import it.unibs.elabingesw.businesslogic.repository.ScambioRepository;
import it.unibs.elabingesw.view.FileUtenteServiceView;
import it.unibs.elabingesw.view.GerarchiaServiceView;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.CompositeDomainTypeLimitedRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.CompositeDomainTypeRenderer;

/**
 * Classe GerarchiaServiceController che gestisce le varie operazioni
 * che si effettuano su una gerarchia e sulle eventuali
 * categorie radice e categorie figlie.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class GerarchiaServiceController {

    private final GerarchiaRepository gerarchiaRepository;
    private final ScambioRepository scambioRepository;
    private final GerarchiaServiceView view = new GerarchiaServiceView();

    /**
     * Costruttore di classe, accetta come parametro un oggetto
     * GerarchiaRepository e un oggetto ScambioRepository.
     *
     * @param gerarchiaRepository oggetto di tipo GerarchiaRepository
     * @param scambioRepository oggetto di tipo ScambioRepository
     */
    public GerarchiaServiceController(GerarchiaRepository gerarchiaRepository, ScambioRepository scambioRepository) {
        this.gerarchiaRepository = gerarchiaRepository;
        this.scambioRepository = scambioRepository;
    }

    /**
     * Metodo che crea una nuova gerarchia: una volta confermata la
     * sua creazione, questa viene aggiunta all'applicativo.
     */
    public void creaNuovaGerarchia() {
        var categoriaRadice = chiediECreaCategoriaRadice();
        var gerarchia = new GerarchiaDiCategorie(categoriaRadice);

        aggiungiSottoCategorie(gerarchia);

        if (chiediConfermaInserimentoGerarchia()) {
            gerarchiaRepository.inserisciNuovaGerarchia(gerarchia);
        }
    }

    /**
     * Metodo che aggiunge eventuali sotto categorie (ricorsivamente)
     * a una specifica gerarchia che viene passata come parametro.
     *
     * @param gerarchia la gerarchia alla quale si vogliono aggiungere
     *                  sotto categorie
     * @return gerarchia oggetto di tipo GerarchiaDiCategorie
     * @see GerarchiaDiCategorie
     */
    private void aggiungiSottoCategorie(GerarchiaDiCategorie gerarchia) {
        if (view.chiediConfermaInserimentoSottoCategoriaConNome(gerarchia.getNome())) {
            var categoriaFiglio = chiediECreaCategoriaFiglioIn(gerarchia);
            var gerarchiaFiglio = gerarchia.inserisciSottoCategoria(categoriaFiglio);

            aggiungiSottoCategorie(gerarchiaFiglio);

            while (view.chiediConfermaInserimentoAltraSottoCategoriaConNome(gerarchia.getNome())) {
                var altraCategoriaFiglio = chiediECreaCategoriaFiglioIn(gerarchia);
                var altraGerarchiaFiglio = gerarchia.inserisciSottoCategoria(altraCategoriaFiglio);
                aggiungiSottoCategorie(altraGerarchiaFiglio);
            }
        }
    }

    /**
     * Metodo che chiede all'utente di inserire una categoria radice
     * con i suoi vari campi.
     *
     * @return una categoria radice
     * @see CampoServiceController
     */
    private CategoriaRadice chiediECreaCategoriaRadice() {
        var nomeCategoriaRadice = view.getNomeCategoriaRadiceString();
        while (gerarchiaRepository.isGerarchiaPresenteByNome(nomeCategoriaRadice)) {
            view.visualizzaMessaggio("Errore nome categoria radice già usato: riprovare.");
            nomeCategoriaRadice = view.getNewNomeCategoriaRadiceString();
        }
        var descrizione = view.getDescrizioneCategoriaRadiceString();

        var listaCampi = CampoServiceController.chiediListaDiCampiPerCategoriaRadice();

        return new CategoriaRadice(nomeCategoriaRadice, descrizione, listaCampi);
    }

    /**
     * Metodo che chiede all'utente d'inserire una categoria figlio
     * con i suoi vari campi.
     *
     * @param gerarchia un oggetto di tipo Gerarchia
     * @return una categoria figlio
     * @see CampoServiceController
     */
    private CategoriaFiglio chiediECreaCategoriaFiglioIn(GerarchiaDiCategorie gerarchia) {
        var nomeCategoriaFiglio = view.getNomeCategoriaFiglioString();
        while (gerarchia.isNomeCategoriaUsato(nomeCategoriaFiglio)) {
            view.visualizzaMessaggio("Errore nome categoria figlio già usato: riprovare.");
            nomeCategoriaFiglio = view.getNewNomeCategoriaFiglioString();
        }
        var descrizione = view.getDescrizioneCategoriaFiglioString();
        var listaCampi = CampoServiceController.chiediListaDiCampiPerCategoriaFiglio(gerarchia);

        return new CategoriaFiglio(nomeCategoriaFiglio, descrizione, listaCampi);
    }

    /**
     * Metodo per chiedere all'utente la conferma d'inserimento
     * di una nuova gerarchia.
     *
     * @return TRUE se si conferma l'inserimento
     * FALSE se non si conferma l'inserimento
     */
    private boolean chiediConfermaInserimentoGerarchia() {
        return view.chiediConfermaInserimentoNuovaGerarchia();
    }

    /**
     * Metodo che visualizza le gerarchie che sono state caricate
     * nell'applicativo.
     */
    public void visualizzaGerarchieInFormaEstesa() {
        view.visualizzaMessaggio("Elenco delle gerarchie caricate:");
        if (this.gerarchiaRepository.getListaGerarchie().isEmpty()) {
           view.visualizzaMessaggio("\tNessuna gerarchia presente.");
        } else {
            this.gerarchiaRepository.getListaGerarchie().forEach(gerarchiaDiCategorie ->
                    view.visualizzaMessaggio(new CompositeDomainTypeRenderer().render(gerarchiaDiCategorie))
            );
        }
    }

    /**
     * Metodo che visualizza le gerarchie che sono state caricate
     * nell'applicativo in forma ridotta.
     */
    public void visualizzaGerarchieInFormaRidotta() {
        view.visualizzaMessaggio("Elenco delle gerarchie caricate:");
        if (this.gerarchiaRepository.getListaGerarchie().isEmpty()) {
            view.visualizzaMessaggio("\tNessuna gerarchia presente.");
        } else {
            this.gerarchiaRepository.getListaGerarchie().forEach(gerarchiaDiCategorie ->
                    view.visualizzaMessaggio(new CompositeDomainTypeLimitedRenderer().render(gerarchiaDiCategorie))
            );
        }
    }

    /**
     * Metodo che carica i dati da un file dell'utente.
     *
     * @see CampoServiceController
     */
    public void caricaDatiDaFileUtente() {
        var fileUtenteServiceView = new FileUtenteServiceView();
        var fileUtenteServiceController = new FileUtenteServiceController(fileUtenteServiceView, this.gerarchiaRepository, this.scambioRepository);

        fileUtenteServiceController.avviaServizio();
    }
}
