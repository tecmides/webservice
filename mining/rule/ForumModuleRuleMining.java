package tecmides.mining.rule;

import java.util.List;
import tecmides.domain.MiningAttribute;
import tecmides.domain.Rule;
import tecmides.tool.association.AssociationTool;
import tecmides.tool.attrSelection.AttrSelectionTool;
import tecmides.tool.attrSelection.GeneralSelection;
import weka.core.Instances;

public class ForumModuleRuleMining extends GenericModuleRuleMining {

    @Override
    public List<Rule> run(Instances instances, AssociationTool associator) throws Exception {
        AttrSelectionTool attrSelector = new GeneralSelection();

        // '-4' beacuse of the number of attributes removed before the index
        Instances preparedInstances = attrSelector.run(instances, "2,3,7,8,10,11", MiningAttribute.ST_GROUP_ASSIGN_LTSUBMIT.ordinal() - 4);
        
        List<Rule> rules = associator.run(preparedInstances, 20);

        List<Rule> filteredRules = this.filter(rules);

        return filteredRules;
    }

}
