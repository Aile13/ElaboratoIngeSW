package it.unibs.elabingesw.view.domaintypelimitedrenderer.limitedrenderers.scambio;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;
import it.unibs.elabingesw.view.domaintypelimitedrenderer.SelectableDomainTypeLimitedRenderer;
import it.unibs.elabingesw.view.domaintyperenderer.renderers.scambio.IntervalloOrarioRenderer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe ScambioLimitedRenderer che implementa SelectableDomainTypeLimitedRenderer.
 *
 * @author Elia Pitozzi
 * @author Ali Laaraj
 */
public class ScambioLimitedRenderer implements SelectableDomainTypeLimitedRenderer {
    
    /**
     * Metodo che restituisce il rendering limitato dell'oggetto
     * in input a cui verrà applicato un cast.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return il rendering limitato dello scambio
     */
    @Override
    public String render(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        Scambio scambio = (Scambio) domainTypeToLimitedRender;
        return "Scambio{" + "\n" +
                "\tpiazza='" + scambio.piazza() + "',\n" +
                "\tlistaLuoghi=" + scambio.listaLuoghi() + ",\n" +
                "\tgiorni=" + scambio.giorni() + ",\n" +
                "\tintervalliOrari=" + renderListaDiIntervalliOrari(scambio.intervalliOrari()) +
                "\n" + '}';
    }

    /**
     * Metodo che restituisce il rendering della lista degli inter-
     * valli orari passata come parametro.
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
     * il rendering limitato dell'oggetto che viene 
     * passato per parametro.
     *
     * @param domainTypeToLimitedRender l'oggetto di tipo DomainTypeToLimitedRender
     * @return TRUE se si può gestire il rendering limitato dello scambio
     * FALSE se non si può gestire il rendering limitato dello scambio
     */
    @Override
    public boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return domainTypeToLimitedRender instanceof Scambio;
    }
}
