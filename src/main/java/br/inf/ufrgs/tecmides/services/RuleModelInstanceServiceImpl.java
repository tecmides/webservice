package br.inf.ufrgs.tecmides.services;

import br.inf.ufrgs.tecmides.entities.Rule;
import br.inf.ufrgs.tecmides.entities.RuleModelInstance;
import org.springframework.stereotype.Service;


@Service
public class RuleModelInstanceServiceImpl implements RuleModelInstanceService {

    @Override
    public Boolean instanceMatchesRule(RuleModelInstance instance, Rule rule) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
