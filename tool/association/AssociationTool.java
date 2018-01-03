package tecmides.tool.association;

import java.util.List;
import tecmides.domain.Rule;

/**
 *
 * @author Bosista
 */
public interface AssociationTool {
    
    public List<Rule> associate(int numRules) throws Exception;
    
}
