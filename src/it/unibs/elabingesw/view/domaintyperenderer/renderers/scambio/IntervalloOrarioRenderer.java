package it.unibs.elabingesw.view.domaintyperenderer.renderers.scambio;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * Classe IntervalloOrarioRenderer che implementa SelectableDomainTypeRenderer.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class IntervalloOrarioRenderer implements SelectableDomainTypeRenderer {
    
    /**
     * Metodo che restituisce il rendering dell'oggetto in input
     * a cui verrà applicato un cast.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return il rendering dell'intervallo orario
     */
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        IntervalloOrario intervalloOrario = (IntervalloOrario) domainTypeToRender;
        return "IntervalloOrario{" + "orarioIniziale=" + intervalloOrario.orarioIniziale() + ", orarioFinale=" + intervalloOrario.orarioFinale() + '}';
    }

    /**
     * Metodo che controlla se il renderer può gestire
     * il rendering dell'oggetto che viene passato per
     * parametro.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return TRUE se si può gestire il rendering dell'intervallo orario
     * FALSE se non si può gestire il rendering dell'intervallo orario
     */
    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof IntervalloOrario;
    }
}
