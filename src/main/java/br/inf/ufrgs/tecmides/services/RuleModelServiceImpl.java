package br.inf.ufrgs.tecmides.services;

import br.inf.ufrgs.tecmides.entities.Rule;
import br.inf.ufrgs.tecmides.entities.RuleModelInstance;
import br.inf.ufrgs.tecmides.repositories.RuleModelInstanceRepository;
import br.inf.ufrgs.tecmides.repositories.RuleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleModelServiceImpl implements RuleModelService {

    @Autowired
    RuleRepository ruleRepository;
    
    @Autowired
    RuleModelInstanceRepository instanceRepository;
    
    @Autowired
    RuleModelInstanceService instanceService;

    @Override
    public List<RuleModelInstance> classify(List<RuleModelInstance> instances) {
        for(RuleModelInstance instance : instances) {
            Integer matchesCount = 0;
            List<Rule> rules = ruleRepository.findAll();
            
            if(rules.size() > 0) {
                // Apply all the rules
                for(Rule rule : rules) {
                    Boolean matches = instanceService.instanceMatchesRule(instance, rule);

                    if(matches) {
                        matchesCount++;
                    }
                }
                
                instance.setDiscouraged((matchesCount/rules.size()) >= 0.75);
            }
            else {
                instance.setDiscouraged(false);
            }
        }
        
        return instances;
    }

    @Override
    public Boolean saveInstances(List<RuleModelInstance> instances) {
        instanceRepository.saveAll(instances);
        
        return true;
    }
}
