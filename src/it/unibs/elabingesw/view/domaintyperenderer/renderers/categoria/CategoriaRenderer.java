package it.unibs.elabingesw.view.domaintyperenderer.renderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.categoria.Campo;
import it.unibs.elabingesw.businesslogic.categoria.Categoria;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe CategoriaRenderer che implementa SelectableDomainTypeRenderer.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class CategoriaRenderer implements SelectableDomainTypeRenderer {
    
    /**
     * Metodo che restituisce il rendering dell'oggetto in input
     * a cui verrà applicato un cast.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return il rendering della categoria
     */
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        Categoria categoria = (Categoria) domainTypeToRender;
        return "nome='" + categoria.getNome() + '\'' +
                ", descrizione='" + categoria.getDescrizione() + '\'' +
                ", campiNativi=" + renderListaDiCampiNativi(categoria.getCampiNativi());
    }

    /**
     * Metodo che restituisce il rendering della lista dei campi
     * nativi di una categoria passati come parametro.
     *
     * @param campiNativi la lista dei campi nativi della categoria
     * @return il rendering della lista dei campi nativi
     */
    private String renderListaDiCampiNativi(List<Campo> campiNativi){
        return "[" + campiNativi.stream().map(campo -> new CampoRenderer().render(campo)).collect(Collectors.joining(", ")) +
                "]";
    }

    /**
     * Metodo che controlla se il renderer può gestire
     * il rendering dell'oggetto che viene passato per
     * parametro.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return FALSE non si può gestire il rendering della categoria
     */
    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return false;
    }
}
