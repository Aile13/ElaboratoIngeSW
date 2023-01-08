package it.unibs.elabingesw.view.domaintyperenderer.renderers.utente;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.utente.Utente;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * @author Elia
 */
public class UtenteRenderer implements SelectableDomainTypeRenderer {
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        Utente utente = (Utente) domainTypeToRender;
        return "Utente{" + "username='" + utente.getUsername() + '\'' + '}';
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof Utente;
    }
}
