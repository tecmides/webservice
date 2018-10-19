package br.inf.ufrgs.tecmides.services.rule;

import br.inf.ufrgs.tecmides.entities.rule.RuleModelInstance;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RuleModelService {

    public List<RuleModelInstance> classify( List<RuleModelInstance> instances );

    public void saveInstances( List<RuleModelInstance> dataset );

    public void updateModel() throws Exception;

}
