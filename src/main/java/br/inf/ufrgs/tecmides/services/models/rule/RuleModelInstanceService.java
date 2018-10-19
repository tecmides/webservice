package br.inf.ufrgs.tecmides.services.models.rule;

import br.inf.ufrgs.tecmides.entities.rule.Rule;
import br.inf.ufrgs.tecmides.entities.rule.RuleModelInstance;
import br.inf.ufrgs.tecmides.services.models.ModelInstanceService;
import org.springframework.stereotype.Service;

@Service
public interface RuleModelInstanceService extends ModelInstanceService<RuleModelInstance>, MatchableObjectService<RuleModelInstance> {

    public Boolean instanceMatchesRule( RuleModelInstance instance, Rule rule );
    
    public void setModelInstanceCoeficient(RuleModelInstance instance, double coeficient);

}
