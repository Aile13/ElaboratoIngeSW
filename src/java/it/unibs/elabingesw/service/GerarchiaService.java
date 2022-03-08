package it.unibs.elabingesw.service;

import it.unibs.elabingesw.businesslogic.categoria.CategoriaFiglio;
import it.unibs.elabingesw.businesslogic.categoria.CategoriaRadice;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.gestione.GestoreGerarchie;
import it.unibs.eliapitozzi.mylib.InputDati;

/**
 * @author Elia
 */
public class GerarchiaService {

    private final GestoreGerarchie gestoreGerarchie;

    public GerarchiaService(GestoreGerarchie gestoreGerarchie) {

        this.gestoreGerarchie = gestoreGerarchie;

    }

    public void creaNuovaGerarchia() {
        var categoriaRadice = chiediCategoriaRadice();
        var gerarchia = new GerarchiaDiCategorie(categoriaRadice);

        gerarchia = aggiungiSottoCategorie(gerarchia);

        if (chiediConfermaInserimentoGerarchia()) {
            gestoreGerarchie.inserisciNuovaGerarchia(gerarchia);
        }
    }

    private GerarchiaDiCategorie aggiungiSottoCategorie(GerarchiaDiCategorie gerarchia) {
        if (InputDati.yesOrNo("Vuoi aggiungere una sotto-categoria per " + gerarchia.getNome() + "?")) {
            CategoriaFiglio figlio = chiediCategoriaFiglio();
            var gerarchiaFiglio = gerarchia.inserisciSottoCategoria(figlio);

            if (InputDati.yesOrNo("Vuoi aggiungervi una sotto-categoria alla gerarchia figlia?")) {
                var gerarchiaDiCategorie = aggiungiSottoCategorie(gerarchiaFiglio);
                while (InputDati.yesOrNo("Vuoi aggiungervi un'altra sotto-categoria?")) {
                    aggiungiSottoCategorie(gerarchiaDiCategorie);
                }
            }

            while (InputDati.yesOrNo("Vuoi aggiungere un'altra sotto categoria per " + gerarchia.getNome() + "?")) {
                gerarchia.inserisciSottoCategoria(chiediCategoriaFiglio());
            }
        }
        return gerarchia;
    }

    private CategoriaRadice chiediCategoriaRadice() {
        var nomeCatRad = InputDati.leggiStringaNonVuota("Inserisci nome della categoria radice: ");
        var descriozione = InputDati.leggiStringaNonVuota("Inserisci descrizione per la categoria radice: ");

        var listaCampi = CampoService.chiediListaDiCampi();

        return new CategoriaRadice(nomeCatRad, descriozione, listaCampi);
    }

    private CategoriaFiglio chiediCategoriaFiglio() {
        var nomeCatFigl = InputDati.leggiStringaNonVuota("Inserisci nome della categoria figlio: ");
        var descrizione = InputDati.leggiStringaNonVuota("Inserisci descrizione per la categoria radice: ");

        var listaCampi = CampoService.chiediListaDiCampi();

        return new CategoriaFiglio(nomeCatFigl, descrizione, listaCampi);
    }

    private boolean chiediConfermaInserimentoGerarchia() {
        return InputDati.yesOrNo("Vuoi inserire la nuova gerarchia?");
    }

    public void visualizzaGerarchie() {
        System.out.println("Elenco delle gerarchie caricate:");
        this.gestoreGerarchie.getListaGerarchie()
                .forEach(System.out::println);
    }
}
