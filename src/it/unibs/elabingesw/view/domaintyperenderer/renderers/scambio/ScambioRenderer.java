package it.unibs.elabingesw.view.domaintyperenderer.renderers.scambio;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;
import it.unibs.elabingesw.view.domaintyperenderer.SelectableDomainTypeRenderer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe ScambioRenderer che implementa SelectableDomainTypeRenderer.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class ScambioRenderer implements SelectableDomainTypeRenderer {
    
    /**
     * Metodo che restituisce il rendering dell'oggetto in input
     * a cui verrà applicato un cast.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return il rendering dello scambio
     */
    @Override
    public String render(DomainTypeToRender domainTypeToRender) {
        Scambio scambio = (Scambio) domainTypeToRender;
        return "Scambio{" + "\n"
                + "\tpiazza='" + scambio.piazza() +
                "',\n" + "\tlistaLuoghi=" + scambio.listaLuoghi() +
                ",\n" + "\tgiorni=" + scambio.giorni() + "\n" +
                "\tintervalliOrari=" + renderListaDiIntervalliOrari(scambio.intervalliOrari()) +
                ",\n" + "\tScadenza=" + scambio.scadenza() +
                "\n" + '}';
    }

    /**
     * Metodo che restituisce il rendering della lista degli inter-
     * valli orari passati come parametro.
     *
     * @param intervalliOrari la lista degli intervalli orari
     * @return il rendering della lista degli intervalli orari
     */
    private String renderListaDiIntervalliOrari(List<IntervalloOrario> intervalliOrari) {
        return "[" + intervalliOrari.stream().map(intervalloOrario -> new IntervalloOrarioRenderer().render(intervalloOrario)).collect(Collectors.joining(", ")) +
                "]";
    }

    /**
     * Metodo che controlla se il renderer può gestire
     * il rendering dell'oggetto che viene passato per
     * parametro.
     *
     * @param domainTypeToRender l'oggetto di tipo DomainTypeToRender
     * @return TRUE se si può gestire il rendering dello scambio
     * FALSE se non si può gestire il rendering dello scambio
     */
    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof Scambio;
    }
}
