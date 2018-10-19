package br.inf.ufrgs.tecmides.services.rule;

import br.inf.ufrgs.tecmides.entities.rule.Rule;
import br.inf.ufrgs.tecmides.entities.rule.RuleModelInstance;
import br.inf.ufrgs.tecmides.services.ModelDataset;
import java.util.ArrayList;
import java.util.List;

public class ResourceRuleMining extends ModuleRuleMining {

    @Override
    public List<Rule> getRules( List<RuleModelInstance> instances, RuleService ruleService ) throws Exception {
        List<Rule> rules = new ArrayList<>();

        rules.addAll(ruleService.generateRules(getResourceDataset1(instances), 10, 0.2, 0.7));
        rules.addAll(ruleService.generateRules(getResourceDataset2(instances), 10, 0.2, 0.7));

        return rules;
    }

    public ModelDataset getResourceDataset1( List<RuleModelInstance> instances ) {
        String[] wantedAttributes = {
            "st_indiv_assign_ltsubmit",
            "st_indiv_subject_diff",
            "rc_indiv_assign_ltsubmit",
            "rc_indiv_subject_diff",
            "rc_indiv_subject_keepup",
            "grade",
            "q_resource_view"
        };

        return this.createCustomDataset("resource_rel1", wantedAttributes, instances);
    }

    public ModelDataset getResourceDataset2( List<RuleModelInstance> instances ) {
        String[] wantedAttributes = {
            "st_indiv_assign_ltsubmit",
            "st_indiv_subject_diff",
            "grade",
            "q_resource_view"
        };

        return this.createCustomDataset("resource_rel2", wantedAttributes, instances);
    }

}
