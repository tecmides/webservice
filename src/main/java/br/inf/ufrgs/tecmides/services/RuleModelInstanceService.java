package br.inf.ufrgs.tecmides.services;

import br.inf.ufrgs.tecmides.entities.Rule;
import br.inf.ufrgs.tecmides.entities.RuleModelInstance;
import org.springframework.stereotype.Service;

@Service
public interface RuleModelInstanceService {

    Boolean instanceMatchesRule(RuleModelInstance instance, Rule rule);
    
}
