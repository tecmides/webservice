package br.inf.ufrgs.tecmides.services;

import br.inf.ufrgs.tecmides.entities.RuleModelInstance;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RuleModelService {

    List<RuleModelInstance> classify(List<RuleModelInstance> instances);
    
    Boolean saveInstances(List<RuleModelInstance> dataset);    
    
}
