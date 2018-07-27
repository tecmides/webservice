package br.inf.ufrgs.tecmides.tool.association;

import br.inf.ufrgs.tecmides.domain.Rule;
import java.util.List;
import weka.core.Instances;

public interface AssociationTool {

    public List<Rule> associate(Instances instances, int numRules, double minSupport, double minConfidence) throws Exception;

}
