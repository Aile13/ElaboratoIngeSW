package it.unibs.elabingesw.domaintypelimitedrenderer.limitedrenderers.scambio;

import it.unibs.elabingesw.businesslogic.DomainTypeToLimitedRender;
import it.unibs.elabingesw.businesslogic.scambio.IntervalloOrario;
import it.unibs.elabingesw.businesslogic.scambio.Scambio;
import it.unibs.elabingesw.domaintypelimitedrenderer.SelectableDomainTypeLimitedRenderer;
import it.unibs.elabingesw.domaintyperenderer.renderers.scambio.IntervalloOrarioRenderer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Elia
 */
public class ScambioLimitedRenderer implements SelectableDomainTypeLimitedRenderer {
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

    private String renderListaDiIntervalliOrari(List<IntervalloOrario> intervalliOrari) {
        return "[" + intervalliOrari.stream().map(intervalloOrario -> new IntervalloOrarioRenderer().render(intervalloOrario)).collect(Collectors.joining(", ")) +
                "]";
    }

    @Override
    public boolean canHandle(DomainTypeToLimitedRender domainTypeToLimitedRender) {
        return domainTypeToLimitedRender instanceof Scambio;
    }
}
