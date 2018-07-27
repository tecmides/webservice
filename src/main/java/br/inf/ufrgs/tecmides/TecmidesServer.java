package br.inf.ufrgs.tecmides;

import br.inf.ufrgs.tecmides.domain.Rule;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)

public interface TecmidesServer {

    @WebMethod
    List<Rule> generateRules(String ARFFString, int numRules, double minSupport, double minConfidence);

    @WebMethod
    List<Rule> generateRulesByAttrRelativity(String ARFFString, int idxClassAttr, int numRules, double minSupport, double minConfidence);

}
