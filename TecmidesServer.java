/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tecmides;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import tecmides.domain.Rule;

@WebService
@SOAPBinding(style = Style.DOCUMENT)

public interface TecmidesServer {

    @WebMethod
    List<Rule> generateRules(String ARFFString, int idxClassAttribute, int numRules);

    @WebMethod
    List<Rule> generateRulesByAttrRelativity(String ARFFString, int idxClassAttr, int numRules);

}
