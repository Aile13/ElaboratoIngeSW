package it.unibs.elabingesw.subservice;

import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaRadice;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreScambio;
import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * Classe GerarchiaService che gestisce le varie operazioni
 * che si effettuano su una gerarchia e sulle eventuali
 * categorie radice e categorie figlie.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class GerarchiaService {

    private final GestoreGerarchie gestoreGerarchie;
    private final GestoreScambio gestoreScambio;

    /**
     * Costruttore di classe, accetta come parametro un oggetto
     * GestoreGerarchie.
     *
     * @param gestoreGerarchie
     * @param gestoreScambio
     * @see GestoreGerarchie
     */
    public GerarchiaService(GestoreGerarchie gestoreGerarchie, GestoreScambio gestoreScambio) {
        this.gestoreGerarchie = gestoreGerarchie;
        this.gestoreScambio = gestoreScambio;
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
            gestoreGerarchie.inserisciNuovaGerarchia(gerarchia);
        }
    }

    /**
     * Metodo che aggiunge eventuali sottocategorie (ricorsivamente)
     * a una specifica gerarchia che viene passata come parametro.
     *
     * @param gerarchia la gerarchia alla quale si vogliono aggiungere
     *                  sottocategorie
     * @return gerarchia oggetto della classe GerarchiaDiCategorie
     * @see GerarchiaDiCategorie
     */
    private void aggiungiSottoCategorie(GerarchiaDiCategorie gerarchia) {
        if (InputDati.yesOrNo("Vuoi aggiungere una sotto-categoria per " + gerarchia.getNome() + "?")) {
            var categoriaFiglio = chiediECreaCategoriaFiglioIn(gerarchia);
            var gerarchiaFiglio = gerarchia.inserisciSottoCategoria(categoriaFiglio);

            aggiungiSottoCategorie(gerarchiaFiglio);

            while (InputDati.yesOrNo("Vuoi aggiungere un'altra sotto-categoria per " + gerarchia.getNome() + "?")) {
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
     * @see @CampoService
     */
    private CategoriaRadice chiediECreaCategoriaRadice() {
        var nomeCategoriaRadice = InputDati.leggiStringaNonVuota("Inserisci nome della categoria radice: ");
        // check se nome già usato o meno tra le altre gerarchia
        while (gestoreGerarchie.isElementoInListaByNome(nomeCategoriaRadice)) {
            System.out.println("Errore nome categoria radice già usato: riprovare.");
            nomeCategoriaRadice = InputDati.leggiStringaNonVuota("Reinserisci nome della categoria radice: ");
        }
        var descrizione = InputDati.leggiStringaNonVuota("Inserisci descrizione per la categoria radice: ");

        var listaCampi = CampoService.chiediListaDiCampiPerCategoriaRadice();

        return new CategoriaRadice(nomeCategoriaRadice, descrizione, listaCampi);
    }

    /**
     * Metodo che chiede all'utente di inserire una categoria figlio
     * con i suoi vari campi.
     *
     * @return una categoria figlio
     * @see @CampoService
     * @param gerarchia
     */
    private CategoriaFiglio chiediECreaCategoriaFiglioIn(GerarchiaDiCategorie gerarchia) {
        var nomeCatFigl = InputDati.leggiStringaNonVuota("Inserisci nome della categoria figlio: ");
        while (gerarchia.isNomeCategoriaUsato(nomeCatFigl)) {
            System.out.println("Errore nome categoria figlio già usato: riprovare.");
            nomeCatFigl = InputDati.leggiStringaNonVuota("Reinserisci nome della categoria radice: ");
        }
        var descrizione = InputDati.leggiStringaNonVuota("Inserisci descrizione per la categoria figlio: ");
        var listaCampi = CampoService.chiediListaDiCampiPerCategoriaFiglio(gerarchia);

        return new CategoriaFiglio(nomeCatFigl, descrizione, listaCampi);
    }

    /**eu
     * Metodo per chiedere all'utente la conferma di inserimento
     * di una nuova gerarchia.
     *
     * @return TRUE se si conferma l'inserimento
     * FALSE se non si conferma l'inserimento
     */
    private boolean chiediConfermaInserimentoGerarchia() {
        return InputDati.yesOrNo("Vuoi inserire la nuova gerarchia?");
    }

    /**
     * Metodo che visualizza le gerarchie che sono state caricate
     * nell'applicativo.
     */
    public void visualizzaGerarchieInFormaEstesa() {
        System.out.println("Elenco delle gerarchie caricate:");
        if (this.gestoreGerarchie.getListaGerarchie().isEmpty()) {
            System.out.println("\tNessuna gerarchia presente.");
        } else {
            this.gestoreGerarchie.getListaGerarchie().forEach(System.out::println);
        }
    }

    public void visualizzaGerarchieInFormaRidotta() {
        System.out.println("Elenco delle gerarchie caricate:");
        if (this.gestoreGerarchie.getListaGerarchie().isEmpty()) {
            System.out.println("\tNessuna gerarchia presente.");
        } else {
            this.gestoreGerarchie.getListaGerarchie().forEach(
                    gerarchiaDiCategorie -> System.out.println(gerarchiaDiCategorie.toStringRidotto())
            );
        }
    }

    public void caricaDatiDaFileUtente() {
        var fileUtenteService = new FileUtenteService(this.gestoreGerarchie, this.gestoreScambio);

        fileUtenteService.avviaServizio();
    }
}
