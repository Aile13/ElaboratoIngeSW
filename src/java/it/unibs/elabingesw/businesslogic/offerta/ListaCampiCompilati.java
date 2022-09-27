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
 * relativi a una categoria foglia, a l'offerta appartiene.
 * <p>
 * Invariante di classe: assumo l'attributo immutabile,
 * dopo la creazione dell'oggetto.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class ListaCampiCompilati implements Serializable {
    private final LinkedHashMap<Campo, String> campiCompilati;

    /**
     * Costruttore di classe, accetta come parametri due oggetti:
     * la gerarchia e la categoria foglia selezionate dal fruitore.
     * <p>
     * Precondizione: assumo parametri costruttore non nulli e correttamente inizializzati.
     * Per gerarchiaSelezionata intendo che tal parametro sia una istanza della gerarchia,
     * registrata nel sistema, che contiene la categoria foglia (secondo parametro)
     * a cui è associata la offerta a cui si sta associando la nuova istanza di ListaCampiCompilati.
     * Quindi categoriaFogliaSelezionata deve essere l'istanza della categoria foglia (terminale) presente
     * nella gerarchia passata come primo parametro, e questa deve essere la categoria a cui l'offerta
     * per cui si sta costruendo la lista campi compilati è appartenente.
     * Post condizione: Quella del secondo metodo chiamato.
     *
     * @param gerarchiaSelezionata       gerarchia a cui appartiene la categoria foglia associata all'offerta
     * @param categoriaFogliaSelezionata categoria foglia associata all'offerta.
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
     * <p>
     * Precondizione: quella del costruttore di classe.
     * Post condizione: data la gerarchia e una sua categoria foglia (terminale),
     * si estraggono e collezionano i vari campi nativi che interessano ogni categoria padre della tal categoria,
     * a partire dalla categoria foglia stessa fino ad arrivare alla categoria radice della gerarchia
     * inclusa. In modo da totalizzare tutti i campi che dovranno essere associati e compilati
     * a una offerta appartenente a tal gerarchia e a tal specifica categoria foglia.
     *
     * @param gerarchia       una gerarchia
     * @param categoriaFoglia una categoria foglia della gerarchia
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
     * Metodo per la formattazione che converte un oggetto
     * nella relativa rappresentazione di stringa.
     *
     * @return stringa dell'oggetto convertito
     */
    @Override
    public String toString() {
        return "ListaCampiCompilati{" + "campiCompilati=" + campiCompilati + '}';
    }
}
