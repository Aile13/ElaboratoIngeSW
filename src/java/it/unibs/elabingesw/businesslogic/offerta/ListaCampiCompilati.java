package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Elia
 */
public class ListaCampiCompilati implements Serializable {
    private final LinkedHashMap<Campo, String> campiCompilati;

    public ListaCampiCompilati(GerarchiaDiCategorie gerarchiaSelezionata, Categoria categoriaFogliaSelezionata) {
        this.campiCompilati = new LinkedHashMap<>();
        estraiCampiDatiGerarchiaECategoriaFoglia(gerarchiaSelezionata, categoriaFogliaSelezionata);
    }

    public ListaCampiCompilati() {
        this.campiCompilati = new LinkedHashMap<>();
    }

    public void inserisci(Campo campo, String valore) {
        this.campiCompilati.put(campo, valore);
    }

    public LinkedHashMap<Campo, String> getCampiCompilati() {
        return campiCompilati;
    }

    private void estraiCampiDatiGerarchiaECategoriaFoglia(GerarchiaDiCategorie gerarchia, Categoria categoriaFoglia) {
        List<Categoria> listaDiCategoriePadri = gerarchia.getListaDiCategoriePadriByCategoria(categoriaFoglia);
        Collections.reverse(listaDiCategoriePadri);
        for (Categoria categoria : listaDiCategoriePadri) {
            for (Campo campo : categoria.getCampiNativi()) {
                this.campiCompilati.put(campo, null);
            }
        }
        categoriaFoglia.getCampiNativi().forEach(campo -> this.campiCompilati.put(campo, null));
    }

    @Override
    public String toString() {
        return "ListaCampiCompilati{" +
                "campiCompilati=" + campiCompilati +
                '}';
    }
}
