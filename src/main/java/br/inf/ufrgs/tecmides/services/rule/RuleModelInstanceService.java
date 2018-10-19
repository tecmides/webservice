package br.inf.ufrgs.tecmides.services.rule;

import br.inf.ufrgs.tecmides.entities.rule.Matchable;
import org.springframework.stereotype.Service;

@Service
public interface RuleModelInstanceService {

    public Boolean instanceMatchesRule( Matchable instance, Matchable rule );

}
