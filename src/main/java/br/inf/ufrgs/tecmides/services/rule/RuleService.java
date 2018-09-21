package br.inf.ufrgs.tecmides.services.rule;

import br.inf.ufrgs.tecmides.entities.Rule;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RuleService {

    public List<Rule> generateRules( RuleModelDataset dataset, int numRules, double minSupport, double minConfidence ) throws Exception;

    public List<Rule> filter( List<Rule> rules, double minLift, double minConviction );

}
