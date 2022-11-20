package it.unibs.elabingesw.domaintyperenderer.renderers.categoria;

import it.unibs.elabingesw.businesslogic.DomainTypeToRender;
import it.unibs.elabingesw.businesslogic.categoria.GerarchiaDiCategorie;
import it.unibs.elabingesw.businesslogic.categoria.TreeNode;
import it.unibs.elabingesw.domaintyperenderer.SelectableDomainTypeRenderer;

/**
 * @author Elia
 */
public class GerarchiaDiCategorieRenderer implements SelectableDomainTypeRenderer {
    @Override
    // todo da risolvere il tostring ridotto.
    public String render(DomainTypeToRender domainTypeToRender) {
        GerarchiaDiCategorie gerarchiaDiCategorie = (GerarchiaDiCategorie) domainTypeToRender;
        return "Gerarchia " + gerarchiaDiCategorie.getNome() + " {\n" + gerarchiaDiCategorie.getGerarchia().getAlberoString() + "}";
    }

    @Override
    public boolean canHandle(DomainTypeToRender domainTypeToRender) {
        return domainTypeToRender instanceof GerarchiaDiCategorie;
    }
}
