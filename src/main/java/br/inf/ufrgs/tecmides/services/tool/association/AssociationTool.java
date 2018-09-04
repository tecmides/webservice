package br.inf.ufrgs.tecmides.services.tool.association;

import br.inf.ufrgs.tecmides.entities.Rule;
import java.util.List;
import weka.core.Instances;

public interface AssociationTool {

    public List<Rule> associate( Instances instances, int numRules, double minSupport, double minConfidence ) throws Exception;

}
