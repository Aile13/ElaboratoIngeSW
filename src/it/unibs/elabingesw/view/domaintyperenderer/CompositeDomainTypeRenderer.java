package it.unibs.elabingesw.view.domaintyperenderer;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.view.domaintyperenderer.renderers.categoria.*;
import it.unibs.elabingesw.view.domaintyperenderer.renderers.offerta.ListaCampiCompilatiRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.renderers.offerta.OffertaContextRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.renderers.offerta.state.*;
import it.unibs.elabingesw.view.domaintyperenderer.renderers.scambio.IntervalloOrarioRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.renderers.scambio.ScambioRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.renderers.utente.UtenteRenderer;

import java.util.Set;

/**
 * Classe CompositeDomainTypeRenderer che implementa SelectableDomainTypeRenderer
 * e che permette l'uso aggregato di tutti i renderer.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class CompositeDomainTypeRenderer implements SelectableDomainTypeRenderer {
    private final Set<SelectableDomainTypeRenderer> renderers =
            Set.of(
                    new CampoRenderer(),
                    new CategoriaFiglioRenderer(),
                    new CategoriaRadiceRenderer(),
                    new CategoriaRenderer(),
                    new GerarchiaDiCategorieRenderer(),
                    new TreeNodeRenderer(),
                    new AccoppiataStateRenderer(),
                    new ApertaStateRenderer(),
                    new ChiusaStateRenderer(),
                    new InScambioStateRenderer(),
                    new RitirataStateRenderer(),
                    new SelezionataStateRenderer(),
                    new ListaCampiCompilatiRenderer(),
                    new OffertaContextRenderer(),
                    new IntervalloOrarioRenderer(),
                    new ScambioRenderer(),
                    new UtenteRenderer()
            );

    /**
     * Metodo che restituisce il rendering dell'oggetto che
     * viene passato per parametro dopo aver controllato se 
     * quest'ultimo è supportato e se quindi è uno dei pos-
     * sibili renderer.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return il rendering
     */
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        for (SelectableDomainTypeRenderer renderer : renderers) {
            if (renderer.canHandle(domainTypeToRender)) {
                return renderer.render(domainTypeToRender);
            }
        }
        return "non supportato";
    }

    /**
     * Metodo che controlla se uno tra i renderer dispo-
     * nibili può gestire il rendering dell'oggetto che
     * viene passato per parametro.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return TRUE se si può gestire il rendering
     * FALSE se non si può gestire il rendering
     */
    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        for (SelectableDomainTypeRenderer renderer : renderers) {
            if (renderer.canHandle(domainTypeToRender))
                return true;
        }
        return false;
    }
}
