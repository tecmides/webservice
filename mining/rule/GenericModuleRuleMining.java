package tecmides.mining.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import tecmides.domain.Mindstate;
import tecmides.domain.MiningAttribute;
import tecmides.domain.Operand;
import tecmides.domain.Rule;
import tecmides.tool.filter.RuleFilter;
import tecmides.tool.filter.RuleFilterImpl;

public abstract class GenericModuleRuleMining implements ModuleRuleMining {
    
    protected List<Rule> filter(List<Rule> rules) throws Exception {
        RuleFilter filter = new RuleFilterImpl();
        
        List<Rule> propertyFilteredRules = filter.filterByMinLift(filter.filterByMinConviction(rules, 1.1), 1.1);
                
        List<Rule> filteredRules = new ArrayList<>();
        
        filteredRules.addAll(filter.filterByConsequent(propertyFilteredRules, new Operand(MiningAttribute.ST_GROUP_ASSIGN_LTSUBMIT.name().toLowerCase() + "=" + Mindstate.DISCOURAGED.ordinal())));
        filteredRules.addAll(filter.filterByConsequent(propertyFilteredRules, new Operand(MiningAttribute.ST_INDIV_ASSIGN_LTSUBMIT.name().toLowerCase() + "=" + Mindstate.DISCOURAGED.ordinal())));
        filteredRules.addAll(filter.filterByConsequent(propertyFilteredRules, new Operand(MiningAttribute.ST_INDIV_SUBJECT_DIFF.name().toLowerCase() + "=" + Mindstate.DISCOURAGED.ordinal())));
        
        List<Rule> filteredRulesWithoutDuplicates = new ArrayList<>(new HashSet<>(filteredRules));
        
        return filteredRulesWithoutDuplicates;
    }
}
