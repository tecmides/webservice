package tecmides.mining.rule;

import java.util.List;
import tecmides.domain.Rule;
import tecmides.tool.association.AssociationTool;
import weka.core.Instances;

public interface ModuleRuleMining {
    
    public List<Rule> run(Instances instances, AssociationTool associator) throws Exception;
}
