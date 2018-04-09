package tecmides.tool.association;

import java.util.List;
import tecmides.domain.Rule;
import weka.core.Instances;

public interface AssociationTool {
    
    public List<Rule> associate(Instances instances, int numRules) throws Exception;

}
