package it.unibs.elabingesw.domaintyperenderer;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.domaintyperenderer.renderers.categoria.*;
import it.unibs.elabingesw.domaintyperenderer.renderers.offerta.ListaCampiCompilatiRenderer;
import it.unibs.elabingesw.domaintyperenderer.renderers.offerta.OffertaContextRenderer;
import it.unibs.elabingesw.domaintyperenderer.renderers.offerta.state.*;
import it.unibs.elabingesw.domaintyperenderer.renderers.scambio.IntervalloOrarioRenderer;
import it.unibs.elabingesw.domaintyperenderer.renderers.scambio.ScambioRenderer;
import it.unibs.elabingesw.domaintyperenderer.renderers.utente.UtenteRenderer;

import java.util.List;
import java.util.Set;

/**
 * @author Elia
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

    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        for (SelectableDomainTypeRenderer renderer : renderers) {
            if (renderer.canHandle(domainTypeToRender)) {
                return renderer.render(domainTypeToRender);
            }
        }
        return "non supportato";
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        for (SelectableDomainTypeRenderer renderer : renderers) {
            if (renderer.canHandle(domainTypeToRender))
                return true;
        }
        return false;
    }
}
