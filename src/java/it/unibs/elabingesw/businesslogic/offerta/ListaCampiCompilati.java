package it.unibs.elabingesw.businesslogic.offerta;

import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Classe ListaCampiCompilati che permette al fruitore
 * di reperire e compilare i campi nativi ed ereditati
 * relativi ad una catgeoria foglia.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class ListaCampiCompilati implements Serializable {
    private final LinkedHashMap<Campo, String> campiCompilati;

    /**
     * Costruttore di classe, accetta come parametri due oggetti:
     * la gerarchia e la categoria foglia selezionate dal fruitore.
     *
     * @param gerarchiaSelezionata
     * @param categoriaFogliaSelezionata
     */ 
    public ListaCampiCompilati(GerarchiaDiCategorie gerarchiaSelezionata, Categoria categoriaFogliaSelezionata) {
        this.campiCompilati = new LinkedHashMap<>();
        estraiCampiDatiGerarchiaECategoriaFoglia(gerarchiaSelezionata, categoriaFogliaSelezionata);
    }

    /**
     * Metodo getter.
     *
     * @return la lista dei campi compilati
     */
    public LinkedHashMap<Campo, String> getCampiCompilati() {
        return campiCompilati;
    }

    /**
     * Metodo che permette di estrarre i campi nativi ed ereditati
     * relativi alla categoria foglia passata per parametro facente
     * parte della categoria anch'essa passata per parametro.
     *
     * @param gerarchia una gerarchia
     * @param categoriaFoglia una categoria foglia
     */ 
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

    /**
     * Metodo per la formattazione che converte un oggetto nella re-
     * lativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "ListaCampiCompilati{" +
                "campiCompilati=" + campiCompilati +
                '}';
    }
}
