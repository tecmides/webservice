package br.inf.ufrgs.tecmides.services;

import br.inf.ufrgs.tecmides.entities.Rule;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RuleService {

    List<Rule> generateRules(String ARFFString, int numRules, double minSupport, double minConfidence);

    List<Rule> generateRulesByAttrRelativity(String ARFFString, int idxClassAttr, int numRules, double minSupport, double minConfidence);

}
