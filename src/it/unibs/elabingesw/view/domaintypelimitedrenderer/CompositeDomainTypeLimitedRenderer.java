package it.unibs.elabingesw.view.domaintypelimitedrenderer;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.limitedrenderers.categoria.*;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.limitedrenderers.scambio.ScambioLimitedRenderer;

import java.util.Set;

/*
 * Classe CompositeDomainTypeLimitedRenderer che implementa SelectableDomainTypeRenderer
 * e che permette l'uso aggregato di tutti i renderer limitati.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class CompositeDomainTypeLimitedRenderer implements SelectableDomainTypeLimitedRenderer {
    private final Set<SelectableDomainTypeLimitedRenderer> renderers =
            Set.of(
                    new CategoriaFiglioLimitedRenderer(),
                    new CategoriaLimitedRenderer(),
                    new CategoriaRadiceLimitedRenderer(),
                    new GerarchiaDiCategorieLimitedRenderer(),
                    new ScambioLimitedRenderer(),
                    new TreeNodeLimitedRenderer()
            );

    /**
     * Metodo che restituisce il rendering limitato 
     * dell'oggetto che viene passato per parametro 
     * dopo aver controllato se quest'ultimo è sup-
     * portato e se quindi è uno dei possibili
     * renderer limitati.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return il rendering limitato
     */
    @Override
    public String render(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        for (SelectableDomainTypeLimitedRenderer renderer : renderers) {
            if (renderer.canHandle(domainTypeToLimitedRender)) {
                return renderer.render(domainTypeToLimitedRender);
            }
        }
        return "limited render non supportato";
    }

    /**
     * Metodo che controlla se uno tra i renderer limitati
     * disponibili può gestire il rendering limitato dell'og-
     * getto che viene passato per parametro.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return TRUE se si può gestire il rendering limitato
     * FALSE se non si può gestire il rendering limitato
     */
    @Override
    public boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        for (SelectableDomainTypeLimitedRenderer renderer : renderers) {
            if (renderer.canHandle(domainTypeToLimitedRender)) return true;
        }
        return false;
    }
}
