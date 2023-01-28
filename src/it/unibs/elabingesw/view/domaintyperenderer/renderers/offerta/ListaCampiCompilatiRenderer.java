package it.unibs.elabingesw.view.domaintyperenderer.renderers.offerta;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.offerta.ListaCampiCompilati;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * Classe ListaCampiCompilatiRenderer che implementa SelectableDomainTypeRenderer.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class ListaCampiCompilatiRenderer implements SelectableDomainTypeRenderer {
    
    /**
     * Metodo che restituisce il rendering dell'oggetto in input
     * a cui verrà applicato un cast.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return il rendering della lista dei campi compilati
     */
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        ListaCampiCompilati listaCampiCompilati = (ListaCampiCompilati) domainTypeToRender;
        return "ListaCampiCompilati{" + "campiCompilati=" + listaCampiCompilati.getCampiCompilati() + '}';
    }

    /**
     * Metodo che controlla se il renderer può gestire
     * il rendering dell'oggetto che viene passato per
     * parametro.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return TRUE se si può gestire il rendering della lista dei campi compilati
     * FALSE se non si può gestire il rendering della lista dei campi compilati
     */
    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof ListaCampiCompilati;
    }
}
