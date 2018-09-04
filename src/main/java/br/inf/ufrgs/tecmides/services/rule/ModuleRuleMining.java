package br.inf.ufrgs.tecmides.services.rule;

import br.inf.ufrgs.tecmides.entities.Rule;
import br.inf.ufrgs.tecmides.entities.RuleModelInstance;
import java.util.List;

public interface ModuleRuleMining {

    public List<Rule> getRules( List<RuleModelInstance> instances, RuleService ruleService ) throws Exception;

}
