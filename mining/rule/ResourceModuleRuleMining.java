package tecmides.mining.rule;

import java.util.ArrayList;
import java.util.List;
import tecmides.domain.MiningAttribute;
import tecmides.domain.Rule;
import tecmides.tool.association.AssociationTool;
import weka.core.Instances;
import tecmides.tool.attrSelection.algorithm.AttrSelectionAlgorithmTool;
import tecmides.tool.attrSelection.algorithm.CsfSelection;

public class ResourceModuleRuleMining  extends GenericModuleRuleMining {

    @Override
    public List<Rule> mine(Instances instances, AssociationTool associator) throws Exception {
        AttrSelectionAlgorithmTool attrSelector = new CsfSelection();
        List<Rule> rules = new ArrayList<>();
        
        rules.addAll(associator.associate(attrSelector.run(instances, MiningAttribute.ST_INDIV_ASSIGN_LTSUBMIT.ordinal()), 10));
        rules.addAll(associator.associate(attrSelector.run(instances, MiningAttribute.ST_INDIV_SUBJECT_DIFF.ordinal()), 10));

        List<Rule> filteredRules = this.filter(rules);
                
        return filteredRules;
    }
    
}
