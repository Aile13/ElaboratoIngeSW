package it.unibs.elabingesw.view.domaintyperenderer.renderers.scambio;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * @author Elia
 */
public class IntervalloOrarioRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        IntervalloOrario intervalloOrario = (IntervalloOrario) domainTypeToRender;
        return "IntervalloOrario{" + "orarioIniziale=" + intervalloOrario.orarioIniziale() + ", orarioFinale=" + intervalloOrario.orarioFinale() + '}';
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof IntervalloOrario;
    }
}
